package com.example.lab3_b4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students;
    private EditText mssv, hoten, lop;

    public StudentAdapter(Context context, ArrayList<Student> students, EditText mssv, EditText hoten, EditText lop) {
        this.context = context;
        this.students = students;
        this.mssv = mssv;
        this.hoten = hoten;
        this.lop = lop;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = (Student) getItem(position);
        viewHolder.textViewMSSV.setText(student.getMssv());
        viewHolder.textViewHoTen.setText(student.getHoTen());
        viewHolder.textViewLop.setText(student.getLop());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mssv.setText(student.getMssv());
                hoten.setText(student.getHoTen());
                lop.setText(student.getLop());
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private TextView textViewMSSV;
        private TextView textViewHoTen;
        private TextView textViewLop;

        public ViewHolder(View itemView) {
            textViewMSSV = itemView.findViewById(R.id.textViewMSSV);
            textViewHoTen = itemView.findViewById(R.id.textViewHoTen);
            textViewLop = itemView.findViewById(R.id.textViewLop);
        }
    }
}
