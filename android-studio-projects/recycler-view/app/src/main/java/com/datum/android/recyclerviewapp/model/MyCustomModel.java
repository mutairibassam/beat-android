package com.datum.android.recyclerviewapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCustomModel implements Parcelable {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<MyCustomModel> CREATOR = new Creator<MyCustomModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MyCustomModel createFromParcel(Parcel in) {
            return new MyCustomModel(in);
        }

        public MyCustomModel[] newArray(int size) {
            return (new MyCustomModel[size]);
        }

    }
            ;

    protected MyCustomModel(Parcel in) {
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public MyCustomModel() {
    }

    /**
     *
     * @param city
     * @param name
     */
    public MyCustomModel(String city, String name) {
        super();
        this.city = city;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }


//    public MyCustomModel() {
//    }
//
//    String city;
//    String name;
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public MyCustomModel(String city, String name) {
//        this.city = city;
//        this.name = name;
//    }
}
