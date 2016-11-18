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
public class RemovePatientBean implements Serializable {

    private DBBean db;
    private String output;
    private int id = -1;

    public void setDb(DBBean db) {
        this.db = db;
    }

    public String getOutput() {
        if (id != -1) {
            removePatient();
        }
        return output;
    }

    public void setPatientID(String patientID) {
        try {
            id = Integer.valueOf(patientID);
        } catch (NumberFormatException c) {
            output = "Invalid patient ID";
        }
    }

    private void removePatient() {

        String query = "SELECT * FROM patients WHERE ID=" + id;
        if (!db.doQuery(query).isEmpty()) {
            query = "SELECT * FROM bills WHERE patient_id=" + id + " AND paid=0";
            if (db.doQuery(query).isEmpty()) {
                query = "DELETE FROM patients WHERE "
                        + "ID=" + id;
                try {
                    int result = db.executeUpdate(query);
                    if (result > 0) {
                        output = result + " patients succesfully removed";
                    } else {
                        output = "Patient found, with no bills. For some reason, couldn't be removed";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RemovePatientBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                output = "Patient has unpaid bills";
            }
        } else {
            output = "Patient not found with that ID";
        }
        query = "SELECT * FROM bills WHERE ID=" + id + " AND paid=false";
        if (!db.doQuery(query).isEmpty()) {
            output = "Patient has unpaid bills";
        }
    }

}
