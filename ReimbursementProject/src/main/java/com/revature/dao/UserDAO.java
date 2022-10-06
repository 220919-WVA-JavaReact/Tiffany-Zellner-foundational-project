package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {
    User getByUsername(String username);

    User createUser(String first, String last, String username, String password);
}
