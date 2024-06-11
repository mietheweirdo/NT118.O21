package com.example.thuchanh2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Bai7Adapter extends RecyclerView.Adapter<Bai7Adapter.EmployeeViewHolder> {
    private List<Employee4> employees;
    private Activity context;
    private int layoutID;
    public Bai7Adapter(Activity context, int layoutID, List<Employee4> employees) {
        this.context = context;
        this.layoutID = layoutID;
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee4 employee = employees.get(position);
        if (employee.getFullName() != null) {
            holder.tvFullName.setText(employee.getFullName());
        } else holder.tvFullName.setText("");
        if (employee.isManager()) {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
        } else {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText("Staff");
        }
        if (position % 2 == 0) {
            holder.llParent.setBackgroundResource(R.color.white);
        } else {
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        if (employees == null) {
            return 0;
        }
        return employees.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullName;
        TextView tvPosition;
        ImageView ivManager;
        LinearLayout llParent;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.item_employee_tv_fullname);
            tvPosition = itemView.findViewById(R.id.item_employee_tv_position);
            ivManager = itemView.findViewById(R.id.item_employee_iv_manager);
            llParent = itemView.findViewById(R.id.item_employee_ll_parent);
        }
    }
}
