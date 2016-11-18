<%-- 
    Document   : payBill
    Created on : 03-Dec-2014, 12:43:52
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<jsp:useBean id="payBill" scope="request" class="beans.PayBillBean">
    <jsp:setProperty name="payBill" property="patient_name" param="patient_name"/>
    <jsp:setProperty name="payBill" property="amountString" param="amount"/>
    <jsp:setProperty name="payBill" property="db" value="${dbConnection}"/>
</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        ${payBill.output}
    </body>
</html>
