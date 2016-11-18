/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.DBBean;
import beans.LogonBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Steve
 */
public class Front extends HttpServlet {

    String mainPageURL = "/WEB-INF/JSP/mainView.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Identifies and stores the specific page requested
        String requestURI = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("Requested: " + requestURI);

        //Defaults to sending the user to logon
        String requestedResource;

        //System.out.println("Requested: " + requestedResource);
        //System.out.println("Sams Requested: " + request.getServletPath());
        switch (requestURI) {
            case "/pages/logon":
                requestedResource = "/HTML-pages/logon.html";
                break;
            case "/requests/logon.do":
                requestedResource = "logon.jsp";
                break;
            case "/requests/addPatient.do":
                requestedResource = "addPatient.jsp";
                break;
            case "/pages/viewPatients":
                requestedResource = "patientView.jsp";
                break;
            case "/requests/removePatient.do":
                requestedResource = "removePatient.jsp";
                break;
            case "/requests/listPatients.do":
                requestedResource = "patientView.jsp";
                break;
            case "/pages/medicines":
                requestedResource = "medicineView.jsp";
                break;
            case "/requests/changeMedicinePrice.do":
                requestedResource = "changeMedicinePrice.jsp";
                break;
            case "/requests/addMedicine.do":
                requestedResource = "addMedicine.jsp";
                break;
            case "/requests/payBill.do":
                requestedResource = "payBills.jsp";
                break;
            case "/pages/payBills":
                requestedResource = "billsView.jsp";
                break;
            case "/requests/addMedicineToBill.do":
                requestedResource = "addMedicineToBill.jsp";
                break;
            case "/requests/printPatientInvoice.do":
                requestedResource = "printPatientInvoice.jsp";
                break;
            
            default:
                requestedResource = "/HTML-pages/welcome.html";
        }

        System.out.println("Going to: " + requestedResource);
        request.setAttribute("requestedResource", requestedResource);
        request.getRequestDispatcher(mainPageURL).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
