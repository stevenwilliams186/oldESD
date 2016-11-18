<%-- 
    Document   : printPatientInvoice
    Created on : 04-Dec-2014, 05:48:16
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<jsp:useBean id="printInvoiceBean" scope="request" class="beans.PrintInvoiceBean">
    <jsp:setProperty name="printInvoiceBean" property="user" param="username"/>
    <jsp:setProperty name="printInvoiceBean" property="db" value="${dbConnection}"/>
</jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        
        ${printInvoiceBean.output}
        
    </body>
</html>
