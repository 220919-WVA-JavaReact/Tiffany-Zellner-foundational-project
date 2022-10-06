package com.revature.dao;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{

    @Override
    public User getByUsername(String username) {

        User byuser = new User();

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){

                rs.next();

                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                byuser = new User(first, last, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return byuser;
    }

    @Override
    public User createUser(String first, String last, String username, String password) {

        User user = new User();

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO users (first, last, username, password) VALUES (?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, username);
            stmt.setString(4, password);

            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null){
                rs.next();

                int id = rs.getInt("id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");

                user = new User(receivedFirst, receivedLast, receivedUsername, receivedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user to the database");
        }
        return user;
    }
}
