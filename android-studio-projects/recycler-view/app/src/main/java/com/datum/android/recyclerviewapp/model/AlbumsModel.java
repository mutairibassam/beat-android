package com.datum.android.recyclerviewapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumsModel {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public AlbumsModel() {
    }

    /**
     *
     * @param id
     * @param title
     * @param userId
     */
    public AlbumsModel(Integer userId, Integer id, String title) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
