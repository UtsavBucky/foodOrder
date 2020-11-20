package com.utsavbucky.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.utsavbucky.onebanc.adapters.DishAdapter;
import com.utsavbucky.onebanc.models.Category;
import com.utsavbucky.onebanc.models.Dishes;
import com.utsavbucky.onebanc.models.Orders;
import com.utsavbucky.onebanc.utils.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView topDishes, previousOrders;
    private DishAdapter topDishesAdapter;
    ArrayList<Dishes> dishesList = new ArrayList<>();
    ArrayList<Category> categoryList = new ArrayList<>();
    SharedPreferences dishSharedPreferences, categorySharedPreferences;
    ArrayList<Orders> ordersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topDishes = findViewById(R.id.top_dishes);
        previousOrders = findViewById(R.id.previous_orders);

        categoryList.add(new Category("",constants.NORTH_INDIAN,"North Indian"));
        categoryList.add(new Category("",constants.CHINESE,"Chinese"));
        categoryList.add(new Category("",constants.MEDITERRANEAN,"Mediterranean"));
        categoryList.add(new Category("",constants.SOUTH_INDIAN,"South Indian"));
        categoryList.add(new Category("",constants.ITALIAN,"Italian"));

        dishesList.add(new Dishes("","Dhokla","1",constants.NORTH_INDIAN,100,0,0));
        dishesList.add(new Dishes("","Dosa","2", constants.SOUTH_INDIAN,80,0,0));
        dishesList.add(new Dishes("","Paneer pasanda","3", constants.NORTH_INDIAN, 200,0,0));
        dishesList.add(new Dishes("","Pizza","4", constants.ITALIAN, 350,0,0));
        dishesList.add(new Dishes("","Burger","5", constants.CHINESE,120,0,0));

        dishSharedPreferences=getSharedPreferences(constants.DISHES_LIST,0);
        categorySharedPreferences = getSharedPreferences(constants.CATEGORIES_LIST,0);

        SharedPreferences.Editor editorDishes = dishSharedPreferences.edit();
        editorDishes.putString("dishesList",new Gson().toJson(dishesList));
        editorDishes.apply();

        SharedPreferences.Editor editorCategories = categorySharedPreferences.edit();
        editorCategories.putString("categoryList",new Gson().toJson(categoryList));
        editorCategories.apply();


        setCategoriesList();
        setTopDishes();
        setPreviousOrders();
    }
    
    public void setCategoriesList(){

    }
    
    public void setTopDishes() {

        topDishesAdapter = new DishAdapter(MainActivity.this,dishesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        topDishes.setLayoutManager(layoutManager);
        topDishes.setItemAnimator(new DefaultItemAnimator());
        topDishes.setAdapter(topDishesAdapter);
    }

    public void setPreviousOrders() {
        ordersList = Util.getOrdersList(MainActivity.this);

    }
}