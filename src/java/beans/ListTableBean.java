/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve
 */
public class ListTableBean implements Serializable {

    private List<ArrayList> output, patientOutput;
    private String table;
    private DBBean db;

    public List<ArrayList> getOutput() {
        getMedicines();
        return output;
    }
    public List<ArrayList> getPatientOutput() {
        getPatients();
        return patientOutput; 
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setDb(DBBean db) {
        this.db = db;
    }

    private void getMedicines() {
        try {
            if (db.tableExists("medicine")) {
                String query = "SELECT * FROM medicine"; // + table;
                output = db.doQuery(query);
            }else{
                output= null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListTableBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void getPatients() {
        try {
            if (db.tableExists("patients")) {
                String query = "SELECT * FROM patients";
                patientOutput = db.doQuery(query);
            }else{
                patientOutput= null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListTableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
