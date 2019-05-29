-- # Create DB
CREATE DATABASE IF NOT EXISTS books DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE books;

-- # Create Table
CREATE TABLE IF NOT EXISTS test (
title varchar(50) NOT NULL,
author varchar(50),
published date,
done boolean,
PRIMARY KEY(title)
);

-- # Insert test cases into table if they do not exist
REPLACE INTO test
    (title, author, published, done)
    VALUES
    ('In Search of Lost Time', 'Marcel Proust', '1890-06-24', 1),
    ('Walden', 'Henry David Thoreau', '1899-12-04', 1),
    ('My Struggle', 'Karl Ove Knausgaard', '2009-10-20', 0),
    ('The Count of Monte Cristo', 'Alexandre Dumas', '1906-03-28', 1),
    ('The Sound and the Fury', 'William Faulkner', '1968-04-24', 0);


