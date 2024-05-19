/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks.model;

/**
 *
 * @author DELL
 */
public class User {
    private int id;
    private String user_name;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", user_name=" + user_name + ", gender=" + gender + '}';
    }
    private String password;
    private String  gender;

    public User(int id, String user_name, String password, String gender) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
