package com.utsavbucky.onebanc.models;

import java.io.Serializable;

public class Dishes implements Serializable {
    public String dishImg;
    public String dishName;
    public String dishId;
    public int dishCategory;
    public int price;
    public int quantity;
    public int soldQuantity;

     public Dishes(String dishImg, String dishName, String dishId) {
         this.dishImg = dishImg;
         this.dishName = dishName;
         this.dishId = dishId;
     }

     public Dishes(String dishImg, String dishName, String dishId, int dishCategory, int price, int quantity, int soldQuantity) {
         this.dishImg = dishImg;
         this.dishName = dishName;
         this.dishId = dishId;
         this.dishCategory = dishCategory;
         this.price = price;
         this.quantity = quantity;
         this.soldQuantity = soldQuantity;
     }
 }
