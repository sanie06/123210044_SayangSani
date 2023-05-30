package controller;

import Controller.AdminController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.AdminModel;
import Model.LoginModel;
import ViewRenter.RoomListView;
import View.LoginPageView;
import ViewAdmin.AdminPageView;

public class LoginController {
    LoginModel loginModel;
    LoginPageView loginView;

    public LoginController(LoginModel loginModel, LoginPageView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        
         loginView.blogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String username = loginView.getUsername();
                String password = loginView.getPassword();
                
                if(loginModel.loginHandler(username, password)){
                    JOptionPane.showMessageDialog(null, "Login Success!!");
                    if(loginModel.isAdmin(username, password)){
                        AdminPageView adminView = new AdminPageView();
                        AdminModel adminModel = new AdminModel();
                        AdminController adminController = new AdminController(adminModel, adminView);
                    }else{
                      RoomListView RenterView = new RoomListView();
                    }
                    loginView.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed!!");
                }
            }
        });    
    }
 }
