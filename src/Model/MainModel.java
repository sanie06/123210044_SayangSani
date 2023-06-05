package Model;

import javax.swing.JOptionPane;
import java.sql.*;

public class MainModel extends Connector implements Calculate {
    
    /**
     * Checks the login credentials of a user by querying the database.
     * @param user The username
     * @param pass The password
     * @return The role of the user ("admin", "renter"), or "none" if the login fails
     */
    public String checkLogin(String user, String pass) {
        try {
            String query = "SELECT role FROM accounts WHERE "
                    + "username='" + user + "' "
                    + "AND password=BINARY '" + pass + "'";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("role").equals("admin")) {
                    return "admin";
                } else if (rs.getString("role").equals("renter")) {
                    return "renter";
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed Checked");
        }
        return "none";
    }
    
    /**
     * Reads the room information from the database and returns it as a 2D array.
     * @return The room data array with columns for name, size, price, and status
     */
    public String[][] readRoom() {
        String[][] data = new String[numberRoom()][4];
        int total = 0;
        try {
            String query = "SELECT * FROM rooms";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                data[total][0] = rs.getString("name");
                data[total][1] = rs.getString("size");
                data[total][2] = rs.getString("price");
                data[total][3] = rs.getString("status");
                total++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed");
        }
        return data;
    }
    
    /**
     * Retrieves the total number of rooms from the database.
     * @return The total number of rooms
     */
    public int numberRoom() {
        int total = 0;
        try {
            String query = "SELECT * FROM rooms";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                total++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed");
        }
        return total;
    }
    
    /**
     * Inserts a new renter record into the database with the provided information.
     * @param name The name of the renter
     * @param id The ID of the renter
     * @param contact The contact information of the renter
     * @param duration The duration of the rent
     * @param room The room for rent
     */
    public void inputRenter(String name, String id, String contact, int duration, String room) {
        try {
            String query = "INSERT INTO renter VALUES('" 
                    + name + "','" 
                    + id + "','" 
                    + contact + "','" 
                    + duration + "','" + totalPrice(room, duration) +"', 'notPaid','" 
                    + room + "')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Booking success!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Insert failed!");
        }
    }
    
    /**
     * Calculates and returns the total price for a room based on its name and duration.
     * @param room The name of the room
     * @param duration The duration of the rent
     * @return The total price for the rent
     */
    @Override
    public int totalPrice(String room, int duration) {
        int price = 0;
        try {
            String query = "SELECT price FROM rooms WHERE name='" + room + "'";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                price = Integer.parseInt(rs.getString("price")) * duration;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed getting price");
        }
        return price;
    }
   
    
    /**
     * Reads the renter data from the database and returns it as a 2D array.
     * @return The renter data array with columns for name, id, contact, duration, bill, status, and room
     */
    public String[][] readRenter() {
        String[][] data = new String[numberRenter()][7];
        int total = 0;
        try {
            String query = "SELECT * FROM renter";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                data[total][0] = rs.getString("name");
                data[total][1] = rs.getString("id");
                data[total][2] = rs.getString("contact");
                data[total][3] = rs.getString("duration");
                data[total][4] = rs.getString("bill");
                data[total][5] = rs.getString("status");
                data[total][6] = rs.getString("room");
                total++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Reading failed!");
        }
        return data;
    }
    
    /**
     * Retrieves the total number of renters from the database.
     * @return The total number of renters
     */
    public int numberRenter() {
        int total = 0;
        try {
            String query = "SELECT * FROM renter";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                total++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed Reading");
        }
        return total;
    }
    
    /**
     * Deletes a renter record from the database based on the renter's ID and updates the room status.
     * @param id The ID of the renter to delete
     * @param room The room associated with the renter
     */
    public void deleteRent(String id, String room) {
        try {
            String query = "DELETE FROM renter WHERE id='" + id + "'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            String query1 = "UPDATE rooms set status='empty' WHERE name='" + room + "'";
            statement = connection.createStatement();
            statement.executeUpdate(query1);
            
            JOptionPane.showMessageDialog(null, "Successfully Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed Delete");
        }
    }
    
    /**
     * Updates the payment status and room status for a renter based on the renter's name, ID, and room.
     * @param name The name of the renter
     * @param id The ID of the renter
     * @param room The room associated with the renter
     */
    public void updateStatus(String name, String id, String room) {
        try {
            if (isAvailable(room)) {
                String query = "UPDATE renter set status='Paid' WHERE id='" + id + "'";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                
                String query1 = "UPDATE rooms set status='" + name + "' WHERE name='" + room + "'";
                statement = connection.createStatement();
                statement.executeUpdate(query1);
                
                JOptionPane.showMessageDialog(null, "Successfully Update");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed Update");
        }
    }
    
    /**
     * Checks if a room is available for rent by querying its status.
     * @param room The room to check availability for
     * @return True if the room is available, False otherwise
     */
    public boolean isAvailable(String room) {
        try {
            String query = "SELECT status from rooms WHERE name='" + room + "'";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("status").equals("empty")) {
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Checking failed!");
        }
        JOptionPane.showMessageDialog(null, "Room is not available! Please wait until the room is available.");
        return false;
    }
    
    /**
     * Edits a renter's information in the database based on the renter's ID.
     * @param name The updated name of the renter
     * @param id The updated ID of the renter
     * @param contact The updated contact information of the renter
     * @param duration The updated duration of the rent
     * @param room The updated room for rent
     * @param tempId The original ID of the renter
     */
    public void editRent(String name, String id, String contact, int duration, String room, String tempId) {
        try {
            String query = "UPDATE renter set "
                    + "name='" + name + "', "
                    + "id='" + id + "', "
                    + "contact='" + contact + "', "
                    + "duration='" + duration + "', "
                    + "bill='" + totalPrice(room, duration) + "' "
                    + "WHERE id='" + tempId + "'";
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Successfully Rent");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed Rent");
        }
    }
}
