package Controller.Rent;

import Controller.LoginController;
import Model.MainModel;
import View.LoginPageView;
import View.Rent.RenterDataView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RenterController {
    RenterDataView renterData;
    MainModel mainModel;
    String room;
    
    public RenterController(RenterDataView renterData, MainModel mainModel, String room) {
        this.renterData = renterData;
        this.mainModel = mainModel;
        this.room=room;
        
        //// ActionListener for the "Add" button
        renterData.btnAddPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 // Call the MainModel to input renter data
                mainModel.inputRenter(renterData.getName(), renterData.getId(), renterData.getContact(), renterData.getRentTime(), room);
            }
        });
         // ActionListener for the "Logout" button
        renterData.btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                LoginPageView LPV=new LoginPageView();
                LoginController LPC=new LoginController(LPV,mainModel);
                renterData.window.dispose();
            }
        });
    }
    
}
