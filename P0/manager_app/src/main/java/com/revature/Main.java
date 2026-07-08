package com.revature;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.DAOs.ApprovalDAO;
import com.revature.DAOs.AuthDAO;
import com.revature.DAOs.ExpenseDAO;
import com.revature.models.Approval;
import com.revature.models.Expense;
import com.revature.models.User;


public class Main {
    public static void main(String[] args) {

        User e1 = new User(1, "jsmith", "password123", "manager");
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);



        // Create console menu 
        while (true) 
        {
            // Generate menu for Manager App
            System.out.println("Welcome to the Employee Management System");
            
            System.out.println("Select from the following choices:");
            System.out.println("1: Login");
            System.out.println("2: Exit");

            int choice = scanner.nextInt();

            switch (choice)
            {
                // login 
                case 1:
                    System.out.println("Enter your username: ");
                    String username = scanner.next();
                    System.out.println("Enter your password: ");
                    String password = scanner.next();
                    AuthDAO authDAO = new AuthDAO();

                    try {
                        User loggedInManager = authDAO.login(username, password);
                        System.out.println("Logged in as: " + loggedInManager.getUsername() + ", ID: " +loggedInManager.getUser_id());

                        // check if logged in user is a manager
                         if (loggedInManager.getRole().equalsIgnoreCase("manager") == false)
                            {
                                System.out.println("Sorry, you do not have access!");
                                System.out.println("Logging out and exiting the program...");
                                loggedInManager = authDAO.logout(loggedInManager);
                                break;
                            }
                        
                        // Menu for managers 
                        while (true)
                        {

                            ApprovalDAO appDAO = new ApprovalDAO();
                            ExpenseDAO expDAO = new ExpenseDAO();
                            // ArrayLists for approvals and expenses
                            ArrayList<Expense> expList = new ArrayList<>();
                            ArrayList<Approval> appList = new ArrayList<>();

                            // Generate main menu for manager options 
                            System.out.println("Welcome, " + loggedInManager.getUsername());
                            System.out.println("Choose from the following options: ");
                            System.out.println("1. View Expense Reports Menu");
                            System.out.println("2. View Approvals Menu");
                            System.out.println("3. Exit");
                            
                            choice = scanner.nextInt();

                            switch(choice)
                            {

                                // Expense Reports Menu
                                case 1:
                                    System.out.println("Expense Report Menu");
                                    System.out.println("Select an option: ");
                                    System.out.println("1. View all expenses");
                                    System.out.println("2. View expenses by status");
                                    System.out.println("3. View expenses by employee");
                                    System.out.println("4. View expenses by date");
                                    System.out.println("5. View expenses by category");
                                    System.out.println("6. Exit menu");


                                    int new_choice = scanner.nextInt();

                                    switch (new_choice)
                                    {
                                        // view all expense reports
                                        case 1:
                                            expList = expDAO.getExpenses();
                                            for (Expense e : expList)
                                            {
                                                System.out.println(e);
                                            }
                                            System.out.println();
                                            break;

                                        // view expense reports by status 
                                        case 2:
                                            System.out.println("Choose what expense reports you want to view: pending, approved or denied");
                                            String status = scanner.next();
                                            System.out.println("Viewing all reports that are " + status);
                                            expList = expDAO.getExpensesByStatus(status);
                                            for (Expense e: expList)
                                            {
                                                System.out.println(e);
                                            }
                                            System.out.println();
                                            break; 
                                        
                                        // view expense reports by employee
                                        case 3:
                                            System.out.println("Enter the employee's username");
                                            String user = scanner.next();
                                            System.out.println("Viewing reports for " + user);
                                            expList = expDAO.getExpensesByEmployee(user);
                                            for (Expense e: expList)
                                            {
                                                System.out.println(e);
                                            }
                                            System.out.println();
                                            break;
                                        
                                        // view expense reports by date
                                        case 4:
                                            System.out.println("Enter the date range: ");
                                            System.out.println("Start date: ");
                                            String start_date = scanner.next();
                                            System.out.println("End date: ");
                                            String end_date = scanner.next();

                                            System.out.println("Viewing reports between " + start_date + "and " + end_date);
                                            expList = expDAO.getExpensesByDate(start_date, end_date);
                                            for (Expense e: expList)
                                            {
                                                System.out.println(e);
                                            }
                                            System.out.println();
                                            break;

                                        // view expense reports by category

                                        case 5:
                                            System.out.println("Enter the keyword you want to search: ");
                                            String category = scanner.next();
                                            System.out.println("Viewing reports containing keyword " + category);

                                            expList = expDAO.getExpensesByCategory(category);
                                            for (Expense e: expList)
                                            {
                                                System.out.println(e);
                                            }
                                            System.out.println();
                                            break;

                                    }   

                                    
                                    
                                    

                                case 2:
                                   

                                case 3:


                            }

                            // exit 
                            if (choice == 3)
                            {
                                System.out.println("Exiting the manager menu...");
                                System.out.println();
                                break;
                            }

                        

                        }

                   
                    } catch (Exception e) {
                        System.out.println("Login failed");
                    }

            }

            if (choice == 2)
            {
                System.out.println("Exiting the program...");
                break;
            }

            
        }

        scanner.close();


    }
}