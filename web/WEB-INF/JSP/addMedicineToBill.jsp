<%-- 
    Document   : addMedicineToBill
    Created on : 03-Dec-2014, 18:21:51
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="AddMedicineToBillBean" scope="request" class="beans.AddMedicineToBillBean">
    <jsp:setProperty name="AddMedicineToBillBean" property="patient_name" param="patient_name" />
    <jsp:setProperty name="AddMedicineToBillBean" property="medicine_name" param="medicine_name" />
    <jsp:setProperty name="AddMedicineToBillBean" property="quant" param="quantity" />
    <jsp:setProperty name="AddMedicineToBillBean" property="db" value="${dbConnection}" />

</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        ${AddMedicineToBillBean.output}
    </body>
</html>
