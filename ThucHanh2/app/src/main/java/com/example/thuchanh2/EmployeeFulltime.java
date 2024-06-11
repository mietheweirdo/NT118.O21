package com.example.thuchanh2;

public class EmployeeFulltime extends Employee {
    @Override
    public double tinhLuong() {return 500;}
    @Override
    public String toString() {return super.toString() + "--> FullTime = "+ tinhLuong();}
}
