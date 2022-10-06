package com.revature.dao;

import com.revature.models.Ticket;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public Ticket createTicket(double amount, String description, String username) throws SQLException {


        Ticket ticket = new Ticket();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO tickets (amount, description, username) VALUES (?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, amount);
            stmt.setString(2, description);
            stmt.setString(3, username);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                rs.next();


                int id = rs.getInt("id");
                double receivedAmount = rs.getDouble("amount");
                String receivedDescription = rs.getString("description");
                String receivedUsername = rs.getString("username");

                ticket = new Ticket(receivedAmount, receivedDescription, receivedUsername);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketsByUsername(String username) {

        List<Ticket> existingTickets = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "Select * FROM tickets WHERE username = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){

                while(rs.next()) {


                    int id = rs.getInt("id");
                    double amount = rs.getDouble("amount");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    String receivedUsername = rs.getString("username");

                    Ticket ticket = new Ticket(id, amount, description, status, receivedUsername);

                    existingTickets.add(ticket);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't find any tickets");
        }

        return existingTickets;
    }

}
