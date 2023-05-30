/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class Connector {
    public Connection connection;
    public Statement statement;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/almaul_db";
    static final String USER = "root";
    static final String PASS = ""; 
    public Connector() {
        try{
            Class.forName(JDBC_DRIVER);
            connection = (java.sql.Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected successfully!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Connection failed!");
        }
    }
}

