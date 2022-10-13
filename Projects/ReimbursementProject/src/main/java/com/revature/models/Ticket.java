package com.revature.models;

import java.util.Objects;

public class Ticket {

    private int ID;

    private double amount;

    private String description;

    private String status = "pending";

    private String username;

    public Ticket(double amount, String description, String username) {
        this.amount = amount;
        this.description = description;
        this.username = username;
    }

    public Ticket(int ID, double amount, String description, String status, String username) {
        this.ID = ID;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.username = username;
    }

    public Ticket() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ID=" + ID +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ID == ticket.ID && Double.compare(ticket.amount, amount) == 0 && description.equals(ticket.description) && status.equals(ticket.status) && username.equals(ticket.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, amount, description, status, username);
    }
}
