package com.revature.models;

import java.util.Objects;

public class User {

    private String first;
    private String last;
    private String username;
    private String password;

    public User(String first, String last, String username, String password) {
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
    }

    public User(){
    }

    public String getFirst() {return first;}

    public void setFirst(String first) {this.first = first;}

    public String getLast() {return last;}

    public void setLast(String last) {this.last = last;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "Users{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(first, user.first) && Objects.equals(last, user.last) && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, username, password);
    }
}
