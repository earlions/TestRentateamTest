package ru.onedr.earlzzz.testrentateam.recyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostPata {


    @SerializedName("data")
    @Expose
    private String data;
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
