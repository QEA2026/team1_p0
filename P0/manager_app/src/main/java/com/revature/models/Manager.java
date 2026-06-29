package com.revature.models;

public class Manager {

    
    private String first_name;
    private String last_name; 

    private int user_id;
    private String username;
    private String password;
    // private Role role;
    private String role = "manager";
    private int role_id_fk;

    public Manager()
    {

    }

    public Manager(String username, String password)
    {
        this.username = username;
        this.password = password;

    }

    public Manager(int user_id, String username, String password)
    {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }/* 
     public Manager(String username, String password, int role_id_fk) {
        this.username = username;
        this.password = password;
        this.role_id_fk = role_id_fk;
    }
        */

     public Manager(String first_name, String last_name, int user_id, String username, String password)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public int getManager_id()
    {
        return this.user_id;
    }

    public void setManager_id(int new_id)
    {
        this.user_id = new_id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String new_username)
    {
        this.username = new_username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String new_pass)
    {
        this.password = new_pass;
    }

    public String getFirstName()
    {
        return this.first_name;
    }

    public void setName(String first_name, String last_name)
    {
        this.first_name = first_name;
        this.last_name = last_name; 
    }


    @Override
    public String toString()
    {
        return "Employee{" +
                "role=" + role +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", employee_id=" + user_id +
                '}';
    }
    
}
