package com.datum.android.recyclerviewapp.oldlayout.model;

import java.util.ArrayList;

public class MyCustomTable {

    public MyCustomTable() {
    }

    public MyCustomTable(String name, String email) {
        this.name = name;
        this.email = email;
    }

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<MyCustomTable> getData() {

        ArrayList<MyCustomTable> data = new ArrayList<>();

        MyCustomTable myCustomTable = new MyCustomTable("bassam", "bassam@gmail.com");
        data.add(myCustomTable);

        MyCustomTable myCustomTable2 = new MyCustomTable("mohammad", "mohammad@gmail.com");
        data.add(myCustomTable2);

        return data;

    }

}







