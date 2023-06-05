package Controller;

import Controller.Rent.RoomListController;
import Controller.Admin.AdminController;
import Model.MainModel;
import View.Admin.AdminPageView;
import View.LoginPageView;
import View.Rent.RoomListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController {
    LoginPageView loginPage;
    MainModel mainModel;

    public LoginController(LoginPageView loginPage, MainModel mainModel) {
        this.loginPage = loginPage;
        this.mainModel = mainModel;
        
        // Login button action listener
        loginPage.blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the login credentials using the MainModel
                // Handle login based on the user type
                if(mainModel.checkLogin(loginPage.getUsername(), loginPage.getPassword()).equals("admin")){
                    AdminPageView adminPage=new AdminPageView();
                    AdminController APC=new AdminController(adminPage,mainModel);
                    loginPage.dispose();
                }
                else if(mainModel.checkLogin(loginPage.getUsername(), loginPage.getPassword()).equals("renter")){
                    // Open the RoomListView and create a new RoomListController
                    RoomListView RDV=new RoomListView();
                    RoomListController RDC=new RoomListController(RDV,mainModel);
                    loginPage.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Wrong username/password!");
                }
            }
        });
    }
    
}
