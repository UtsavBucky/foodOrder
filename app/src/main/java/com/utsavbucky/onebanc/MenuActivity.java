package com.utsavbucky.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.utsavbucky.onebanc.adapters.DishAdapter;
import com.utsavbucky.onebanc.adapters.MenusAdapter;
import com.utsavbucky.onebanc.models.Dishes;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView menuRecyclerView;
    private MenusAdapter menuAdapter;
    ArrayList<Dishes> menulist = new ArrayList<>();
    ArrayList<Dishes> finalOrderList = new ArrayList<>();
    RelativeLayout checkoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuRecyclerView = findViewById(R.id.items_list);
        checkoutButton = findViewById(R.id.order_button);
        setMenuList();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalOrderList.clear();
                for(int i=0;i<menulist.size();i++)
                {
                    if(menulist.get(i).quantity>0) {
                        finalOrderList.add(menulist.get(i));
                    }
                }
                if(finalOrderList.size()>0){
                    Intent intent = new Intent(MenuActivity.this, checkout.class);
                    intent.putExtra("orderList", (Serializable) finalOrderList);
                    startActivity(intent);
                }

            }
        });
    }

    private void setMenuList() {
        menulist.add(new Dishes("","Dhokla","1",constants.NORTH_INDIAN,100,0,0));
        menulist.add(new Dishes("","Dosa","2", constants.SOUTH_INDIAN,80,0,0));
        menulist.add(new Dishes("","Paneer pasanda","3", constants.NORTH_INDIAN, 200,0,0));
        menulist.add(new Dishes("","Pizza","4", constants.ITALIAN, 350,0,0));
        menulist.add(new Dishes("","Burger","5", constants.CHINESE,120,0,0));
        menuAdapter = new MenusAdapter(menulist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        menuRecyclerView.setLayoutManager(layoutManager);
        menuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        menuRecyclerView.setAdapter(menuAdapter);
    }
}