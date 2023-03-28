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
    answer_to_id int,
    client_id int not null,
    FOREIGN KEY (client_id) REFERENCES client(id)
);
