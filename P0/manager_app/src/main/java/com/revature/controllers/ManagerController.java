package com.revature.controllers;

import java.util.ArrayList;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

/*
The controller layer is where HTTP requests get sent after Javalin directs them
It's in this layer that JSON comes in and gets translated to Java and vice versa
We'll either be getting data from the Service or DAO to get an HTTP Response to the
front end (select) OR we'll be receiving data from the front end to send to the database (insert, update,
delete)
*/
public class ManagerController {

    UserDAO eDAO = new UserDAO();

    public Handler getManagersHandler = (ctx) -> {
        /*
        What's ctx?? The context object! This object contains methods that we can use to process HTTP requests
        and send HTTP responses. Here, we are giving it a variable called "ctx" so that we can access its methods*/

        //if the Session is not null, we know the user is logged in.
        //Thus, we can allow them to view employees
        if (AuthController.ses != null) {

            //this is just showing you how to retrieve saved session attributes
            //realistically, you'de be putting these values into your DAOs
            System.out.println(AuthController.ses.getAttribute("employee_id"));

            //We need an ArrayList of Employees, courtesy of our EmployeeDAO
            ArrayList<User> managers = eDAO.getManagers();

            //use the .json() method to turn our Java into a JSON string
            ctx.json(managers);

            //we can set the status code with ctx.status()
            ctx.status(HttpStatus.OK);
            //200 is the defualt "OK" message


        } else { //if the user is NOT logged in
            ctx.result("YOU MUST LOG IN TO DO THIS");
            ctx.status(HttpStatus.UNAUTHORIZED);
        }
    };


    //This Handler will get the HTTP Post Request for inserting a new employee to the DB.
    public Handler insertEmployee = (ctx -> {

        //we have JSON data coming in, which we can convert to Java with ctx.bodyAsClass();
        //what's body?? it refers to the HTTP Request body (the data sent in the HTTP Request)
        User newEmp = ctx.bodyAsClass(User.class); //we now have a Java String holding a JSON String

        /* we're calling the insert employees method from the EmployeeDAO
        * if it's successful, we'll send the new employee back in the response with a 201 status code
        * if it fails, we'll send an error message and a 406 status code*/
        User returnedEmp = eDAO.insertUser(newEmp);

        //if insert was successful, returnedEmp will hold an Employee object (not null)
        if(returnedEmp != null){
            ctx.status(201); //201 "created"
            ctx.json(returnedEmp); //send back the employee
        } else {
            ctx.status(406); //406 "not acceptable"
            ctx.result("Insert manager failed");
        }
    });

    public Handler getPending = (ctx -> {
        

    });
}