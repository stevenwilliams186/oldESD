<%-- 
    Document   : removePatient
    Created on : 01-Dec-2014, 19:48:34
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<jsp:useBean id="removePatient" scope="request" class="beans.RemovePatientBean">
    <jsp:setProperty name="removePatient" property="patientID" param="patientID" />
    
    <jsp:setProperty name="removePatient" property="db" value="${dbConnection}"/>
</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        ${removePatient.output};
    </body>
</html>
