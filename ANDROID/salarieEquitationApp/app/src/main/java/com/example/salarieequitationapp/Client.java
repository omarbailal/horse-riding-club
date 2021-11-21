package com.example.salarieequitationapp;

import java.io.Serializable;

public class Client implements Serializable {
    int id;
    String fullName;
    public Client(){

    }

    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
