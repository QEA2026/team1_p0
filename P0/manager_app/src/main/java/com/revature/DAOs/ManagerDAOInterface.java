package com.revature.DAOs;

import java.util.ArrayList;

import com.revature.models.Manager;

public interface ManagerDAOInterface {

    //here we will lay out functionalities that ManagerDAO will implement

    //a method to select all employees
    ArrayList<Manager> getManagers();
    //Why Arraylist? Get all will return multiple employees.
    //So we need something that can store multiple Employee objects are once

    //A method to insert a new employee
    Manager insertManager(Manager emp);
    //if we're sending an Employee, why return one back?
    //just so the User can see what they've inserted. Think of it as a confirmation

    //TODO: delete a manager
    Manager deleteManager(Manager emp);
}