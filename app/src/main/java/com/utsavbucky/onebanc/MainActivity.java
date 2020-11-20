package com.utsavbucky.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.utsavbucky.onebanc.adapters.DishAdapter;
import com.utsavbucky.onebanc.models.Dishes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView topDishes, previousOrders;
    private DishAdapter topDishesAdapter;
    ArrayList<Dishes> dishesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topDishes = findViewById(R.id.top_dishes);
        previousOrders = findViewById(R.id.previous_orders);
        setCategoriesList();
        setTopDishes();
        setPreviousOrders();
    }
    
    public void setCategoriesList(){}
    
    public void setTopDishes() {
        dishesList.add(new Dishes("","Dhokla","1"));
        dishesList.add(new Dishes("","Dhokla","2"));
        dishesList.add(new Dishes("","Dhokla","3"));
        dishesList.add(new Dishes("","Dhokla","4"));
        dishesList.add(new Dishes("","Dhokla","5"));
        topDishesAdapter = new DishAdapter(MainActivity.this,dishesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        topDishes.setLayoutManager(layoutManager);
        topDishes.setItemAnimator(new DefaultItemAnimator());
        topDishes.setAdapter(topDishesAdapter);
    }

    public void setPreviousOrders() {
        
    }
}