package com.calmat.keyaqua.Logic;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String fileName;

    private String path = "data/userData/";



    public void writeKeyToFile(Key key) throws IOException {
        try (FileWriter writer = new FileWriter("data/userData/" + getActiveUserAsString() + ".dat", true)) {

            writer.write(key.getName() + " --- " + key.getKey() + "\n");
        }
    }

    public void writeUserToFile(User user) throws IOException {
        try (FileWriter writer = new FileWriter("data/users.dat", true)) {
            writer.write(user.getUsername() + " --- " + user.getPassword() + "\n");
        }
        createUserFile(user.getUsername());
    }

    private void createUserFile(String username) {
        try {
            (new File("data/userData/" + username + ".dat")).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getActiveUserAsString(){
        File activeUserFile = new File("data/activeUser.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(activeUserFile))) {
            String user = reader.readLine();
            return user;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getActiveUser(){
        File activeUserFile = new File("data/activeUser.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(activeUserFile))) {
            String user = reader.readLine();

            BufferedReader reader2 = new BufferedReader(new FileReader("data/users.dat"));
            String line;
            while ((line = reader2.readLine()) != null) {
                if (line.startsWith(user)) {
                    String[] fields = line.split(" --- ");
                    return new User(fields[0], fields[1]);
                }
            }
            reader2.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
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
        try (BufferedReader reader = new BufferedReader(new FileReader("data/userData/"+getActiveUserAsString() + ".dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the name to be deleted
                if (!line.contains(nameToDelete + " --- ")) {
                    lines.add(line);
                }
            }
        }

        // Write the filtered lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/userData/"+getActiveUserAsString() + ".dat"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public KeyChain loadKeys() {
        KeyChain keyChain = new KeyChain();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/userData/" + getActiveUserAsString()+".dat"))) {
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


    public static String generatePassword() {
        // Define the characters allowed in the password
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+=<>?";

        // Create a SecureRandom object to generate random numbers
        SecureRandom random = new SecureRandom();

        // Initialize a StringBuilder to build the password
        StringBuilder password = new StringBuilder(20);

        // Generate 10 random characters from the allowedChars string
        for (int i = 0; i < 20; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public void addBackupKey(String user, String bk) throws IOException {
        // Define the file path
        String filePath = "data/users.dat";

        // Read the existing lines from the file and update the matching line
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder updatedContent = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith(user)) {
                // Append the "bk" value to the end of the matching line
                line += " --- " + bk;
            }
            updatedContent.append(line).append(System.lineSeparator());
        }

        reader.close();

        // Write the updated content back to the file
        FileWriter writer = new FileWriter(filePath);
        writer.write(updatedContent.toString());
        writer.close();
    }

    public void writeChangesToFile(User user) throws IOException{
        deleteUserFromFile(user);
        writeUserToFile(user);
    }

    public void deleteUserFromFile(User user) throws IOException{
        String filePath = "data/users.dat";

        // Read the existing lines from the file and exclude the line starting with the specified user
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<String> updatedLines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.startsWith(user.getUsername())) {
                updatedLines.add(line);
            }
        }
        reader.close();

        // Write the updated content (excluding the line with the specified user) back to the file
        FileWriter writer = new FileWriter(filePath);
        for (String updatedLine : updatedLines) {
            writer.write(updatedLine + System.lineSeparator());
        }
        writer.close();
    }

    public String getBackupKey(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/users.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(user.getUsername())) {
                    String[] fields = line.split(" --- ");
                    return fields[2];
                }
            }
        } catch (
                ArrayIndexOutOfBoundsException e) {
            return "";
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public boolean checkIfKeyExists() {
        return !getBackupKey(getActiveUser()).isEmpty();
    }

    public void writePasswordToFile(String newPassword) {
        User user = getActiveUser();
        user.setPassword(newPassword);
        try {
            writeChangesToFile(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
