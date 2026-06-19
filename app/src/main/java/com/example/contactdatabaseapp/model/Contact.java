package com.example.contactdatabaseapp.model;

public class Contact {

    private int id;
    private String name;
    private String phone;
    private String email;
    private int avatar;

    public Contact(
            int id,
            String name,
            String phone,
            String email,
            int avatar
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}