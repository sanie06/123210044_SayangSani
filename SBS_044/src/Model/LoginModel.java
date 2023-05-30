/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

//import java.sql.ResultSet;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.DriverManager;
//import javax.swing.JOptionPane;

import java.sql.ResultSet;

public class LoginModel extends Connector {

    public LoginModel() {

    }

    public boolean loginHandler(String user, String pass) {
        int totalData = 0;
        try {
            String query = "SELECT * FROM `accounts` WHERE " + "`username`='" + user + "' AND "+ "`password`='" + pass + "'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                totalData++;
            };

            statement.close();
            if (totalData > 0) {
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    public boolean isAdmin(String user, String pass) {
        int totalData = 0;
        try {
            String query = "SELECT * FROM `accounts` WHERE " + "`username`='" + user + "' AND " + "`password`='" + pass + "' AND " + "`role`='admin'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                totalData++;
            };

            statement.close();
            if (totalData > 0) {
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

}
