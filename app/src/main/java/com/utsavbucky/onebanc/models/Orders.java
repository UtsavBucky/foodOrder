package com.utsavbucky.onebanc.models;

import java.util.List;

public class Orders {

    public List<Dishes> orderDishesList ;
    public String orderId ;
    public int orderPrice;
    public String orderDate;

    public Orders(List<Dishes> orderDishesList, String orderId, int orderPrice, String orderDate) {
        this.orderDishesList = orderDishesList;
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
    }
}
