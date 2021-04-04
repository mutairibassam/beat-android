package com.datum.android.pagingapp.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "issue_table")
public class Issue {

    public void setNumber(String numbers) {
        this.number = numbers;
    }

    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String number;

    public Issue() {

    }

    public Issue(String reporter) {
        this.number = reporter;
    }

    public static ArrayList<Issue> getNumbers() {
        ArrayList<Issue> reporter = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            reporter.add(new Issue(i +""));
        }

        return reporter;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
