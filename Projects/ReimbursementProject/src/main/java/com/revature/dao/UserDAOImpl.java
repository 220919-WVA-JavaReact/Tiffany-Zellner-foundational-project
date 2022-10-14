package com.revature.dao;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                boolean isManager = rs.getBoolean("ismanager");

                byuser = new User(first, last, username, password, isManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return byuser;
    }


    @Override
    public User createUser(String first, String last, String username, String password, boolean isManager) {

        User user = new User();

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO users (first, last, username, password, isManager) VALUES (?,?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.setBoolean(5, isManager);

            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null){
                rs.next();

                int id = rs.getInt("id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedUsername = rs.getString("username");
                String receivedPassword = rs.getString("password");
                Boolean receivedIsManager = rs.getBoolean("isManager");

                user = new User(receivedFirst, receivedLast, receivedUsername, receivedPassword, receivedIsManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user to the database");
            return null;
        }
        return user;
    }

    @Override
    public User promoteUser(int id) {
        return null;
    }

    @Override
    public List<User> getAllEmployees() {
        List<User> users = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE isManager = false";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs;

            if((rs = stmt.executeQuery()) != null){
                while(rs.next()){
                    int id = rs.getInt("id");
                    String first = rs.getString("first");
                    String last = rs.getString("last");
                    String username = rs.getString("username");
                    String password = rs.getString("password");

                    User user = new User(id, first, last, username, password, false);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


}
