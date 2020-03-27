package es.code.urjc.BiblioBookingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User {


    private long id;

    private String name;
    private String lastname;
    private String email;

    private String username;
    private String passwordHash;
    private boolean blocked;

    private List<String> roles;

    public User(){

    }

    public User(String name, String lastname, String username, String email, String password, String... role){
        this.name=name;
        this.lastname = lastname;
        this.email=email;
        this.username = username;
        this.passwordHash = password;
        this.roles = new ArrayList<>(Arrays.asList(role));
    }

    public User(String name, String lastname, String username, String email, String password, boolean blocked, String... role){
        this.name=name;
        this.lastname = lastname;
        this.email=email;
        this.username = username;
        this.passwordHash = password;
        this.roles = new ArrayList<>(Arrays.asList(role));
        this.blocked = blocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
