package com.example.nguyenquanganh_ktra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nguyenquanganh_ktra2_bai2.model.Customer;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.CustomerHolder> {

    ArrayList<Customer> list = new ArrayList<>();
    Context context;

    public void setList(ArrayList<Customer> list) {
        this.list = list;
    }

    public RecyclerviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer, null);
        return new CustomerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHolder holder, int position) {
        Customer customer = list.get(position);
        holder.txtIdName.setText(customer.getName() + " - " + customer.getId());
        holder.txtDate.setText(customer.getDate());
        holder.txtDeparture.setText(customer.getDeparture());
        if(customer.isLuggage()) {
            holder.txtCheck.setText("Có");
        } else {
            holder.txtCheck.setText("Không");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("update_customer", customer);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomerHolder extends RecyclerView.ViewHolder {
        TextView txtIdName, txtDate, txtDeparture, txtCheck;
        public CustomerHolder(@NonNull View itemView) {
            super(itemView);
            txtIdName = itemView.findViewById(R.id.txt_idname);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtDeparture = itemView.findViewById(R.id.txt_departure);
            txtCheck = itemView.findViewById(R.id.txt_check);
        }
    }
}
