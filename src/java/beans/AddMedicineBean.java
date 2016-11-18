/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve
 */
public class AddMedicineBean implements Serializable {

    private DBBean db;
    private String output, name;
    private int cost;

    public void setDb(DBBean db) {
        this.db = db;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMedCost(String medCost) {
        try {
            
            medCost = medCost.replaceAll("\\$", "");
            
            cost = Integer.valueOf(medCost);
        } catch (NumberFormatException ex) {
            output = "Invalid cost";
        }
    }

    public String getOutput() {
        addMedicine();
        return output;
    }

    private void addMedicine() {
        String update = "INSERT INTO medicine (name,cost) VALUES ('"+name+"',"+cost+")";
        try {
            int result = db.executeUpdate(update);
            if(result > 0){
                output = "Successfully added Medicine";              
            }else{
                output = "Not added for some reason";
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(AddMedicineBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
