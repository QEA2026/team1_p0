package com.revature.models;

public class Expense {

    private int id;
    private int user_id;
    private double amount;
    private String description;
    private String date;


    public Expense(int id, int user_id, double amount, String description, String date) {
        this.id = id;
        this.user_id = user_id;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }
    
    public int getUser_id()
    {
        return this.user_id;
    }

    public double getAmount()
    {
        return this.amount;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return ("ID: " + this.id + ", User ID: " + this.user_id + ", Amount: " + this.amount + ", Description: " + this.description);
    }
}
