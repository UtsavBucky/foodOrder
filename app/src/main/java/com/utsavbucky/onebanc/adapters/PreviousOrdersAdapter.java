package com.utsavbucky.onebanc.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.utsavbucky.onebanc.MainActivity;
import com.utsavbucky.onebanc.R;
import com.utsavbucky.onebanc.fragments.OrderConfirmationDialog;
import com.utsavbucky.onebanc.fragments.OrderDishesListDialog;
import com.utsavbucky.onebanc.models.Dishes;
import com.utsavbucky.onebanc.models.Orders;

import java.util.List;

public class PreviousOrdersAdapter extends RecyclerView.Adapter<PreviousOrdersAdapter.MyViewHolder> {
    private List<Orders> ordersList;Context mContext;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, orderId, itemTotal;
        List<Dishes> dishList;
        LinearLayout parentLayout;

        MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.order_date);
            orderId = view.findViewById(R.id.order_id);
            itemTotal = view.findViewById(R.id.order_price);
            parentLayout = view.findViewById(R.id.parent_layout);
        }
    }
    public PreviousOrdersAdapter(Context context,List<Orders> ordersList)
    {
        this.ordersList = ordersList;
        this.mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_item, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Orders order = ordersList.get(position);

        holder.date.setText(order.orderDate);
        holder.itemTotal.setText("Rs."+String.valueOf(order.orderPrice));
        holder.orderId.setText("Order id: #"+order.orderId);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment orderDialog = new OrderDishesListDialog();
                Bundle args = new Bundle();
                Gson gson = new Gson();
                args.putString("selected_order",gson.toJson(order));
                orderDialog.setArguments(args);
                orderDialog.show(((MainActivity)mContext).getSupportFragmentManager(), orderDialog.getTag());
                orderDialog.setCancelable(true);
            }
        });

    }
    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}