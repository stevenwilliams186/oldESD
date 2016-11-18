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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sam
 */
public class AddPatientBean implements Serializable {

    private String name, output, medicine1="", medicine2="", quantity1, quantity2, billOutput, costString;
    private int cost, patient_id, medicine_cost;

    private DBBean db;

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getOutput() {
        addPatient();
        return output;
    }

    public void setDb(DBBean db) {
        this.db = db;
    }

    private void addPatient() {
        String update = "INSERT INTO patients (name) VALUES ('" + name + "')";
        output = "Failed for somereason";
        try {
            
            
            costString = costString.replaceAll("\\$", "");
            cost = Integer.parseInt((costString));
            
            
            int result = db.executeUpdate(update);
            if (result != 0) {
                result = db.getLastKey();
                patient_id = result;
                update = "INSERT INTO bills (patient_id,cost,paid) VALUES (" + result + "," + cost + ",false)";
                result = db.executeUpdate(update);
                if (result != 0) {
                    output = "Patient Successfully Added";

                    addMedicines();
                }

            }

        } catch (SQLException ex) {
            System.out.println("Error adding patient");
            Logger.getLogger(AddPatientBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addMedicines() {

        int bill_id, medicine_id, quan1, quan2;
        String query;
        // Sorry but result sets I think are lovely... 
        ResultSet rs;
        // What we have: patient_id, medicine_id, bill_id 

        // First get the actual patient id not just 
        try {
            quan1 = Integer.parseInt(quantity1);

        } catch (NumberFormatException nfe) {
            quan1 = 0;
        }
        try {
            quan2 = Integer.parseInt(quantity2);
        } catch (NumberFormatException nfe) {
            quan2 = 0;
        }

        query = "SELECT * FROM bills WHERE patient_id = (" + patient_id + ")";

        rs = db.getResultFromQuery(query);

        try {
            if (rs.next()) {

                bill_id = rs.getInt("ID");

                medicine1 = medicine1.replaceAll("'", "''");

                query = "SELECT * FROM medicine WHERE name = ('" + medicine1 + "')";

                rs = db.getResultFromQuery(query);

                // Add first medicine 
                try {
                    if (rs.next()) {

                        medicine_id = rs.getInt("id"); // Get medicine id 

                        medicine_cost = rs.getInt("cost"); // Get medicine cost 

                        cost = cost + (medicine_cost * quan1); // update cost to be the medicine cost * the quantity 

                        query = "INSERT INTO prescriptions (bill_id,medicine_id,quantity,cost) VALUES (" + bill_id + "," + medicine_id + "," + quan1 + "," + medicine_cost + ")";

                        db.executeUpdate(query); // Create a new prescription and add it 

                        medicine1 = medicine1.replaceAll("''", "'");

                        medicine1 = "Prescription for " + quan1 + " x " + medicine1 + " costing £" + medicine_cost;

                    }

                    // Last thing to do is update the bill 
                } catch (SQLException ex) {
                    medicine1 = "";
                    Logger.getLogger(AddPatientBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                medicine2 = medicine2.replaceAll("'", "''");

                // Same thing for next medicine 
                query = "SELECT * FROM medicine WHERE name = ('" + medicine2 + "')";

                rs = db.getResultFromQuery(query);

                try {
                    if (rs.next()) {

                        medicine_id = rs.getInt("id"); // Get medicine id 

                        medicine_cost = rs.getInt("cost"); // Get medicine cost 

                        cost = cost + (medicine_cost * quan2); // update cost to be the medicine cost * the quantity 

                        query = "INSERT INTO prescriptions (bill_id,medicine_id,quantity,cost) VALUES (" + bill_id + "," + medicine_id + "," + quan2 + "," + medicine_cost + ")";

                        db.executeUpdate(query); // Create a new prescription and add it 

                        medicine2 = medicine2.replaceAll("''", "'");

                        medicine2 = " Prescription for " + quan2 + " x " + medicine2 + " costing £" + medicine_cost;

                    }
                } catch (SQLException sql) {
                    medicine2 = "";
                }

                updateBill(bill_id);

            } else {
                output += "No bill!";
            }
        } catch (SQLException ex) {

        }

    }

    public void updateBill(int bill_id) {

        String query = "UPDATE bills " + "SET cost = (" + cost + ")" + " WHERE ID = (" + bill_id + ")";

        try {
            db.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(AddPatientBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        billOutput = "Total bill is £" + cost;

    }

    public String getMedicine1() {
        return medicine1;
    }

    public void setMedicine1(String medicine1) {
        this.medicine1 = medicine1;
    }

    public String getMedicine2() {
        return medicine2;
    }

    public void setMedicine2(String medicine2) {
        this.medicine2 = medicine2;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(String quantity1) {
        this.quantity1 = quantity1;
    }

    public String getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(String quantity2) {
        this.quantity2 = quantity2;
    }

    public int getMedicine_cost() {
        return medicine_cost;
    }

    public void setMedicine_cost(int medicine_cost) {
        this.medicine_cost = medicine_cost;
    }

    public String getBillOutput() {
        return billOutput;
    }

    public void setBillOutput(String billOutput) {
        this.billOutput = billOutput;
    }

    public String getCostString() {
        return costString;
    }

    public void setCostString(String costString) {
        this.costString = costString;
    }
    
    

}
