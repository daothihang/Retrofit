package com.daothihang.realmretrofit.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Users extends RealmObject {
    private int id;
    private String login;
    private String avatar_url;
    private String url;

    public Users() {

    }

    public Users(String login, int id, String avatar_url, String url) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Users{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
