package com.jeremycurny.sparkjavarestapi.app;

import com.team_glados.UserController;

import static spark.Spark.*;

public class App {

    public static int PORT = 8080;

    public static void main(String[] args) {

        UserController userController = new UserController();

        port(PORT);

        before((req, res) -> {
            res.header("Access-Control-Allow-Headers", "Authorization, Content-Type");
            res.header("Access-Control-Allow-Methods", "POST");
            res.header("Access-Control-Allow-Origin", "*");
        });

        post("/", (req, res) -> userController.bot(req, res));

        exception(Exception.class, (e, req, res) -> {
            String message = e.getClass().getName() + ": " + e.getMessage();
            res.type("application/json");
            res.status(500);
            res.body(message);
        });
    }

}
