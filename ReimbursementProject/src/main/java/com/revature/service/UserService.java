package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

import java.util.Scanner;

public class UserService {

    UserDAO ud = (UserDAO) new UserDAOImpl();

    Scanner input = new Scanner(System.in);

    public User Login() {

        System.out.println("Please enter your username");
        String username = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();

        User byuser = ud.getByUsername(username);

        if(byuser.getPassword().equals(password)) {
            System.out.println("Congratulations, you're officially logged in!");
            System.out.println(byuser);

            return byuser;
        }else {
            System.out.println("Invalid Login!");
            return null;
        }
    }

    public User Register() {
        System.out.println("Please enter your first name");
        String first = input.nextLine();
        System.out.println("Please enter your last name");
        String last = input.nextLine();
        System.out.println("Please enter your username");
        String username = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();

        User newUser = ud.createUser(first, last, username, password);

        return newUser;
    }

}