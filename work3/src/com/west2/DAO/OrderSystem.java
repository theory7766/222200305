package com.west2.DAO;

import com.west2.entity.*;

import java.sql.*;
import java.util.List;


public class OrderSystem {
    private static Connection con = null;
    private static PreparedStatement pst = null;

    private static Statement st = null;
    private static ResultSet rs = null;

    // 开启订货系统
    public void startOrderSystem(){
        try{
            con = JdbcUtils.getConnection();
            st = con.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 关闭订货系统
    public void closeOrderSystem(){
        try{
            JdbcUtils.release(con, pst,st,rs);
        }catch (MyException e){
            System.out.println("订货系统关闭失败："+e.toString());
        }
    }

    // 显示查询结果
    public void showQuery(List<Object[]> result,int cnt){
        for (int i=0;i<result.size();i++){
            for (int j=0;j<cnt;j++){
                System.out.print(result.get(i)[j]);
                System.out.print('\t');
            }
            System.out.println();
        }
    }

    // 查询整张商品表
    public void queryGoodsAll() throws MyException {
        try {
            String sql = "select * from goods";
            List<Object[]> result = JdbcUtils.queryOrder(sql,st,rs);
            if (!result.isEmpty()){
                showQuery(result,3);
           }else {
               throw new MyException("查询失败：商品表为空！");
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MyException e){
            throw e;
        }
    }
    // 商品表按价格排序
    public void queryGoodsByPrice() throws MyException {
        try {
            String sql = "select * from goods order by `goods_price`";
            List<Object[]> result = JdbcUtils.queryOrder(sql,st,rs);
            if (!result.isEmpty()){
                showQuery(result,3);
            }else {
                throw new MyException("查询失败：商品表为空！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MyException e) {
            throw e;
        }

    }

    /**查询商品表中特定商品名称的商品
     *
     * @param name 待查询商品名称
     * @return int 返回查询到的商品编号，没查到就返回-1
     * */
    public int queryGoodsByName(String name) throws MyException {
        try {
            String sql = "select * from goods where `goods_name`like ?";
            Object[] tmp= {name};
            List<Object[]> result = null;
            result = JdbcUtils.queryOrder(con,sql,pst,rs,tmp);
            if (result==null){
                throw new MyException(name+"不存在于商品表中！");
            }else {
                // 在插入时保证商品名称的唯一性
                // 返回查询到数据的主键
                showQuery(result,3);
                return (int)result.get(0)[0];
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        } catch (MyException e) {
            return -1;
        }
    }

    // 按商品编号查询
    public boolean queryGoodsById(int id) throws MyException {
        try{
            String sql = "select * from goods where goods_id = ?";
            Object[] tmp= {id};
            List<Object[]> result = null;
            result = JdbcUtils.queryOrder(con,sql,pst,rs,tmp);
            if (result==null){
                throw new MyException("商品表不存在编号为"+id+"的商品！");
            }else {
                showQuery(result,3);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (MyException e) {
            throw e;
        }
    }
    // 按商品价格排序
    public boolean SortGoodsByPrice(double price) throws MyException {
        try {
            String sql = "select * from goods where goods_price = ?";
            Object[] tmp= {price};
            List<Object[]> result = null;
            result = JdbcUtils.queryOrder(con,sql,pst,rs,tmp);
            if (result==null){
                throw new MyException("商品表不存在价格为"+ price +"的商品！");
            }else {
                showQuery(result,3);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (MyException e) {
            throw e;
        }
    }

    /**往商品表中插入数据
     *
     * @param name 商品名称
     * @param price 商品价格
     * @throws MyException
     * */
    public void insertGoods(String name,double price)throws MyException{
        try{
            // 使用?占位符解决SQL注入问题
            String sql = "insert into goods(`goods_name`,`goods_price`) values (?,?)";

            // 对name与price进行参数判断
            if (price <= 0){
                throw new MyException("插入失败：商品价格必须大于0元！");
            }else {
                // 查询商品表中是否已经存在该商品名称
                if (queryGoodsByName(name)!=-1){
                    //System.out.println("插入的主键为"+queryGoodsByName(name));
                    throw new MyException("插入失败：商品表中已存在名称为"+name+"的商品！");
                }else {
                    Object[] param={name,price};
                    JdbcUtils.update(con,sql,param,pst,rs);
                    System.out.println("插入"+name+"成功");
                }
            }
        }catch (MyException e){
            throw e;
        }
    }

    // 删除整张商品表的内容
    public void deleteGoodsAll(){
        try{
            String sql="delete from goods";
            st.executeUpdate(sql);
            System.out.println("商品表已成功清空！");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 按编号删除商品表内容
    public void deleteGoodsById(int id) throws MyException {
        try {
            String sql="delete from goods where goods_id = ?";
            Object[] param= {id};
            if (queryGoodsById(id)){
                JdbcUtils.update(con,sql,param,pst,rs);
                System.out.println("删除商品表中商品编号为"+id+"的商品成功！");
            }
        } catch (MyException e) {
            System.out.print("删除失败：");
            throw e;
        }
    }

    // 按商品名称删除商品表内容
    public void deleteGoodsByName(String name) throws MyException {
        try {
            String sql = "delete from goods where goods_name like ?";
            Object[] param= {name};
            if (queryGoodsByName(name)!=-1){
                JdbcUtils.update(con,sql,param,pst,rs);
                System.out.println("删除商品表中商品名称为"+name+"的商品成功！");
            }
        }catch (MyException e){
            System.out.print("删除失败：");
            throw e;
        }
    }
    // 修改商品名字
    public void modifyGoodsByName(String oldName,String newName) throws MyException {
//        try {
//            String sql = "update goods set goods_name = ? where goods_name = ?";
//            if (queryGoodsByName(oldName)==-1){
//                throw new MyException(oldName+"不存在于商品表！");
//            }else if (queryGoodsByName(newName)!=-1){
//                throw new MyException(newName+"已存在于商品表！");
//            }else {
//                Object[] param= {oldName,newName};
//                JdbcUtils.update(con,sql,param,pst,rs);
//                System.out.println("已将商品名称"+oldName+"修改为"+newName);
//            }
//        } catch (MyException e) {
//            System.out.print("修改商品名字失败:");
//            throw e;
//        }
        String sql = "update goods set goods_name = ? where goods_name = ?";
        Object[] param= {oldName,newName};
        JdbcUtils.update(con,sql,param,pst,rs);
        System.out.println("已将商品名称"+oldName+"修改为"+newName);
    }

    // 修改特定名称商品的价格
    public void modifyGoodsPriceByName(String name,double price) throws MyException {
        try {
            String sql = "update goods set goods_price = ? where goods_name = ?";
            if (price<=0){
                throw new MyException("商品价格必须大于0！");
            }else if (queryGoodsByName(name)==-1){
                throw new MyException(name+"不存在于商品表！");
            }else {
                Object[] param= {name,price};
                JdbcUtils.update(con,sql,param,pst,rs);
                System.out.println("已将"+name+"的价格修改为"+price);
            }
        } catch (MyException e) {
            throw e;
        }
    }
    // 删除整张订单表与订单商品关联表的内容
    public void deleteOrderFormAll(){
        try {
            String sql1="delete from order_form";
            String sql2="delete from order_goods";
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);
            System.out.println("订单表与订单商品关联表已成功清空！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
