package com.revature.models;

public class Expense {

    private int expense_id;
    private int user_id;
    private double amount;
    private String description;
    private String date;


    public Expense(int expense_id, int user_id, double amount, String description, String date) {
        this.expense_id = expense_id;
        this.user_id = user_id;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public int getExpense_id() {
        return this.expense_id;
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
        return ("ID: " + this.expense_id + ", User ID: " + this.user_id + ", Amount: " + this.amount + ", Description: " + this.description);
    }
}
