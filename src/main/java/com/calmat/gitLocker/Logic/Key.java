package com.calmat.gitLocker.Logic;

/**
 * Represents a key... l8r
 */

public class Key {

    private String name;
    private String key;

    public Key(String name, String key){
        this.name = name;
        this.key = key;
    }

    public String getName(){
        return this.name;
    }

    public String getKey(){
        return this.key;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setKey(String key){
        this.key = name;
    }


    public static class KeyBuilder {
        private String name;
        private String key;

        /**
         * Creates a new instance of the KeyBuilder class.
         */
        public KeyBuilder() {
            this.name = "";
            this.key = "";
        }

        /**
         * Sets the name of the player.
         *
         * @param name Player name.
         * @return The player name.
         */
        public KeyBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the image of the player.
         *
         * @param key Player image.
         * @return The player image.
         */
        public KeyBuilder key(String key) {
            this.key = key;
            return this;
        }


        /**
         * Builds the player.
         *
         * @return The player.
         */
        public Key build() {
            return new Key(name, key);
        }

    }
}
