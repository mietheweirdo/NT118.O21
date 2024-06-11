package com.example.lab3_b3;

public class Student {
    private String mssv;
    private String hoTen;
    private String lop;
    public Student(String mssv, String hoTen, String lop) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
