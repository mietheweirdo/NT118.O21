package com.example.thuchanh2;

public class Employee4 {
    private String Id;
    private String FullName;
    private boolean isManager;

    public Employee4(String id, String fullName, boolean isManager) {
        Id = id;
        FullName = fullName;
        this.isManager = isManager;
    }

    public Employee4() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}