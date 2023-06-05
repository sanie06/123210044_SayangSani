/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author LENOVO
 */
public class Connector {
    String JDBC_Driver="com.mysql.cj.jdbc.Driver";
    String DB_URL="jdbc:mysql://localhost/almaul_db";
    String user="root";
    String pass="";
    Statement statement;
    Connection connection;
    public Connector(){
        try{
            Class.forName(JDBC_Driver);
            connection=DriverManager.getConnection(DB_URL,user,pass);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
