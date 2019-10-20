package com.example.locateme;

public class Service {
    private String name;
    private String Email;
    private String Password;

    public Service(){

    }

    public Service(String name, String email, String password) {
        this.name = name;
        Email = email;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
