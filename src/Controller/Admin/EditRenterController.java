package Controller.Admin;

import Controller.Admin.AdminController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MainModel;
import View.Admin.AdminPageView;
import View.Admin.EditRentView;


public class EditRenterController {
    //Constructs the EditRenterController with the given EditRentView, MainModel, and data array.
    EditRentView editRenter;
    MainModel mainModel;
    String[] data; //@param data The array of renter data to be edited
    
    
    public EditRenterController(EditRentView editRenter, MainModel mainModel, String[] data) {
        this.editRenter = editRenter;
        this.mainModel = mainModel;
        this.data = data;
        editForm();
        // Submit button action listener 
        editRenter.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 // Update the renter data in the MainModel
                mainModel.editRent(editRenter.getName(),editRenter.getId(),editRenter.getContact(),editRenter.getDuration(),data[6],data[1]);
                // Open the AdminPageView and create a new AdminController
                AdminPageView adminPage=new AdminPageView();
                AdminController APC=new AdminController(adminPage,mainModel);
                // Dispose the EditRentView
                editRenter.dispose();
            }
        });
    }
    
    //Pre-fills the edit form with the existing renter data.
    void editForm(){
        editRenter.editName.setText(data[0]);
        editRenter.editId.setText(data[1]);
        editRenter.editContact.setText(data[2]);
        editRenter.editDuration.setText(data[3]);
    }
}
