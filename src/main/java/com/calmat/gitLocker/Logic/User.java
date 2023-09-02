package com.calmat.gitLocker.Logic;

/**
 * The type User.
 */
public class User {

    private String username;

    private String password;

    private String backupKey;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.backupKey = "";
    }

    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Get password string.
     *
     * @return the string
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Get backup key string.
     *
     * @return the string
     */
    public String getBackupKey(){
        return this.backupKey;
    }

    /**
     * Set username.
     *
     * @param username the username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Set password.
     *
     * @param password the password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Set backup key.
     */
    public void setBackupKey(){
        this.backupKey = backupKey;
    }
}
