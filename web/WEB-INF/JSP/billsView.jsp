<%-- 
    Document   : BillseView
    Created on : 03-Dec-2014, 06:44:15
    Author     : Steve
--%>

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
                    <jsp:include page="listBills.jsp"/>
                </td>
            </tr>     
            <tr>
                <td>        
                    <jsp:include page="../../HTML-pages/payBills.html"/>
                </td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="../../HTML-pages/addMedicineToBill.html"/>
                <td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="../../HTML-pages/getPatientInvoice.html"/>
                <td>
            </tr>
           
        </table>
    </body>
</html>
