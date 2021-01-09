package com.datum.android.recyclerviewapp.model;

public class MyCustomAPI {

    public MyCustomAPI() {
    }

    private String name;
    private String email;


    public MyCustomAPI(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
