/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewRenter;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class RoomListView { 
    JFrame window = new JFrame("Renter Data");
    Object columnName [];
    
    String data[][] = new String [100][4];
    DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
    JTable tabel = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(tabel);
    
    JButton bcancel = new JButton("Logout");

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
        
        showData();
        
    }
    
    private void showData(){
        
    }
    
}
