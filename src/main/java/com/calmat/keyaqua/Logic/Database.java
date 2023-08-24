package com.calmat.keyaqua.Logic;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Database {

    private String fileName;

    private String path = "data/userData/";



    public void writeKeyToFile(Key key) throws IOException {
        FileWriter writer = null;
        String nameString = key.getName();
        String keyString = key.getKey();

        try {
            writer = new FileWriter("data/userData/" + getActiveUser() + ".dat", true);

            writer.write(nameString + " --- " + keyString + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }



    public String getActiveUser(){
        File activeUserFile = new File("data/activeUser.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(activeUserFile))) {
            String user = reader.readLine();
            System.out.println(user);
            return user;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setActiveUser(String userName) throws IOException{
        FileWriter writer = null;
        writer = new FileWriter("data/activeUser.dat");
        writer.write(userName);
        writer.close();
    }

    public boolean checkUser(User user){

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the name to be deleted
                if (line.contains(user.getUsername() + " --- " + user.getPassword())) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void deleteKey(Key keyToDelete) throws IOException {
        // Read the file and store its lines in a list
        String nameToDelete = keyToDelete.getName();
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/userData/"+getActiveUser() + ".dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the name to be deleted
                if (!line.contains(nameToDelete + " --- ")) {
                    lines.add(line);
                }
            }
        }

        // Write the filtered lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/userData/"+getActiveUser() + ".dat"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public KeyChain loadKeys() {
        KeyChain keyChain = new KeyChain();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/userData/" + getActiveUser()+".dat"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(" --- ");
                Key tempKey = new Key(fields[0], fields[1]);
                keyChain.addKey(tempKey);
                // Reads next line of text from an input source
                line = reader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return keyChain;
    }



}
