<<<<<<< HEAD:P0/db/schema.sqlite
CREATE TABLE users (
    user_id INTEGER PRIMARY KEY,
=======
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
>>>>>>> main:P0/db/schema.sql
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);

<<<<<<< HEAD:P0/db/schema.sqlite
CREATE TABLE expenses (
    expense_id INTEGER PRIMARY KEY,
=======
CREATE TABLE IF NOT EXISTS expenses (
    id INTEGER PRIMARY KEY,
>>>>>>> main:P0/db/schema.sql
    user_id INTEGER NOT NULL,
    amount REAL NOT NULL,
    exp_description TEXT NOT NULL,
    date TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS approvals (
    id INTEGER PRIMARY KEY,
    expense_id INTEGER NOT NULL,
    status TEXT NOT NULL,
    reviewer INTEGER,
    comment TEXT,
    review_date TEXT,
    FOREIGN KEY (expense_id) REFERENCES expenses (expense_id)
);
