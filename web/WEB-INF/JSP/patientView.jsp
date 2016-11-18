<%-- 
    Document   : patientManagement
    Created on : 03-Dec-2014, 02:07:35
    Author     : Steve
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
    </head>
    <body>
        <table border="1">
 
            <tr>
                <td>
                    <jsp:include page="listPatients.jsp"/>
                </td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="../..//HTML-pages/addPatient.html"/>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="../..//HTML-pages/removePatient.html"/>
                </td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="../..//HTML-pages/getPatientInvoice.html"/>
                </td>
            </tr>
        
    </table>
</body>
</html>
