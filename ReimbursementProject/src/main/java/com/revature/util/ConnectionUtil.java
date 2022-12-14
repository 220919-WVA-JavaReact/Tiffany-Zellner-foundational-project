package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static Connection conn = null;

    private ConnectionUtil() {

    }

    public static Connection getConnection(){

        try{
            if (conn != null && !conn.isClosed()){
                System.out.println("Use a previously made connection");
                return conn;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");

        Properties prop = new Properties();


       try{
           prop.load(new FileReader("src/main/resources/application.properties"));

           url = System.getenv("url");
           username= System.getenv("username");
           password = System.getenv("password");

           conn = DriverManager.getConnection(url, username, password);
           System.out.println("Established connection to database!");
       } catch (IOException e) {
           System.out.println("Could not find properties file!");
           e.printStackTrace();
        } catch (SQLException e){
           System.out.println("Could not establish connection!");
           e.printStackTrace();
       }

        return conn;
    }
}
