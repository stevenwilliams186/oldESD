<%-- 
    Document   : addPatientWithMeds
    Created on : 04-Dec-2014, 05:35:24
    Author     : Steve
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form name="PatientsNMeds" action="/GPBusiness/requests/newPatientWithMeds.do" method="POST">
            <table>
                <tr>
                    <td>
                        Patient Name:<input type="text" name="name" value="" />
                    </td>
                    <td>
                        Consultation Cost: <input type="text" name="cost" value="50" /> 
                    </td>
                </tr>
                
            </table>

        </form>
    </body>
</html>
