package com.west2.DAO;

import com.west2.entity.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;


public class OrderSystem {
    private static Connection con = null;
    private static PreparedStatement pst = null;

    private static Statement st = null;
    private static ResultSet rs = null;

    // 开启订货系统
    public void startOrderSystem() {
        try {
            con = JdbcUtils.getConnection();
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭订货系统
    public void closeOrderSystem() {
        try {
            JdbcUtils.release(con, pst, st, rs);
        } catch (MyException e) {
            System.out.println("订货系统关闭失败：" + e.toString());
        }
    }

    // 显示查询结果
    public void showQuery(List<Object[]> result, int cnt) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < cnt; j++) {
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
            List<Object[]> result = JdbcUtils.queryOrder(sql, st, rs);
            if (result!=null) {
                showQuery(result, 3);
            } else {
                throw new MyException("查询失败：商品表为空！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MyException e) {
            throw e;
        }
    }

    // 商品表按价格排序
    public void sortGoodsByPrice() throws MyException {
        try {
            String sql = "select * from goods order by `goods_price`";
            List<Object[]> result = JdbcUtils.queryOrder(sql, st, rs);
            if (!result.isEmpty()) {
                showQuery(result, 3);
            } else {
                throw new MyException("查询失败：商品表为空！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MyException e) {
            throw e;
        }

    }

    /**
     * 查询商品表中特定商品名称的商品
     *
     * @param name 待查询商品名称
     * @return int 返回查询到的商品编号，没查到就返回-1
     */
    public int queryGoodsByName(String name) throws MyException {
        try {
            String sql = "select * from goods where `goods_name`like ?";
            Object[] tmp = {name};
            List<Object[]> result = null;
            result = JdbcUtils.queryOrder(con, sql, pst, rs, tmp);
            if (result == null) {
                throw new MyException(name + "不存在于商品表中！");
            } else {
                // 在插入时保证商品名称的唯一性
                // 返回查询到数据的主键
                showQuery(result, 3);
                return (int) result.get(0)[0];
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (MyException e) {
            return -1;
        }
    }

    // 按商品编号查询
    public boolean queryGoodsById(int id) throws MyException {
        try {
            String sql = "select * from goods where goods_id = ?";
            Object[] tmp = {id};
            List<Object[]> result = null;
            result = JdbcUtils.queryOrder(con, sql, pst, rs, tmp);
            if (result == null) {
                throw new MyException("商品表不存在编号为" + id + "的商品！");
            } else {
                showQuery(result, 3);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (MyException e) {
            throw e;
        }
    }

    // 按商品价格查询
    public boolean queryGoodsByPrice(double price) throws MyException {
        try {
            String sql = "select * from goods where goods_price = ?";
            Object[] tmp = {price};
            List<Object[]> result = null;
            result = JdbcUtils.queryOrder(con, sql, pst, rs, tmp);
            if (result == null) {
                throw new MyException("商品表不存在价格为" + price + "的商品！");
            } else {
                showQuery(result, 3);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (MyException e) {
            throw e;
        }
    }

    /**
     * 往商品表中插入数据
     *
     * @param name  商品名称
     * @param price 商品价格
     * @throws MyException
     */
    public void insertGoods(String name, double price) throws MyException {
        try {
            // 使用?占位符解决SQL注入问题
            String sql = "insert into goods(`goods_name`,`goods_price`) values (?,?)";

            // 对name与price进行参数判断
            if (price <= 0) {
                throw new MyException("插入失败：商品价格必须大于0元！");
            } else {
                // 查询商品表中是否已经存在该商品名称
                if (queryGoodsByName(name) != -1) {
                    //System.out.println("插入的主键为"+queryGoodsByName(name));
                    throw new MyException("插入失败：商品表中已存在名称为" + name + "的商品！");
                } else {
                    Object[] param = {name, price};
                    con.setAutoCommit(false);//开启事务
                    JdbcUtils.update(con, sql, param, pst, rs);
                    System.out.println("插入" + name + "成功");
                    con.commit();
                }
            }
        } catch (MyException e) {
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除整张商品表的内容
    public void deleteGoodsAll() {
        try {
            String sql = "delete from goods";
            con.setAutoCommit(false);//开启事务
            st.executeUpdate(sql);
            con.commit();
            System.out.println("商品表已成功清空！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 按编号删除商品表内容
    public void deleteGoodsById(int id) throws MyException {
        try {
            String sql = "delete from goods where goods_id = ?";
            Object[] param = {id};
            if (queryGoodsById(id)) {
                con.setAutoCommit(false);//开启事务
                JdbcUtils.update(con, sql, param, pst, rs);
                con.commit();
                System.out.println("删除商品表中商品编号为" + id + "的商品成功！");
            }
        } catch (MyException e) {
            System.out.print("删除失败：");
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 按商品名称删除商品表内容
    public void deleteGoodsByName(String name) throws MyException {
        try {
            String sql = "delete from goods where goods_name like ?";
            Object[] param = {name};
            if (queryGoodsByName(name) != -1) {
                con.setAutoCommit(false);//开启事务
                JdbcUtils.update(con, sql, param, pst, rs);
                con.commit();
                System.out.println("删除商品表中商品名称为" + name + "的商品成功！");
            }
        } catch (MyException e) {
            System.out.print("删除失败：");
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改商品名字
    public void modifyGoodsNameById(int id, String newName) throws MyException {
        try {
            String sql = "update goods set goods_name = ? where goods_id = ?";
            if (!queryGoodsById(id)) {
                throw new MyException("商品编号为" + id + "的商品不存在于商品表！");
            } else if (queryGoodsByName(newName) != -1) {
                throw new MyException(newName + "已存在于商品表！");
            } else {
                Object[] param = {newName, id};
                con.setAutoCommit(false);//开启事务
                JdbcUtils.update(con, sql, param, pst, rs);
                con.commit();
                System.out.println("已将商品编号为" + id + "的商品名称" + "修改为" + newName);
            }
        } catch (MyException e) {
            System.out.print("修改商品名字失败:");
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 修改特定编号商品的价格
    public void modifyGoodsPriceById(int id, double price) throws MyException {
        try {
            String sql = "update goods set goods_price = ? where goods_id = ?";
            if (price <= 0) {
                throw new MyException("商品价格必须大于0！");
            } else if (queryGoodsById(id)) {
                throw new MyException("商品编号为" + id + "的商品不存在于商品表！");
            } else {
                Object[] param = {price, id};
                con.setAutoCommit(false);//开启事务
                JdbcUtils.update(con, sql, param, pst, rs);
                con.commit();
                System.out.println("已将商品编号" + id + "的价格修改为" + price);
            }
        } catch (MyException e) {
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除整张订单表与订单商品关联表的内容
    public void deleteOrderFormAll() {
        try {
            String sql1 = "delete from order_form";
            String sql2 = "delete from order_goods";
            con.setAutoCommit(false);//开启事务
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);
            con.commit();
            System.out.println("订单表与订单商品关联表已成功清空！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 增加订单
    public void insertOrderForm(List<Integer> goods, double price) throws MyException {
        try {
            LocalDateTime t = LocalDateTime.now();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = sdf.format(t);
            String sql1 = "insert into order_form(`order_time`,`order_price`) values ('" + datetime + "',?)";
            String sql2 = "insert into order_goods(`order_id`,`good_id`) values (?,?)";
            if (price <= 0) {
                throw new MyException("插入失败：商品价格必须大于0元！");
            } else {
                for (int i = 0; i < goods.size(); i++) {
                    queryGoodsById(goods.get(i));
                }
                Object[] param = {price};
                con.setAutoCommit(false);//开启事务
                List<Integer> tmp = JdbcUtils.update(con, sql1, param, pst, rs);
                //System.out.println(tmp);
                System.out.println("已将订单插入到订单表中！");
                for (int i = 0; i < goods.size() && tmp != null; i++) {
                    Object[] params = {tmp.get(0), goods.get(i)};
                    JdbcUtils.update(con, sql2, params, pst, rs);
                }
                con.commit();
                System.out.println("已更新订单商品关联表！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // 查询订单表以及订单关联表
    public void queryOrderTableALL() throws MyException {
        try {
            String sql1 = "select * from order_form";
            String sql2 = "select * from order_goods";
            System.out.println("正在为您查询订单表：");
            List<Object[]> result1 = JdbcUtils.queryOrder(sql1, st, rs);
            if (result1 != null) {
                showQuery(result1, 3);
            } else {
                throw new MyException("查询失败：订单表为空！");
            }
            System.out.println("正在为您查询订单商品关联表：");
            List<Object[]> result2 = JdbcUtils.queryOrder(sql2, st, rs);
            if (result2 != null) {
                showQuery(result2, 3);
            } else {
                throw new MyException("查询失败：订单表为空！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据id查找订单内容
    public boolean queryOrderById(int id) throws MyException {
        try {
            String sql1 = "select * from order_form where order_id = ?";
            String sql2 = "select * from order_goods where order_id = ?";
            Object[] param = {id};
            List<Object[]> result1 = JdbcUtils.queryOrder(con, sql1, pst, rs, param);
            List<Object[]> result2 = JdbcUtils.queryOrder(con, sql2, pst, rs, param);
            if (result1 == null || result2 == null) {
                throw new MyException("订单编号错误！");
            } else {
                for (int i = 0; i < result1.size(); i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(result1.get(i)[j]);
                        System.out.print('\t');
                    }
                    System.out.println("对应的商品信息:");
                    // 根据good_id去输出商品信息
                    queryGoodsById((int) result2.get(i)[2]);
                    System.out.println();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据id删除订单
    public void deleteOrderById(int id) throws MyException {
        try {
            String sql1 = "delete from order_form where order_id = ? ";
            String sql2 = "delete from order_goods where order_id = ?";
            Object[] param = {id};
            if (queryOrderById(id)) {
                con.setAutoCommit(false);//开启事务
                JdbcUtils.update(con, sql1, param, pst, rs);
                JdbcUtils.update(con, sql2, param, pst, rs);
                con.commit();
                System.out.println("已将订单编号为" + id + "的订单从订单表与订单商品关联表删除!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据id修改订单价格
    public void modifyOrderPriceById(int id, double price) throws MyException {
        String sql = "update order_form set order_price = ? where order_id = ?";
        if (price <= 0) {
            throw new MyException("订单价格必须大于0！");
        } else if (queryOrderById(id)) {
            Object[] param = {price, id};
            JdbcUtils.update(con, sql, param, pst, rs);
            System.out.println("已成功修改编号为" + id + "的订单价格为" + price);
        }
    }

    // 根据good_id和order_id修改订单中商品信息
    public void modifyOrderGoodsById(int old_good_id, int new_good_id, int order_id) throws MyException {
        try {
            String sql = "update order_goods set good_id = ? where order_id = ? where good_id = ?";
            if (queryGoodsById(old_good_id) && queryGoodsById(new_good_id) && queryOrderById(order_id)) {
                Object[] param = {new_good_id, order_id, old_good_id};
                con.setAutoCommit(false);//开启事务
                JdbcUtils.update(con, sql, param, pst, rs);
                con.commit();
                System.out.println("已将订单编号为" + order_id + "的订单中的商品编号由" + old_good_id +
                        "修改为" + new_good_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 按下单时间排序
    public void sortOrderByTime() throws MyException {
        try {
            String sql = "select * from order_form order by `order_time`";
            List<Object[]> result = JdbcUtils.queryOrder(sql, st, rs);
            if (result != null) {
                showQuery(result, 3);
            } else {
                throw new MyException("查询失败：订单表为空！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 按订单价格排序
    public void sortOrderByPrice() throws MyException {
        try {
            String sql = "select * from order_form order by `order_price`";
            List<Object[]> result = JdbcUtils.queryOrder(sql, st, rs);
            if (result != null) {
                showQuery(result, 3);
            } else {
                throw new MyException("查询失败：订单表为空！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
