package com.revature;

import com.revature.models.User;
import com.revature.service.TicketService;
import com.revature.service.UserService;


import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static UserService us = new UserService();

    public static TicketService ts = new TicketService();

    public static void main(String[] args) throws SQLException {
        System.out.println("Press 1 to Login, Press 2 to register");

        Scanner input = new Scanner(System.in);

        String choice = input.nextLine();

        User loggedInUser = null;

        if (choice.equals("1")){
            loggedInUser = us.Login();
        } else if (choice.equals("2")){
            loggedInUser = us.Register();
        }

        if (loggedInUser != null){
            System.out.println("Press 1 to create ticket, Press 2 to view all your tickets");

            String subchoice = input.nextLine();

            switch (subchoice) {
                case "1":
                    ts.createTicket();
                    break;
                case "2":
                    ts.getAllTicketsByUsername(loggedInUser.getUsername());
                    break;

            }
        }
    }

}