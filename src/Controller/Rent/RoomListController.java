package Controller.Rent;

import Controller.LoginController;
import Controller.Rent.RenterController;
import Model.MainModel;
import View.LoginPageView;
import View.Rent.RenterDataView;
import View.Rent.RoomListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomListController {
    RoomListView roomList;
    MainModel mainModel;
    String[][] data;
    int row;
    public RoomListController(RoomListView roomList, MainModel mainModel) {
        this.roomList = roomList;
        this.mainModel = mainModel;
        showData();
        // MouseAdapter for handling table row selection
        roomList.tabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                row=roomList.tabel.getSelectedRow();
                if(data[row][3].equals("empty"))
                {
                    RenterDataView RDV=new RenterDataView();
                    RenterController RDC=new RenterController(RDV,mainModel,data[row][0]);
                    roomList.window.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Room is occupied!");
                }
            }
        });
        // ActionListener for the "Cancel" button
        roomList.bcancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginPageView LPV=new LoginPageView();
                LoginController LPC=new LoginController(LPV,mainModel);
                roomList.window.dispose();
            }
        });
    }
    //Updates the data in the table by retrieving the room information from the MainModel.
    void showData(){
        data=mainModel.readRoom();
        String[] columnName={"Name","Size","Price","Status"};
        // Create a DefaultTableModel with non-editable cells
        DefaultTableModel tableModel=new DefaultTableModel(data,columnName){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        // Set the table model for the RoomListView
        roomList.tabel.setModel(tableModel);
    }
}
