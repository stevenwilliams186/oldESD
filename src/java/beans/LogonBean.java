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
public class LogonBean implements Serializable {

    private DBBean db;
    private String username, password;

    public void setDb(DBBean db) {
        this.db = db;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getValid() {
        return logon();
    }

    private Boolean logon() {
        String query = "SELECT * FROM logins "
                + "WHERE username='" + username + "' AND password='" + password + "'";

        List<ArrayList> result = db.doQuery(query);
        return (result.size() > 0);
    }

}
