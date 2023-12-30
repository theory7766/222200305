package com.west2.entity;

import java.sql.Time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderForm {
    // 订单编号
    private int no;
    // 商品信息
    private List<Integer> goods_id;
    // 下单时间
    private LocalDateTime ordertime;
    // 订单价格
    private double price;

    public OrderForm() {
    }

    public OrderForm(int no, List<Integer> goods_id, LocalDateTime ordertime, double price) {
        this.no = no;
        this.goods_id = goods_id;
        this.ordertime = ordertime;
        this.price = price;
    }

    /**
     * 获取订单编号
     *
     * @return no
     */
    public int getNo() {
        return no;
    }

    /**
     * 设置订单编号
     *
     * @param no
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * 获取商品信息
     *
     * @return goods_id
     */
    public List<Integer> getGoodsId() {
        return goods_id;
    }

    /**
     * 设置商品信息
     *
     * @param goods_id
     */
    public void setGoods(List<Integer> goods_id) {
        this.goods_id = goods_id;
    }

    /**
     * 获取下单时间
     *
     * @return ordertime
     */
    public LocalDateTime getOrdertime() {
        return ordertime;
    }

    /**
     * 设置下单时间
     *
     * @param ordertime
     */
    public void setOrdertime(LocalDateTime ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * 获取订单价格
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置订单价格
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "订单编号 : " + no + "\n" + "商品编号：" + goods_id +
                "\n下单时间 : " + ordertime + "\n订单价格 : " + price;
    }
}
