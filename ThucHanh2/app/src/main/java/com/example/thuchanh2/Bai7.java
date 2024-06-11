package com.example.thuchanh2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Bai7 extends AppCompatActivity {
    EditText edtName;
    CheckBox chbxManager;
    Button btnAdd;
    RecyclerView rv_Employee;
    ArrayList<Employee4> employees;
    Bai7Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai7);

        edtName = (EditText) findViewById(R.id.edtName);
        chbxManager = (CheckBox) findViewById(R.id.chbxManager);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        rv_Employee = (RecyclerView) findViewById(R.id.rv_Employee);
        employees = new ArrayList<>();
        adapter = new Bai7Adapter(this, R.layout.item_employee, employees);
        rv_Employee.setAdapter(adapter);
        rv_Employee.setHasFixedSize(true);
        rv_Employee.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Employee4 employee = new Employee4();
                employee.setFullName(name);
                if (chbxManager.isChecked())
                {
                    employee.setManager(true);
                }
                else
                {
                    employee.setManager(false);
                }
                //Đưa employee vào ArrayList
                employees.add(employee);
                //Cập nhập giao diện
                adapter.notifyDataSetChanged();
            }
        });
    }
}