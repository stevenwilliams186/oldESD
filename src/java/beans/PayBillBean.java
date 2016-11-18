/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steve
 */
public class PayBillBean implements Serializable {

    private int billID, billCost;
    private DBBean db;
    private String output;

    private String patient_name, amountString;
    private int amount;

    public String getOutput() {
        payBill();
        return output;
    }

    public void setId(String id) {
        try {
            billID = Integer.valueOf(id);
        } catch (NumberFormatException ex) {
            output = "Invalid billID";
        }
    }

    public void setCost(String cost) {
        try {
            billCost = Integer.valueOf(cost);
        } catch (NumberFormatException ex) {
            output = "Invalid Cost value";
        }
    }

    public void setDb(DBBean db) {
        this.db = db;
    }

    private void payBill() {

        ResultSet rs;
        int patient_id, bill_id, bill_cost, change; 
        String query = "SELECT * FROM patients WHERE name = ('" + patient_name + "')";

        try {
            
            amountString = amountString.replaceAll("\\$", "");
            
            amount = Integer.parseInt(amountString);

            rs = db.getResultFromQuery(query);

            try {
                if (rs.next()) {

                    patient_id = rs.getInt("id"); 
                    
                    query = "SELECT * FROM bills WHERE patient_id = (" + patient_id + ")"; 
                    
                    rs = db.getResultFromQuery(query);
                    
                    if(rs.next()) {
                        
                        bill_id = rs.getInt("ID"); 
                        bill_cost = rs.getInt("cost"); 
                        
                        if (bill_cost <= amount) {
                            
                            change = amount - bill_cost;
                            
                            // Now remove the bill completely 
                            
                            query = "DELETE FROM bills WHERE ID = (" + bill_id + ")"; 
                            
                            db.executeUpdate(query); 
                            
                            output = "Successfully paid bill of £" + bill_cost + " for " + patient_name + " change due is £" + change;
                            
                        }
                        else {
                            output = "Sorry please pay in full only. " + patient_name + " currently owes £" + bill_cost;  
                        }
                        
                    }
                    else {
                        output = patient_name + " has no bills to pay!"; 
                    }
                    
                }
                else {
                    output = patient_name + " is not in the database!"; 
                }
            } catch (SQLException sql) {
                output = "SQL EXCEPTION!"; 
            }

        } catch (NumberFormatException nfe) {
            output = amountString + " is not a valid number!";
        }

        rs = db.getResultFromQuery(query);

    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
