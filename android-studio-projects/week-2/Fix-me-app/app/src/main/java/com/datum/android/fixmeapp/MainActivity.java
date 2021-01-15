package com.datum.android.fixmeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView leftCornerTop, leftMiddle, leftCornerBottom;
    TextView topCenter, centerCenter, bottomCenter;
    TextView rightCornerTop, rightMiddle, rightCornerBottom;

    String one = "";
    String two = "";
    String three = "";
    String four = "";
    String five = "";
    String six = "";
    String seven = "";
    String eight = "";
    String nine = "";

    String x = "X";
    String o = "O";

    boolean isX = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInit();

    }

    private void check() {

        if(one.equals("X") && two.equals("X") && three.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(one.equals("O") && two.equals("O") && three.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(one.equals("X") && four.equals("X") && seven.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(one.equals("O") && four.equals("O") && seven.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(one.equals("X") && five.equals("X") && nine.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(one.equals("O") && five.equals("O") && nine.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(two.equals("X") && five.equals("X") && eight.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(two.equals("O") && five.equals("O") && eight.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(three.equals("X") && five.equals("X") && seven.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(three.equals("O") && five.equals("O") && seven.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(three.equals("X") && six.equals("X") && nine.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(three.equals("O") && six.equals("O") && nine.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(four.equals("X") && five.equals("X") && six.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(four.equals("O") && five.equals("O") && six.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
            return;
        }

        if(seven.equals("X") && eight.equals("X") && nine.equals("X")) {
            Util.showAlert(MainActivity.this, "X");
            newGame();
            return;
        }

        if(seven.equals("O") && eight.equals("O") && nine.equals("O")) {
            Util.showAlert(MainActivity.this, "O");
            newGame();
        }
    }


    private void changePlayer() {
        if(isX) isX = false;
        else isX = true;
    }
    public void getInit() {
        leftCornerTop = findViewById(R.id.leftCornerTop);
        leftMiddle = findViewById(R.id.leftMiddle);
        leftCornerBottom = findViewById(R.id.leftCornerBottom);

        topCenter = findViewById(R.id.centerTop);
        centerCenter = findViewById(R.id.centerCenter);
        bottomCenter = findViewById(R.id.bottomCenter);

        rightCornerTop = findViewById(R.id.rightCornerTop);
        rightMiddle = findViewById(R.id.rightMiddle);
        rightCornerBottom = findViewById(R.id.rightCornerBottom);
    }

    public void leftCornerTop(View view) {
        if(leftCornerTop.getText().toString().isEmpty()) {

            if(isX) one = "X";
            else one = "O";

            leftCornerTop.setText(one);
            changePlayer();
            check();
        }
    }

    public void centerTop(View view) {
        if(topCenter.getText().toString().isEmpty()) {

            if(isX) two = "X";
            else two = "O";

            topCenter.setText(two);
            changePlayer();
            check();
        }
    }

    public void rightCornerTop(View view) {
        if(rightCornerTop.getText().toString().isEmpty()) {

            if(isX) three = "X";
            else three = "O";

            rightCornerTop.setText(three);
            changePlayer();
            check();
        }
    }

    public void leftMiddle(View view) {
        if(leftMiddle.getText().toString().isEmpty()) {

            if(isX) four = "X";
            else four = "O";

            leftMiddle.setText(four);
            changePlayer();
            check();

        }
    }

    public void centerCenter(View view) {
        if(centerCenter.getText().toString().isEmpty()) {
            if(isX) {
                centerCenter.setText(x);
                isX = false;
            }
            else {
                centerCenter.setText(o);
                isX = true;
            }
            five = centerCenter.getText().toString();
            check();

        }
    }

    public void rightMiddle(View view) {
        if(rightMiddle.getText().toString().isEmpty()) {
            if(isX) {
                rightMiddle.setText(x);
                isX = false;
            }
            else {
                rightMiddle.setText(o);
                isX = true;
            }
            six = rightMiddle.getText().toString();
            check();

        }
    }

    public void leftCornerBottom(View view) {
        if(leftCornerBottom.getText().toString().isEmpty()) {
            if(isX) {
                leftCornerBottom.setText(x);
                isX = false;
            }
            else {
                leftCornerBottom.setText(o);
                isX = true;
            }
            seven = leftCornerBottom.getText().toString();
            check();

        }
    }

    public void bottomCenter(View view) {
        if(bottomCenter.getText().toString().isEmpty()) {
            if(isX) {
                bottomCenter.setText(x);
                isX = false;
            }
            else {
                bottomCenter.setText(o);
                isX = true;
            }

            eight = bottomCenter.getText().toString();
            check();
        }
    }

    public void rightCornerBottom(View view) {
        if(rightCornerBottom.getText().toString().isEmpty()) {
            if(isX) {
                rightCornerBottom.setText(x);
                isX = false;
            }
            else {
                rightCornerBottom.setText(o);
                isX = true;
            }
            nine = rightCornerBottom.getText().toString();
            check();

        }
    }

    public void newGame() {
        leftCornerTop.setText("");
        leftMiddle.setText("");
        leftCornerBottom.setText("");
        topCenter.setText("");
        centerCenter.setText("");
        bottomCenter.setText("");
        rightCornerTop.setText("");
        rightMiddle.setText("");
        rightCornerBottom.setText("");
        one = "";
        two = "";
        three = "";
        four = "";
        five = "";
        six = "";
        seven = "";
        eight = "";
        nine = "";
        isX = true;
    }
}