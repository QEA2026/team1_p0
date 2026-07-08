package com.revature;

import java.util.ArrayList;

import com.revature.DAOs.AuthDAO;
import com.revature.DAOs.UserDAO;
import com.revature.controllers.AuthController;
import com.revature.controllers.ManagerController;
import com.revature.models.User;

import io.javalin.Javalin;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Launcher {
    public static void main(String[] args) {

        User e1 = new User(1, "jsmith", "password123", "manager");
        // System.out.println(e1);

        User e2 = new User(2, "ajones", "1234", "employee");
        User e3 = new User(3, "bsmith", "bob123", "employee");
        UserDAO eDAO = new UserDAO();

        eDAO.insertUser(e1);
        eDAO.insertUser(e2);
        eDAO.insertUser(e3);

        ArrayList<User> managers =  eDAO.getManagers();

        for(User e: managers){
            System.out.println(e);
        }

        ManagerController ec = new ManagerController();
        AuthController ac = new AuthController();

        AuthDAO ad = new AuthDAO();
        System.out.println(ad.login("jsmith","password123"));

        //Typical Javalin object creation syntax
        Javalin.create( config -> {
            config.routes.get("hello",ctx -> ctx.result("Hello World"));
            config.routes.post("/login", ac.loginHandler);
            config.routes.get("/employees",ec.getManagersHandler);
            config.routes.post("/employees",ec.insertEmployee);

        }).start(3000);





    }

    
}