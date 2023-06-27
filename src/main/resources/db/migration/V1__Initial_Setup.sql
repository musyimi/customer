CREATE TABLE customer (
   id INT DEFAULT nextval('customer_id_seq') PRIMARY KEY,
   first_name TEXT not null,
   last_name TEXT NOT NULL,
   email TEXT UNIQUE NOT NULL
);