package com.example.app.models;

import com.example.app.utils.SHA256Hash;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts") // Specifies MongoDB collection name
public class Account {

    public enum Role {
        ADMIN, USER
    }

    @Id // Marks this as the primary key in MongoDB
    private String id; // MongoDB uses String as ID (ObjectId)
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String salt = SHA256Hash.generateSalt();;
    private Role role;
    private Long lastLogout;

    public Account() {
    }

    public Account(String username, String firstname, String lastname, String email, String password, String salt,
            Role role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public Long getLastLogout() {
        return lastLogout;
    }

    public Role getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLastLogout(Long lastLogout) {
        this.lastLogout = lastLogout;
    }

}