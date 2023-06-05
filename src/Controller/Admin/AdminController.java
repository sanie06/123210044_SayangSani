package Controller.Admin;

import Controller.LoginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.MainModel;
import View.Admin.AdminPageView;
import View.Admin.EditRentView;
import View.LoginPageView;


public class AdminController {
    //Constructs AdminController with AdminPageView and MainModel instances.
    //@param adminPage The AdminPageView instance
    //@param mainModel The MainModel instance
    AdminPageView adminPage;
    MainModel mainModel;
    String[][] data;
    int row;
      
    public AdminController(AdminPageView adminPage, MainModel mainModel) {
        this.adminPage = adminPage;
        this.mainModel = mainModel;
        showData();
        
        // Logout button action listener
        adminPage.blogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginPageView LPV=new LoginPageView();
                LoginController LPC=new LoginController(LPV,mainModel);
                adminPage.window.dispose();
            }
        });
        // Mouse click event listener for the table
        adminPage.tabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                row=adminPage.tabel.getSelectedRow();          
                // If status is 'notPaid', ask for confirmation to update the booking status to 'Paid'
                if(data[row][5].equals("notPaid"))
                {
                    int input=JOptionPane.showConfirmDialog(null,"UPDATE booking status of renter '"
                            +data[row][0]+"' to 'Paid'?","Option",JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        mainModel.updateStatus(data[row][0], data[row][1], data[row][6]);
                        showData();
                    }
                }
                 // If status is not 'notPaid', ask for confirmation to delete the booking or edit the booking
                else{
                    int input=JOptionPane.showConfirmDialog(null,"DELETE booking of room '"
                            +data[row][6]+"'?","Option",JOptionPane.YES_NO_OPTION);
                    if(input==0){
                        mainModel.deleteRent(data[row][1],data[row][6]);
                        showData();
                    }else{
                        int input1=JOptionPane.showConfirmDialog(null,"EDIT booking of room '"
                                +data[row][6]+"'?","Option",JOptionPane.YES_NO_OPTION);
                        if(input1==0){
                            EditRentView ERV=new EditRentView();
                            EditRenterController EPC=new EditRenterController(ERV,mainModel,data[row]);
                            ERV.setVisible(true);
                            adminPage.window.dispose();
                        }
                    }
                }
            }
        });
    }
    
    //Updates the table data in the AdminPageView with the data from the MainModel.
        void showData(){
        data=mainModel.readRenter();
        String[] columnName={"Name","ID","Contact","Duration","Bill","Status","Room"};
        DefaultTableModel tableModel=new DefaultTableModel(data,columnName){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        adminPage.tabel.setModel(tableModel);
    }
}
