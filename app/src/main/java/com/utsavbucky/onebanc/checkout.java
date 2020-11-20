package com.utsavbucky.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.utsavbucky.onebanc.adapters.MenusAdapter;
import com.utsavbucky.onebanc.adapters.OrdersAdapter;
import com.utsavbucky.onebanc.models.Dishes;
import com.utsavbucky.onebanc.models.Orders;
import com.utsavbucky.onebanc.utils.Util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class checkout extends AppCompatActivity {
    RecyclerView orderRecyclerView;
    private OrdersAdapter orderAdapter;
    ArrayList<Dishes> orderlist = new ArrayList<>();
    SharedPreferences ordersSharedPreferences;
    RelativeLayout orderButton;
    TextView orderQuantity, orderPrice;
    int nos, total;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    ArrayList<Orders> previousOrdersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        orderRecyclerView = findViewById(R.id.orders_list);
        orderButton = findViewById(R.id.checkout_button);
        orderPrice = findViewById(R.id.order_price);
        orderQuantity = findViewById(R.id.order_quantity);

        Intent i = getIntent();
        orderlist = (ArrayList<Dishes>) i.getSerializableExtra("orderList");
        setOrderList();

        previousOrdersList = Util.getOrdersList(checkout.this);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String orderId = generateOrderId(10);
             //Orders currentOrder = new Orders(orderlist,orderId,total,);

            }

        });
    }

    private void setOrderList() {
        /*orderlist.add(new Dishes("","Dhokla","1",constants.NORTH_INDIAN,100,0,0));
        orderlist.add(new Dishes("","Dosa","2", constants.SOUTH_INDIAN,80,0,0));
        orderlist.add(new Dishes("","Paneer pasanda","3", constants.NORTH_INDIAN, 200,0,0));
        orderlist.add(new Dishes("","Pizza","4", constants.ITALIAN, 350,0,0));
        orderlist.add(new Dishes("","Burger","5", constants.CHINESE,120,0,0));*/

        for(int i=0;i<orderlist.size();i++)    {
            nos = nos + orderlist.get(i).quantity;
            total = total + orderlist.get(i).quantity*orderlist.get(i).price;
        }
        orderAdapter = new OrdersAdapter(orderlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        orderRecyclerView.setLayoutManager(layoutManager);
        orderRecyclerView.setItemAnimator(new DefaultItemAnimator());
        orderRecyclerView.setAdapter(orderAdapter);
        orderQuantity.setText(""+nos);
        orderPrice.setText("Rs."+total);
    }



    String generateOrderId(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}