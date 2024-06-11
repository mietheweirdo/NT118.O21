package com.example.thuchanh2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Bai2 extends AppCompatActivity {
    private ListView lvPerson;
    private TextView tvSelection;
    private Button btnSubmit;
    private EditText etEnterName;
    private ArrayList<String> names;

    private void initUi() {
        lvPerson = (ListView) findViewById(R.id.lv_person);
        tvSelection = (TextView) findViewById(R.id.tv_selection);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etEnterName = (EditText) findViewById(R.id.etEnterName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2);
        initUi();
        names = new ArrayList<String>();
        names.add("tý");
        names.add("tèo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Thêm dữ liệu mới vào arraylist
                String name = etEnterName.getText().toString();
                names.add(name);
                //Cập nhật dữ liệu mới lên giao diên
                adapter.notifyDataSetChanged();
            }
        });

        //5. Xử lý sự kiện chọn một phần tử trong ListView
        lvPerson.setAdapter(adapter);
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //đối số arg2 là vị trí phần tử trong Data Source (arr)
                tvSelection.setText("position :" + arg2 + "; value =" + names.get(arg2));
            }
        });

        //6. xử lý sự kiện Long click
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                names.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
