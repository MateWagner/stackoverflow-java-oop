package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.queston.QuestionsDAO;
import com.codecool.stackoverflowtw.queston.QuestionsDaoJdbc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.codecool.stackoverflowtw.config")
@ComponentScan("com.codecool.stackoverflowtw.answer")
@ComponentScan("com.codecool.stackoverflowtw.client")
@ComponentScan("com.codecool.stackoverflowtw.queston")
@ComponentScan("com.codecool.stackoverflowtw.controller")
public class StackoverflowTwApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

}
