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

/**
 *
 * @author Sam
 */
// Bean for the list all patients and list all medicines use cases 
public class ListAllPatientsBean {

    private ArrayList<String> output;
    private List<ArrayList> patients;
    private DBBean db;

    public void setDb(DBBean db) {
        this.db = db;
    }

    public List<ArrayList> getPatients() {
        getAllPatients();
        return patients;
    }

    private void getAllPatients() {

        // The query 
        String query = "SELECT * FROM patients";

        //The resultSet gets converted to ArrayList of ArrayLists
        patients= db.doQuery(query);

    }

}
