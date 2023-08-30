package com.calmat.gitLocker.Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of keys
 */
public class KeyChain {

    private List<Key> keys;

    public KeyChain() {
        this.keys = new ArrayList<>();
    }

    public void addKey(Key key) {

        boolean keyExists = keys.stream()
                .anyMatch(existingKey -> existingKey.getName().equals(key.getName()));
        if (!keyExists) {
            keys.add(key);
        } else {
            System.out.println("A key with the same name already exists: " + key.getName());
        }
    }

    public void removeKey(Key key){
        if (keys.contains(key)){
            keys.remove(key);
        }
    }

    public List<Key> getKeys(){
        return keys;
    }

    public List<String> getKeyNames(){
        List<String> keyNames = new ArrayList<>();

        for (Key key : keys) {
            keyNames.add(key.getName());
        }
        return keyNames;
    }

    public Key retrieveKeyFromName(String name){
        for (Key key : keys) {
            if (key.getName().equals(name)) {
                return key;
            }
        }
        return null;
    }
}
