package com.urjcstarshipbazaar.models;

public class Admin extends User {

    public Admin(int id, String name, String nickname, String email) {
        super(id, name, nickname, email);
    }

    public Admin(String name, String nickname, String email) {
        super(name, nickname, email);
    }

    public Admin() {
        super();
    }

}
