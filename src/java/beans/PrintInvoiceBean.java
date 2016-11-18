/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Steve
 */
public class PrintInvoiceBean {

    private DBBean db;
    private String user="", output; 
    
    public String getPatientInvoice() {

        String username, query, med_name, hold;
        int patient_id, bill_id, med_id, amountOwed, med_cost, med_quantity, counter;

        ResultSet rs;

        username = user;

        // Check to see if the username is in the db 
        query = "SELECT id FROM patients WHERE name = ('" + username + "')";

        rs = db.getResultFromQuery(query);

        try {
            // If false then that patient doesn't exist, otherwise get the id
            if (rs.next()) {

                output = username;

                patient_id = rs.getInt("id");

                // Now get the bill id of the bill that has that id (should only ever be one) 
                query = "SELECT * FROM bills WHERE patient_id = (" + patient_id + ")";

                rs = db.getResultFromQuery(query);

                // True if the patient has at least one bill
                if (rs.next()) {

                    // Set bill_id which we will use to get all the prescriptions for that patient
                    bill_id = rs.getInt("ID");

                    // Get the amount owed for the bill 
                    amountOwed = rs.getInt("cost");

                    // Add the amount owed onto the output string 
                    output += " has a bill for £" + amountOwed;

                    // Get all the prescriptions that have that bill id
                    //query = "SELECT * FROM prescriptions WHERE bill_id = (" + bill_id + ")";
                    query = "SELECT prescriptions.*, medicine.name " + " FROM prescriptions, medicine "
                            + "WHERE prescriptions.bill_id = (" + bill_id + ") AND " + " medicine.id = prescriptions.medicine_id";
                    rs = db.getResultFromQuery(query);

                    // If false then the user has a bill but no prescriptions 
                    if (rs.next()) {

                        // If here then the user has prescriptions, we need to loop through and get everything 
                        // This holds the strings made from analysing each prescription
                        ArrayList<String> prescriptions = new ArrayList<>();

                        // Used to look at the medicine table whilst keeping the result set of prescriptions
                        ResultSet rs2;

                        // Set med_id to the id of the medicine in the current prescription
                        med_id = rs.getInt("medicine_id");

                        // Get the number of medicines 
                        med_quantity = rs.getInt("quantity");

                        // Set med_cost to the quantity in the prescription * the cost in the prescription
                        med_cost = (med_quantity * (rs.getInt("cost")));
                       
                        // Set med_name to the medicine name 
                        med_name = rs.getString("name");

                        hold = "£" + med_cost + " owed for " + med_quantity + " " + med_name;
                        prescriptions.add(0, hold);
                        counter = 1;

                        // Loop until no more prescriptions left to do 
                        while (rs.next()) {
                           
                            med_id = rs.getInt("medicine_id");
                            med_quantity = rs.getInt("quantity");
                            med_cost = (med_quantity * (rs.getInt("cost")));
                            med_name = rs.getString("name");
                            hold = "£" + med_cost + " owed for " + med_quantity + " " + med_name;
                            prescriptions.add(counter, hold);
                            counter++;

                        }

                        // At this point we have an array list of Strings containing a standard message about different prescriptions 
                        output += " which consists of the following ";

                        for (int i = 0; i < prescriptions.size(); i++) {
                            output += " " + prescriptions.get(i);
                        }

                        // And there we go... 
                    } else {
                        output = username + " has a bill of £" + amountOwed + " but no outstanding prescriptions";
                    }

                } else {
                    output = username + " has no bills!";
                }

            } else {
                output = username + " is not a patient!";
            }
        } catch (SQLException ex) {
            
            output = "SQL ERROR";
        }

        return output;

    }

    public DBBean getDb() {
        return db;
    }

    public void setDb(DBBean db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOutput() {
        getPatientInvoice();
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    

}
