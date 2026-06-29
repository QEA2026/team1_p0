package com.revature;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        // Create console menu 
        while (true) 
        {
            System.out.println("Select from the following choices:");
            System.out.println("1. -");
            System.out.println("2. -");
            System.out.println("3: Exit");

            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    System.out.println("1");
                    break;

                case 2:
                    System.out.println("2");
                    break;
            }

            if (choice == 3)
            {
                System.out.println("Exiting the program...");
                break;
            }

            
        }

        




    }
}