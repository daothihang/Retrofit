package com.daothihang.realmretrofit.model;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class User  implements Serializable{
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("url")
    private String url;

    public User(String login, int id, String avatar_url,String url) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.url=url;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
