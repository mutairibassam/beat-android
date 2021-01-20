package com.datum.android.recyclerviewapp.newlayout;

import java.util.ArrayList;

public class Model {

    public Model() {
    }

    public Model(String name) {
        this.name = name;
//        this.email = email;
    }

    String name;
    String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public ArrayList<Model> getData() {
        ArrayList<Model> data = new ArrayList<>();

        data.add(new Model("Sunday"));
        data.add(new Model("Mon"));
        data.add(new Model("Tus"));
        data.add(new Model("Wedn"));
        data.add(new Model("Thr"));
        data.add(new Model("Fri"));
        data.add(new Model("Sat"));

        return data;
    }

}
