package com.revature.dao;

import com.revature.models.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {
    Ticket createTicket(double amount, String description, String username) throws SQLException;

    List<Ticket> getAllTicketsByUsername(String username);


}
