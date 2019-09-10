/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ravindu Weerasnghe
 */

//main utility class for the database connection

public class ConnectionManager {
    
    private static Connection con;
    private static String connectionUrl;
    private static String username;
    private static String password;
    
    public static Connection getConnection() throws SQLException{
        
        try {
            connectionUrl = "jdbc:mysql://localhost:3306/crud";
            username = "root";
            password = "";
            
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection(connectionUrl,username,password);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //print out a success and error msgs regarding the db connection
        if(con != null){
            System.out.println("Connected to database");
        }else{
            System.out.println("Connection is failed");
        }
        
        return con; //return the db connection
    }
}//this class have the responsibility of providing the db connection
