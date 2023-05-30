/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class RoomListModel extends Connector implements CalculateResult {

    public RoomListModel() {

    }

    @Override
    public long totalPrice(long duration, String name) {
        long price = 0;

        if (name.equals("A1")) {
            price = 200000;
          
        } else if (name.equals("A2")) {
            price = 200000;
          
        } else if (name.equals("B1")) {
            price = 230000;
            
        } else if (name.equals("B2")) {
            price = 250000;
            
        } else if (name.equals("C1")) {
            price = 450000;
            
        } else {
            price = 400000;
           
        }

        return price * duration;
    }

}
