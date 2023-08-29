package com.calmat.keyaqua.Logic;

public class User {

    private String username;

    private String password;

    private String backupKey;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.backupKey = "";
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getBackupKey(){
        return this.backupKey;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setBackupKey(){
        this.backupKey = backupKey;
    }
}
