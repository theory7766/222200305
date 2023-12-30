package com.west2;

import com.west2.DAO.JdbcUtils;
import com.west2.DAO.MyException;
import com.west2.DAO.OrderSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        OrderSystem h = new OrderSystem();
        // 开启订货系统
        h.startOrderSystem();

        // 测试对于商品表的增删改查

        /*System.out.println("--------测试删除商品表全部内容--------");
        // 删除商品表内容
        //h.deleteGoodsAll();
        System.out.println("--------查看删除结果--------");
        // 测试查询商品空表
        try{
            h.queryGoodsAll();
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        // 测试按编号删除
        try {
            System.out.println("测试按编号删除：");
            h.deleteGoodsById(17);// 可用18
            // 测试删除不存在编号
            h.deleteGoodsById(90);
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        // 测试按商品名称删除
        try {
            System.out.println("测试按商品名称删除：");
            h.deleteGoodsByName("暖宝宝");// 可用18
            // 测试删除不存在名称
            h.deleteGoodsByName("比亚迪");
        } catch (MyException e) {
            System.out.println(e.toString());
        }


        // 对商品表添加新商品
        System.out.println("\n--------进入商品表的插入功能测试--------");
        try{
            // 正确添加商品
            h.insertGoods("水杯",30.0);
            h.insertGoods("暖宝宝",9.9);
            h.insertGoods("围巾",15);
            h.insertGoods("手套",22.5);
            h.insertGoods("皮带",100);
            h.queryGoodsAll();
            // 添加同名商品
            h.insertGoods("围巾",20);
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        // 添加价格不大于零的商品
        try{
            h.insertGoods("围巾",-1);
        } catch (MyException e) {
            System.out.println(e.toString());
        }

        System.out.println("\n--------进入商品表的查询功能测试--------");
        // 查询特定商品名称的商品
        try {
            h.queryGoodsByName("皮带");
            // 测试查询不存在的商品
            if (h.queryGoodsByName("帽子")==-1){
                throw new MyException("帽子不存在于商品表中！");
            }
        }catch (MyException e){
            System.out.println(e.toString());
        }

        // 查询整张商品表
        try{
            h.queryGoodsAll();
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        // 按商品编号查询
        try {
            System.out.println("查询编号为18的商品");
            h.queryGoodsById(18);
            // 测试查询不存在的商品
            h.queryGoodsById(90);
        }catch (MyException e){
            System.out.println(e.toString());
        }
        // 按商品价格查询
        try {
            System.out.println("查询价格为9.9的商品：");
            h.queryGoodsByPrice(9.9);
            // 测试查询不存在的价格
            h.queryGoodsByPrice(14.9);

        } catch (MyException e) {
            System.out.println(e.toString());
        }
        // 商品表按价格从小到大排序
        System.out.println("\n商品表按价格从小到大排序:");
        try{
            h.sortGoodsByPrice();
        } catch (MyException e) {
            System.out.println(e.toString());
        }*/

        System.out.println("\n--------进入商品表的修改功能测试--------");
        try {
            h.modifyGoodsByName("皮带","耳机");
            // 测试旧商品名不存在
            //h.modifyGoodsByName("麦克风","手镯");
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        // 测试新商品名已经存在
        /*try {
            h.modifyGoodsByName("手机支架","暖宝宝");
        } catch (MyException e) {
            System.out.println(e.toString());
        }

        System.out.println("修改特定商品名称的价格");
        try {
            h.modifyGoodsPriceByName("手机支架",19.9);
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        try {
            h.modifyGoodsPriceByName("手机支架",-1);
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        try {
            h.modifyGoodsPriceByName("比亚迪",19.9);
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        */


        // 关闭订货系统
        h.closeOrderSystem();

    }
}
