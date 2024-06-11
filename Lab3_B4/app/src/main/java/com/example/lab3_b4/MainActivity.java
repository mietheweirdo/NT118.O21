package com.example.lab3_b4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    String DATABASE_NAME = "qlsv.db";
    //Khai báo ListView
    ListView lv;
    ArrayList<Student> mylist;
    StudentAdapter myadapter;
    private EditText editTextName;
    private EditText editTextLop;
    private EditText editTextMSSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.hoten);
        editTextLop = findViewById(R.id.lop);
        editTextMSSV = findViewById(R.id.mssv);

        Button buttonAdd = findViewById(R.id.insert);
        Button buttonDelete = findViewById(R.id.delete);
        Button buttonUpdate = findViewById(R.id.update);
        Button buttonQuery = findViewById(R.id.query);

        //Ham Copy CSDL từ assets vào thư mục Databases
        processCopy();
        database = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
        // Tạo ListView
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new StudentAdapter(MainActivity.this, mylist, editTextMSSV, editTextName, editTextLop);
        lv.setAdapter(myadapter);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                removeAllDataListView();
                queryAllData();
                clearFillIn();
                myadapter.notifyDataSetChanged();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                removeAllDataListView();
                queryAllData();
                clearFillIn();
                myadapter.notifyDataSetChanged();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
                removeAllDataListView();
                queryAllData();
                clearFillIn();
                myadapter.notifyDataSetChanged();
            }
        });

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryData();
                clearFillIn();
                myadapter.notifyDataSetChanged();
            }
        });
        // Truy vấn CSDL và cập nhật hiển thị lên Listview
        Cursor c = database.query("qlsv", null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Student student = new Student(c.getString(0), c.getString(2), c.getString(1));
            mylist.add(student);
            c.moveToNext();
        }
        c.close();
        myadapter.notifyDataSetChanged();
    }

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    // Ham copy file DB tu thu muc Asset vao file DB moi tao ra trong ung dung
    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            // Kiem tra neu duong dan khong co, thi tao moi file
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) f.mkdir();
            // Mo empty db su dung output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // Sao chep du lieu bytes tu input toi ouput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        if (!editTextMSSV.getText().toString().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("MSSV", editTextMSSV.getText().toString());
            values.put("Lop", editTextLop.getText().toString());
            values.put("HoTen", editTextName.getText().toString());
            database.insert("qlsv", null, values);
        }
    }

    public void updateData() {
        if (!editTextMSSV.getText().toString().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("Lop", editTextLop.getText().toString());
            values.put("HoTen", editTextName.getText().toString());
            database.update("qlsv", values, "MSSV=?", new String[]{editTextMSSV.getText().toString()});
        }
    }

    public void deleteData() {
        String whereClause = "";
        if (!editTextMSSV.getText().toString().equals("")) {
            whereClause = "MSSV='" + editTextMSSV.getText().toString() + "'";
        }
        if (!editTextLop.getText().toString().equals("")) {
            if (!whereClause.isEmpty()) whereClause += " AND ";
            whereClause += "Lop='" + editTextLop.getText().toString() + "'";
        }
        if (!editTextName.getText().toString().equals("")) {
            if (!whereClause.isEmpty()) whereClause += " AND ";
            whereClause += "HoTen='" + editTextName.getText().toString() + "'";
        }
        if (whereClause.isEmpty()) {
            whereClause = null;
        }
        database.delete("qlsv", whereClause, null);
    }

    public void queryData() {
        String whereClause = "";
        if (!editTextMSSV.getText().toString().equals("")) {
            whereClause = "MSSV='" + editTextMSSV.getText().toString() + "'";
        }
        if (!editTextLop.getText().toString().equals("")) {
            if (!whereClause.isEmpty()) whereClause += " AND ";
            whereClause += "Lop='" + editTextLop.getText().toString() + "'";
        }
        if (!editTextName.getText().toString().equals("")) {
            if (!whereClause.isEmpty()) whereClause += " AND ";
            whereClause += "HoTen='" + editTextName.getText().toString() + "'";
        }
        if (whereClause.isEmpty()) {
            whereClause = null;
        }

        removeAllDataListView();

        Cursor c = database.query("qlsv", new String[]{"MSSV", "Lop", "HoTen"}, whereClause, null, null, null, null);
        while (c.moveToNext()) {
            Student student = new Student(c.getString(0), c.getString(2), c.getString(1));
            mylist.add(student);
        }
        c.close();
    }

    public void queryAllData() {
        Cursor c = database.query("qlsv", new String[]{"MSSV", "Lop", "HoTen"}, null, null, null, null, null);
        while (c.moveToNext()) {
            Student student = new Student(c.getString(0), c.getString(2), c.getString(1));
            mylist.add(student);
        }
        c.close();
    }

    public void clearFillIn() {
        editTextMSSV.setText("");
        editTextLop.setText("");
        editTextName.setText("");
    }

    public void removeAllDataListView() {
        mylist.clear();
    }
}
