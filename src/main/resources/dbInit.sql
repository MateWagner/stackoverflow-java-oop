DROP TABLE IF EXISTS answer, question, client CASCADE;
CREATE TABLE client
(
    id       SERIAL PRIMARY KEY,
    name     text not null,
    email    text not null,
    password TEXT,
    date     DATE
);

DROP TABLE IF EXISTS question;
CREATE TABLE question (
   id SERIAL PRIMARY KEY,
   client_id int not null ,
   title text not null ,
   description text not null,
   date text not null ,
   FOREIGN KEY (client_id) REFERENCES client(id)
);

DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
    id SERIAL PRIMARY KEY,
    description text not null ,
    date DATE not null ,
    question_id INT not null ,
CREATE TABLE client
(
    id       SERIAL PRIMARY KEY,
    name     text                        not null,
    email    text                        not null,
    password TEXT,
    date     timestamp without time zone not null
);

CREATE TABLE question
(
    id          SERIAL PRIMARY KEY,
    client_id   int                         not null,
    title       text                        not null,
    description text                        not null,
    date        timestamp without time zone not null,
    FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE answer
(
    id           SERIAL PRIMARY KEY,
    description  text                        not null,
    date         timestamp without time zone not null,
    question_id  INT                         not null,
    answer_to_id int,
    client_id    int                         not null,
    FOREIGN KEY (client_id) REFERENCES client (id)
);

INSERT INTO client (name, email, password, date)
VALUES ('John Doe', 'john.doe@example.com', 'password123', '2022-03-28'),
       ('Jane Smith', 'jane.smith@example.com', 'password456', '2022-03-28'),
       ('Bob Johnson', 'bob.johnson@example.com', 'password789', '2022-03-28');

INSERT INTO question (client_id, title, description, date)
VALUES (1, 'How do I create a new table in SQL?',
        'I am new to SQL and I want to create a new table. Can someone help me?', '2022-03-28'),
       (2, 'What is the difference between INNER JOIN and OUTER JOIN?',
        'I am confused about the different types of joins in SQL. Can someone explain the difference between INNER JOIN and OUTER JOIN?',
        '2022-03-28'),
       (3, 'How can I insert data into a table?',
        'I have created a new table and now I want to insert some data into it. What is the syntax for inserting data into a table in SQL?',
        '2022-03-28');

INSERT INTO answer (description, date, question_id, answer_to_id, client_id)
VALUES ('To create a new table in SQL, use the CREATE TABLE statement. The syntax is CREATE TABLE table_name (column_name data_type, ...)',
        '2022-03-28', 1, NULL, 2),
       ('INNER JOIN returns only the matching rows from both tables, while OUTER JOIN returns all rows from both tables, with NULL values where there is no match.',
        '2022-03-28', 2, NULL, 3),
       ('To insert data into a table in SQL, use the INSERT INTO statement. The syntax is INSERT INTO table_name (column1, column2, ...) VALUES (value1, value2, ...)',
        '2022-03-28', 3, NULL, 1);
