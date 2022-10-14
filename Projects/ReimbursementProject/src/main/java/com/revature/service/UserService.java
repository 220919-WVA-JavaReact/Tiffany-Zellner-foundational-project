package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.User;

import java.util.List;
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
        System.out.println("Are you a manager: True or false");
        boolean isManager = input.nextBoolean();

        User newUser = ud.createUser(first, last, username, password, isManager);

        return newUser;
    }

    public void promoteUser() {
        List<User> users = ud.getAllEmployees();
        for(User user : users){
            System.out.println("Id: " + user.getId() + " Name: " + user.getFirst() + " " + user.getLast());
        }
        System.out.println("Please enter id of employee you wish to promote");
        int id = input.nextInt();
        ud.promoteUser(id);
    }
}