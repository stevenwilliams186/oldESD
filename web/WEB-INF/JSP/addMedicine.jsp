<%-- 
    Document   : addMedicine.jsp
    Created on : 03-Dec-2014, 12:05:04
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<jsp:useBean id="addMedicine" scope="request" class="beans.AddMedicineBean">
    <jsp:setProperty name="addMedicine" property="db" value="${dbConnection}"/>
    <jsp:setProperty name="addMedicine" property="name" param="name" />
    <jsp:setProperty name="addMedicine" property="medCost" param="cost" />

</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        ${addMedicine.output}
    </body>
</html>
