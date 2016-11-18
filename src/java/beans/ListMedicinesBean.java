/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Steve
 */
public class ListMedicinesBean implements Serializable{
    
    private List<ArrayList> output; 
    private DBBean db;

    public List<ArrayList> getOutput() {
        getMedicines();
        return output;
    }

    public void setDb(DBBean db) {
        this.db = db;
    }
    
    private void getMedicines(){
        String query = "SELECT * FROM medicine";
        output = db.doQuery(query);
    }
    
    
        
        
        
         
        
    }
    

