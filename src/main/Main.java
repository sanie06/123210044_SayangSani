package main;

import Controller.LoginController;
import Model.MainModel;
import View.LoginPageView;

public class Main {
    public static void main(String[] args) {
        LoginPageView LP=new LoginPageView();
        MainModel mainModel=new MainModel();
        LoginController LPC=new LoginController(LP,mainModel);
        LP.setVisible(true);
    }
}
