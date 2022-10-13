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
        boolean running = true;
        while(running) {


            System.out.println("Press 1 to Login, Press 2 to register, Press 3 to quit");

            Scanner input = new Scanner(System.in);

            String choice = input.nextLine();

            User loggedInUser = null;

            if (choice.equals("1")) {
                loggedInUser = us.Login();
            } else if (choice.equals("2")) {
                loggedInUser = us.Register();
            } else if (choice.equals("3")) {
                System.out.println("Thank you, have a nice day!");
                running = false;
            } else {
                System.out.println("Invalid input");
            }

            while (loggedInUser != null) {
                if(loggedInUser.getIsManager()) {
                    System.out.println("Press 1 to view all pending tickets");
                    System.out.println("Press 2 to view all approved tickets");
                    System.out.println("Press 3 to view all denied tickets");
                    System.out.println("Press 4 to approve/deny tickets");
                    System.out.println("Press 5 to create new manager");
                    System.out.println("Press 6 to logout");

                    String subchoice = input.nextLine();

                    switch (subchoice) {
                        case "1":
                            ts.getAllTicketsByStatus("pending");
                            break;
                        case "2":
                            ts.getAllTicketsByStatus("approved");
                            break;
                        case "3":
                            ts.getAllTicketsByStatus("denied");
                            break;
                        case "4":
                            ts.changeTicketStatus();
                            break;
                        case "5":

                            break;
                        case "6":
                            loggedInUser = null;
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                }else {
                    System.out.println("Press 1 to create ticket, Press 2 to view all your tickets, Press 3 to logout");

                    String subchoice1 = input.nextLine();

                    switch (subchoice1) {
                        case "1":
                            ts.createTicket();
                            break;
                        case "2":
                            ts.getAllTicketsByUsername(loggedInUser.getUsername());
                            break;
                        case "3":
                            System.out.println("Have a nice day!");
                            loggedInUser = null;
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;

                    }
                }
            }
        }
    }

}