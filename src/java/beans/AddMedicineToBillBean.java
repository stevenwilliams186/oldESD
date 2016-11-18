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

/**
 *
 * @author Steve
 */
public class AddMedicineToBillBean implements Serializable {

    private String output;
    private int medId, pId, quantity;
    private DBBean db;

    private String patient_name, medicine_name, quant;
    private int patient_id, medicine_id, bill_id, cost;

    public void setDb(DBBean db) {
        this.db = db;
    }

    private void addMedicineToBill() {

        ResultSet rs;
        int amount, medicine_cost;
        String query = "SELECT * FROM medicine WHERE name = ('" + medicine_name + "')";

        rs = db.getResultFromQuery(query);

        try {
            if (rs.next()) {

                medicine_id = rs.getInt("id");

                medicine_cost = rs.getByte("cost");

                amount = Integer.parseInt(quant);

                cost = medicine_cost * amount;

                // Get patient ID for bill id 
                query = "SELECT * FROM patients WHERE name = ('" + patient_name + "')";

                rs = db.getResultFromQuery(query);

                try {
                    if (rs.next()) {

                        patient_id = rs.getInt("id");

                        // now get bill id 
                        query = "SELECT * FROM bills WHERE patient_id = (" + patient_id + ")";

                        rs = db.getResultFromQuery(query);

                        // if true p has bill 
                        if (rs.next()) {

                            cost = cost + rs.getInt("cost"); 
                            
                            bill_id = rs.getInt("ID"); 
                            
                            query = "UPDATE bills SET cost = (" + cost + ") WHERE patient_id = (" + patient_id + ")";
                            
                            db.executeUpdate(query);
                            
                            
                        } // else p has no bill so we need to make one 
                        else {

                            query = "INSERT INTO bills (patient_id,cost) " + " VALUES (" + patient_id + "," + cost + ")"; 
                            
                            db.executeUpdate(query);
                    
                            query = "SELECT * FROM bills WHERE patient_id = (" + patient_id + ")";
                            
                            rs = db.getResultFromQuery(query); 
                            
                            rs.next();
                            
                            bill_id = rs.getInt("ID"); 
                            
                        }
                        
                        
                        
                        // Finally make prescription 
                        
                        query = "INSERT INTO prescriptions (bill_id,medicine_id,quantity,cost) VALUES (" + bill_id + "," + medicine_id + "," + amount + "," + medicine_cost + ")"; 
                        
                        db.executeUpdate(query);
                        
                        output = "Added " + quant + " x " + medicine_name + " to " + patient_name + "'s bill. Total bill is now £" + cost;
                        
                        
                    }
                }
                catch (SQLException sql) {
                    output = patient_name + " is not a patient with us!";
                } catch (NumberFormatException nfe) {
                    output = quant + " is not a valid number!";
                }

            }
        } catch (SQLException sql) {
            output = medicine_name + " is not in the database!";
        }

    }

    public void setMedicineId(String medicineId) {
        try {
            medId = Integer.valueOf(medicineId);
        } catch (NumberFormatException ex) {
            output = "Invalid Medicine ID";
        }
    }

    public void setPatientId(String patientId) {
        try {
            pId = Integer.valueOf(patientId);
        } catch (NumberFormatException ex) {
            output = "Invalid Patient ID";
        }
    }

    public void setAmount(String amount) {
        try {
            quantity = Integer.valueOf(amount);
            if (quantity <= 0) {
                throw new NumberFormatException(); //FOR FUNS :D
            }
        } catch (NumberFormatException ex) {
            output = "Invalid Quantity";
        }
    }

    public String getOutput() {
        addMedicineToBill();
        return output;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    
    
}

/*
 String query = "SELECT * FROM bills WHERE patient_id=" + pId;
 List<ArrayList> bills = db.doQuery(query);
 ArrayList<String> bill = bills.get(0);
 int billId =Integer.valueOf(bill.get(0));
 int result = db.doQuery(query).size();
 if (result > 0) {
 query = "SELECT * FROM medicine WHERE id=" + medId;
 List<ArrayList> results = db.doQuery(query);

 if (results.size() > 0) {
 ArrayList<String> data = results.get(0);
 int cost = Integer.valueOf(data.get(data.size() - 1));  //asumming cost is always last column

 String update = "INSERT INTO prescriptions"
 + " VALUES (" + billId + "," + medId + "," + quantity + "," + cost + ")";

 try {
 result = db.executeUpdate(update);
 if (result > 0) {
 output = "Successfully added medicine to bill";
 } else {
 output = "Error adding medicine to bill";
 }
 } catch (SQLException ex) {
 System.out.println("Went wrong adding prescription");
 Logger.getLogger(AddMedicineToBillBean.class.getName()).log(Level.SEVERE, null, ex);
 }
 } else {
 output = "That Medicine wasn't found";
 }
 } else {
 output = "That Bill ID was not found";
 }
 */
