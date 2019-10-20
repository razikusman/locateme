package com.example.locateme;

public class User {
    private String Name;
    private String Email;
    private String Password;

    public User(){

    }

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMail() {
        return Email;
    }

    public void setMail(String mail) {
        this.Email = mail;
    }

    public String getPaswd() {
        return Password;
    }

    public void setPaswd(String paswd) {
        Password = paswd;
    }

}
