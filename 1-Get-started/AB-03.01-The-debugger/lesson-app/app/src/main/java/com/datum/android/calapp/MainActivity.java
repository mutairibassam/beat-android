
/*
 * Created by mutairibassam on 1/2/21 10:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 10:29 PM
 */

package com.datum.android.calapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView output, multiply, add, sub, div;
    int current_num;
    String operation = "";

    ArrayList<String> arr = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        multiply = findViewById(R.id.multiply_id);
        add = findViewById(R.id.add_id);
        sub = findViewById(R.id.subtract_id);
        div = findViewById(R.id.divison_id);

    }

    public void clearHighlight() {
        multiply.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.orange));
        div.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.orange));
        add.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.orange));
        sub.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.orange));
    }

    public void clear() {
        arr = new ArrayList<>();
        arr2 = new ArrayList<>();
        clearHighlight();
        operation = "";
    }

    public void clear(View view) {
        output.setText("0");
        arr = new ArrayList<>();
        arr2 = new ArrayList<>();
        clearHighlight();
        operation = "";
    }

    public void percentage(View view) {
        double result = current_num / 100.0;
        output.setText(String.valueOf(result));
    }

    public void multiply(View view) {
        operation = "*";
        multiply.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_orange));
    }

    public void subtract(View view) {
        operation = "-";
        sub.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_orange));
    }

    public void add(View view) {
        operation = "+";
        add.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_orange));
    }

    public void division(View view) {
        operation = "/";
        div.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_orange));
    }



    public void equal(View view) {

        if(arr.size() == 0 || arr2.size() == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        int size = arr.size();

        for(int i=0; i < size; i++) {
            sb.append(arr.get(i));
        }


        StringBuilder sb2 = new StringBuilder();
        int size2 = arr2.size();

        for(int i=0; i < size2; i++) {
            sb2.append(arr2.get(i));
        }


        int first = Integer.parseInt(String.valueOf(sb));
        int second = Integer.parseInt(String.valueOf(sb2));

        String total = "";
        if(operation.equals("+")) {
            total = String.valueOf(first + second);
        } else if(operation.equals("/")) {
            total = String.valueOf(first / second);
        } else if(operation.equals("-")) {
            total = String.valueOf(first - second);
        } else if(operation.equals("*")) {
            total = String.valueOf(first * second);
        }


        output.setText(total);
        clear();


    }

    public void appendValues(String num) {

        StringBuilder sb = new StringBuilder();

        if(operation.equals("")) {
            arr.add(num);
            int size = arr.size();

            for(int i = 0; i < size; i++) {
                sb.append(arr.get(i));
            }
        } else {
            arr2.add(num);
            int size = arr2.size();

            for(int i=0; i < size; i++) {
                sb.append(arr2.get(i));
            }
        }

        output.setText(sb.toString());

    }

    public void zero_clicked(View view) {
        current_num = 0;
        appendValues("0");

    }

    public void one_clicked(View view) {
        current_num = 1;
        appendValues("1");
    }

    public void two_clicked(View view) {
        current_num = 2;

        appendValues("2");
    }

    public void three_clicked(View view) {
        current_num = 3;

        appendValues("3");
    }

    public void four_clicked(View view) {
        current_num = 4;

        appendValues("4");
    }

    public void five_clicked(View view) {
        current_num = 5;

        appendValues("5");
    }

    public void six_clicked(View view) {
        current_num = 6;

        appendValues("6");
    }

    public void seven_clicked(View view) {
        current_num = 7;

        appendValues("7");
    }

    public void eight_clicked(View view) {
        current_num = 8;

        appendValues("8");
    }

    public void nine_clicked(View view) {
        current_num = 9;

        appendValues("9");
    }

    public void neg_pos_btn(View view) {

        int mCurrentNum = Integer.parseInt(output.getText().toString());

        if(mCurrentNum > 0) {

            int converted = - (mCurrentNum * 2) + mCurrentNum;
            output.setText(String.valueOf(converted));

        } else {

            int converted = - (mCurrentNum * 2) + mCurrentNum;
            output.setText(String.valueOf(converted));

        }

    }
}