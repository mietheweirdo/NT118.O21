package com.example.thuchanh2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Bai4 extends AppCompatActivity {
    EditText edtName;
    CheckBox chbxManager;
    Button btnAdd;
    ListView lv_Employee;
    ArrayList<Employee4> employees;
    EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai4);

        edtName = (EditText) findViewById(R.id.edtName);
        chbxManager = (CheckBox) findViewById(R.id.chbxManager);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lv_Employee = (ListView) findViewById(R.id.lv_Employee);
        ArrayList<Employee4> employees = new ArrayList<>();

        adapter = new EmployeeAdapter(this, R.layout.item_employee,employees);
        lv_Employee.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                Employee4 employee = new Employee4();
                if (chbxManager.isChecked())
                {
                    employee.setManager(true);
                }
                else
                {
                    employee.setManager(false);
                }
                employee.setFullName(name);
                //Đưa employee vào ArrayList
                employees.add(employee);
                //Cập nhập giao diện
                adapter.notifyDataSetChanged();
            }
        });
    }
}