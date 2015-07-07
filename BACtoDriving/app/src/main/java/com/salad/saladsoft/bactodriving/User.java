package com.salad.saladsoft.bactodriving;

public class User {

    String name, username, password;
    int weight;

    public User(String name, int weight, String username, String password) {
        this.name = name;
        this.weight = weight;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this("", -1, username, password);
    }
}