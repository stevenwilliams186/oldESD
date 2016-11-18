/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve
 */
public class AlterMedicinePriceBean {

    private String output;
    private int id, cost;
    private DBBean db;

    public void setDb(DBBean db) {
        this.db = db;
    }

    public String getOutput() {
        changeCost();
        return output;
    }

    public void setMedicineId(String medId) {
        try {
            id = Integer.valueOf(medId);
        } catch (NumberFormatException ex) {
            output = "Invalid medicine ID";
        }
    }

    public void setMedCost(String medCost) {
        try {
            cost = Integer.valueOf(medCost);
        } catch (NumberFormatException ex) {
            output = "Invalid medicine Cost";
        }
    }
    
    private void changeCost(){
        
        String update = "UPDATE medicine SET cost="+cost+" WHERE id=" +id;
        try {
            int result = db.executeUpdate(update);
            if(result==0){
                output = "Cost not update, check ID and try again";
            }else{
                output = "Price succesfully updated";
            }
        } catch (SQLException ex) {
            System.out.println("Error updating medicine");
            Logger.getLogger(AlterMedicinePriceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
