package com.utsavbucky.onebanc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utsavbucky.onebanc.R;
import com.utsavbucky.onebanc.models.Dishes;
import com.utsavbucky.onebanc.models.Orders;

import java.util.List;

public class PreviousOrdersAdapter extends RecyclerView.Adapter<PreviousOrdersAdapter.MyViewHolder> {
    private List<Orders> ordersList;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, orderId, itemTotal;
        List<Dishes> dishList;
        MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.order_date);
            orderId = view.findViewById(R.id.order_id);
            itemTotal = view.findViewById(R.id.order_price);
        }
    }
    public PreviousOrdersAdapter(List<Orders> ordersList)
    {
        this.ordersList = ordersList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.previous_order_item, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Orders order = ordersList.get(position);

        holder.date.setText(order.orderDate);
        holder.itemTotal.setText("Rs."+String.valueOf(order.orderPrice));
        holder.orderId.setText("Order id: #"+order.orderId);

    }
    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}