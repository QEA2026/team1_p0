import os
import sqlite3

from user_model import User
from expense_model import Expense

DB_PATH = os.path.join(os.path.dirname(__file__), "..", "db", "expense_manager.db")

def get_connection():
        try:
                conn = sqlite3.connect(DB_PATH)
                conn.row_factory = sqlite3.Row
                return conn, conn.cursor()
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to connect to database: {error}") from error


def login(username, password):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute(
                        "SELECT * FROM USERS WHERE username = ? AND password = ? AND role = 'employee';",
                        (username, password),
                )
                row = cursor.fetchone()
                if row:
                        return User(id = row['id'],username=row['username'],password=row['password'],role=row['role'])
                return None
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to log in user: {error}") from error
        finally:
                if conn is not None:
                        conn.close()

def create_expense(user_id, amount, description, date):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute(
                        "INSERT INTO EXPENSES (user_id, amount, description, date) VALUES (?, ?, ?, ?);",
                        (user_id, amount, description, date),
                )
                expense_id = cursor.lastrowid
                cursor.execute(
                        "INSERT INTO APPROVALS (expense_id, status, reviewer, comment, review_date) VALUES (?, 'pending', NULL, NULL, NULL);",
                        (expense_id,),
                )
                conn.commit()
                return expense_id
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to create expense: {error}") from error
        finally:
                if conn is not None:
                        conn.close()


def get_employee_expenses(user_id):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute(
                        """SELECT e.id, e.user_id, e.amount, e.description, e.date,
                                          a.status, a.comment
                           FROM EXPENSES e
                           LEFT JOIN APPROVALS a ON a.expense_id = e.id
                           WHERE e.user_id = ?
                           ORDER BY e.date DESC, e.id DESC;""",
                        (user_id,),
                )
                rows = cursor.fetchall()
                expenses = []
                for row in rows:
                        expense = Expense(id=row['id'],user_id=row['user_id'],amount=row['amount'],description=row['description'],date=row['date'])
                        expenses.append({
                                "expense": expense,
                                "status": row['status'] or 'pending',
                                "comment": row['comment'],
                        })
                return expenses
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to load employee expenses: {error}") from error
        finally:
                if conn is not None:
                        conn.close()

def get_expense_by_id(expense_id):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute("SELECT * FROM EXPENSES WHERE id = ?;", (expense_id,))
                row = cursor.fetchone()
                if row:
                        return Expense(id=row['id'],user_id=row['user_id'],amount=row['amount'],description=row['description'],date=row['date'])
                return None
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to load expense: {error}") from error
        finally:
                if conn is not None:
                        conn.close()

def get_expense_with_status(expense_id):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute(
                        """SELECT e.id, e.user_id, e.amount, e.description, e.date, a.status
                           FROM EXPENSES e
                           LEFT JOIN APPROVALS a ON a.expense_id = e.id
                           WHERE e.id = ?;""",
                        (expense_id,),
                )
                row = cursor.fetchone()
                if row is None:
                        return None
                expense = Expense(id=row['id'],user_id=row['user_id'],amount=row['amount'],description=row['description'],date=row['date'])
                return {"expense": expense, "status": row['status'] or 'pending'}
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to load expense: {error}") from error
        finally:
                if conn is not None:
                        conn.close()

def update_expense(expense_id, amount, description, date):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute(
                        """UPDATE EXPENSES SET amount = ?, description = ?, date = ?
                           WHERE id = ? AND id IN (
                                   SELECT expense_id FROM APPROVALS WHERE expense_id = ? AND status = 'pending'
                           );""",
                        (amount, description, date, expense_id, expense_id),
                )
                conn.commit()
                return cursor.rowcount > 0
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to update expense: {error}") from error
        finally:
                if conn is not None:
                        conn.close()

def delete_expense(expense_id):
        conn = None
        try:
                conn, cursor = get_connection()
                cursor.execute(
                        "SELECT status FROM APPROVALS WHERE expense_id = ?;",
                        (expense_id,),
                )
                row = cursor.fetchone()
                if row is None or row['status'] != 'pending':
                        return False
                cursor.execute("DELETE FROM APPROVALS WHERE expense_id = ?;", (expense_id,))
                cursor.execute("DELETE FROM EXPENSES WHERE id = ?;", (expense_id,))
                conn.commit()
                return cursor.rowcount > 0
        except sqlite3.Error as error:
                raise RuntimeError(f"Failed to delete expense: {error}") from error
        finally:
                if conn is not None:
                        conn.close()
