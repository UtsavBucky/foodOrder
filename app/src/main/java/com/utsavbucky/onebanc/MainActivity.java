package com.utsavbucky.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.utsavbucky.onebanc.adapters.DishAdapter;
import com.utsavbucky.onebanc.adapters.ViewPagerAdapter;
import com.utsavbucky.onebanc.databinding.ActivityMainBinding;
import com.utsavbucky.onebanc.models.Category;
import com.utsavbucky.onebanc.models.Dishes;
import com.utsavbucky.onebanc.models.Orders;
import com.utsavbucky.onebanc.utils.Util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentPage=0;
    private Timer timer;

    RecyclerView topDishes, previousOrders;
    private DishAdapter topDishesAdapter;
    ArrayList<Dishes> dishesList = new ArrayList<>();
    ArrayList<Category> categoryList = new ArrayList<>();
    SharedPreferences dishSharedPreferences, categorySharedPreferences;
    ArrayList<Orders> ordersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        topDishes = findViewById(R.id.top_dishes);
        previousOrders = findViewById(R.id.previous_orders);

        prepareData();
        setCategoriesList();
        setTopDishes();
        setPreviousOrders();
    }
    
    public void setCategoriesList(){
        binding.viewPager.setAdapter(new ViewPagerAdapter(this, categoryList));
        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                binding.viewPager.setCurrentItem(currentPage, true);
                if (currentPage == 5) {
                    currentPage = 0;
                } else {
                    ++currentPage;
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 2500);
    }

    public void prepareData(){
        categoryList.add(new Category("https://d4t7t8y8xqo0t.cloudfront.net/resized/750X436/eazytrendz%2F2137%2Ftrend20181213060017.jpg",constants.NORTH_INDIAN,"North Indian"));
        categoryList.add(new Category("https://www.kohinoorfoods.co.uk/wp-content/uploads/2020/01/indo-chinese-food.jpg",constants.CHINESE,"Chinese"));
        categoryList.add(new Category("https://nomadparadise.com/wp-content/uploads/2020/05/mediterranean-food-011.jpg",constants.MEDITERRANEAN,"Mediterranean"));
        categoryList.add(new Category("https://d4t7t8y8xqo0t.cloudfront.net/resized/750X436/eazytrendz%2F2033%2Ftrend20180920125236.jpg",constants.SOUTH_INDIAN,"South Indian"));
        categoryList.add(new Category("https://restaurantindia.s3.ap-south-1.amazonaws.com/s3fs-public/content10735.jpg",constants.ITALIAN,"Italian"));

        dishesList.add(new Dishes("https://cdn2.foodviva.com/static-content/food-images/snacks-recipes/khaman-dhokla-recipe/khaman-dhokla-recipe.jpg","Dhokla","1",constants.NORTH_INDIAN,100,0,0));
        dishesList.add(new Dishes("https://sukhis.com/wp-content/uploads/2020/01/Dosa-500x400.jpg","Dosa","2", constants.SOUTH_INDIAN,80,0,0));
        dishesList.add(new Dishes("https://farm1.staticflickr.com/269/19741628821_7ff0dd8b7c_o.jpg","Paneer pasanda","3", constants.NORTH_INDIAN, 200,0,0));
        dishesList.add(new Dishes("https://farm1.staticflickr.com/269/19741628821_7ff0dd8b7c_o.jpg","Pizza","4", constants.ITALIAN, 350,0,0));
        dishesList.add(new Dishes("https://simply-delicious-food.com/wp-content/uploads/2015/07/Bacon-and-cheese-burgers-3-500x500.jpg","Burger","5", constants.CHINESE,120,0,0));

        dishSharedPreferences=getSharedPreferences(constants.DISHES_LIST,0);
        categorySharedPreferences = getSharedPreferences(constants.CATEGORIES_LIST,0);

        SharedPreferences.Editor editorDishes = dishSharedPreferences.edit();
        editorDishes.putString("dishesList",new Gson().toJson(dishesList));
        editorDishes.apply();

        SharedPreferences.Editor editorCategories = categorySharedPreferences.edit();
        editorCategories.putString("categoryList",new Gson().toJson(categoryList));
        editorCategories.apply();

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