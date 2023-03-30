
CREATE TABLE client
(
    id       SERIAL PRIMARY KEY,
    name     text not null,
    email    text not null,
    password TEXT,
    date     DATE
);



CREATE TABLE answer
(
    id           SERIAL PRIMARY KEY,
    description  text not null,
    date         DATE not null,
    question_id  INT  not null,
    answer_to_id int,
    client_id    int  not null,
    FOREIGN KEY (client_id) REFERENCES client (id)
);
CREATE TABLE question
(
    id                 SERIAL PRIMARY KEY,
    client_id          int                         not null,
    title              text                        not null,
    description        text                        not null,
    date               timestamp without time zone not null,
    solution_answer_id int,
    FOREIGN KEY (solution_answer_id) REFERENCES answer (id),
    FOREIGN KEY (client_id) REFERENCES client (id)
);
