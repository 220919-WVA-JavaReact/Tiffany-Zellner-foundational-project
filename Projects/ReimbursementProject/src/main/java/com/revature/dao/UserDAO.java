package com.revature.dao;

import com.revature.models.User;

import java.util.List;

public interface UserDAO {
    User getByUsername(String username);

    User createUser(String first, String last, String username, String password, boolean isManager);

    User promoteUser(int id);

    List<User> getAllEmployees();
}
