/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import  Model.LoginModel;
import View.LoginPageView;
import controller.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginPageView loginView = new LoginPageView();
        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel, loginView);
    }
}







