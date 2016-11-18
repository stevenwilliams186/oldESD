<%-- 
    Document   : changeMedicinePrice
    Created on : 03-Dec-2014, 06:57:47
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<jsp:useBean id="changeMedicinePrice" scope="request" class="beans.AlterMedicinePriceBean" >
    <jsp:setProperty name="changeMedicinePrice" property="db" value="${dbConnection}"/>
    <jsp:setProperty name="changeMedicinePrice" property="medicineId" param="id"/>
    <jsp:setProperty name="changeMedicinePrice" property="medCost" param="cost" />
</jsp:useBean>
<html>
    <head>
        
    </head>
    <body>
        ${changeMedicinePrice.output}
    </body>
</html>
