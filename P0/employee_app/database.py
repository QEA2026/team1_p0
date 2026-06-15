import sqlite3, os

DB_PATH = "../db/expense_manager.db"

def get_connection():
        conn = sqlite3.connect(DB_PATH)
        conn.row_factory = sqlite3.Row
        return conn, conn.cursor()

def get_user_by_username(username):
        conn, cursor = get_connection()
        pass

def create_expense(user_id, amount, description, date):
        conn, cursor = get_connection()
        pass

def get_employee_expenses(user_id):
        conn, cursor = get_connection()
        pass

def get_expense_by_id(expense_id):
        conn, cursor = get_connection()
        pass

def update_expense(expense_id, amount, description, date):
        conn, cursor = get_connection()
        pass

def delete_expense(expense_id):
        conn, cursor = get_connection()
        pass