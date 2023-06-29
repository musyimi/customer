CREATE TABLE customer (
   id INT PRIMARY KEY,
   first_name TEXT not null,
   last_name TEXT NOT NULL,
   email TEXT UNIQUE NOT NULL
);