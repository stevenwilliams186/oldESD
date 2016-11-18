/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

/**
 *
 * @author Steve
 */
/*TODO
 * I need to change all methods taking input to be PreparedStatements
 * to avoid SQLInjection
 */
public class DBBean {

    private final String dbName;
    private final String dbURL;
    private final String dbUsername;
    private final String dbPassword;

    private Connection con;
    private Statement state;
    private ResultSet rs;
    private ResultSetMetaData rsMD;

    public DBBean(String dbName, String dbURL, String dbUsername, String dbPassword) {
        this.dbName = dbName;
        this.dbURL = dbURL;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            state = con.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Unable to load driver class");
            System.out.println(ex);
            /*
             MAYBE NOT HAVE THIS
             */
            System.exit(0);
        } catch (SQLException ex) {
            System.out.println("Error opening connection");
            /*
             MAYBE NOT HAVE THIS
             */
            System.exit(0);
            Logger.getLogger(DBBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Returns the generate id of a created value
    */
    public int getLastKey() throws SQLException {
        rs = state.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }return -1;
    }

    //TODO Execute update and execute query are different
/*
    THese still all need to be prepared statements for the updates -.-
    */
    public int executeUpdate(String query) throws SQLException {

        int result = state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        return result;
    }

    public List<ArrayList> doQuery(String query) {

        List<ArrayList> results = new ArrayList();
        try {
            rs = state.executeQuery(query);
            results = formatResultSet();
        } catch (SQLException sqle) {
            System.out.println("Error doing query");
            System.out.println(sqle);
        }

        return results;

    }
    
    public boolean tableExists(String table) throws SQLException{
        rs = con.getMetaData().getTables(null, null, table, null);
        return rs.next();
    }
    
    public ResultSet getResultFromQuery(String query) {
        try {
            rs = state.executeQuery(query);
            
        }
        catch(SQLException ex) {
            System.out.println("ERROR SQL EXCEPTION");
        }
        
        return rs; 
    }

    //Used to convert the result set into an ArrayList
    private ArrayList<ArrayList> formatResultSet() throws SQLException {
        ArrayList<ArrayList> rows = new ArrayList();
        ArrayList<String> fields;
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            fields = new ArrayList();
            for (int i = 1; i <= columnCount; i++) {
                fields.add(rs.getString(i));
            }
            rows.add(fields);
        }
        return rows;
    }

    /*
     Used for debugging
     */
    private void printResult() {

        try {
            rsMD = rs.getMetaData();
            int n = rsMD.getColumnCount();
            for (int i = 1; i <= n; i++) {
                System.out.print(rsMD.getColumnName(i) + "\t");
            }
            System.out.println("");
            while (rs.next()) {
                for (int i = 1; i <= n; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() throws SQLException {

        if (state != null) {
            state.close();
        }
        if (con != null) {
            con.close();
        }
    }

}
