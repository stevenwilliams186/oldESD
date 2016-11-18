/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import beans.DBBean;

/**
 * Web application lifecycle listener.
 *
 * @author Steve
 */
public class ContextListener implements ServletContextListener {

    DBBean db;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext context = sce.getServletContext();
        context.setInitParameter("contextPath", context.getContextPath());
      
        System.out.println("Contect path: " + context.getContextPath());
        String dbName = context.getInitParameter("dbName");
        String dbURL = context.getInitParameter("dbURL");
        String dbUsername = context.getInitParameter("dbUsername");
        String dbPassword = context.getInitParameter("dbPassword");

        db = new DBBean(dbName, dbURL, dbUsername, dbPassword);
        db.connect();
        context.setAttribute("dbConnection", db);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (db != null) {
            try {
                db.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
