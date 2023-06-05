package View.Rent;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class RoomListView { 
    public JFrame window = new JFrame("Renter Data");
    String[] columnName={"Name","Size","Price","Status"};
    
    String data[][] = new String [100][4];
    DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
    public JTable tabel = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(tabel);
    
    public JButton bcancel = new JButton("Logout");

    public RoomListView(){
        window.setLayout(null);
        window.setSize(550,600);
       
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        window.add(scrollPane);
        window.add(bcancel);
        scrollPane.setBounds(20, 35, 500, 300);
        bcancel.setBounds(20, 350, 100,50);
        
           
    }
    
}
