package com.calmat.keyaqua.Logic;

public class User {

    private String username;

    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(){
        this.username = username;
    }

    public void setPassword(){
        this.password = password;
    }
}
