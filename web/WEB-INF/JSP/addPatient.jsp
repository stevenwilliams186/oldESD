<%-- 
    Document   : addPatient
    Created on : 27-Nov-2014, 00:31:20
    Author     : Sam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>

<jsp:useBean id="addPatientBean"  class="beans.AddPatientBean" scope="request">
    <jsp:setProperty name="addPatientBean" property="name" param="name"/>
    <jsp:setProperty name="addPatientBean" property="costString" param="cost"/>
    <jsp:setProperty name="addPatientBean" property="db" value="${dbConnection}" />
    <jsp:setProperty name="addPatientBean" property="medicine1" param="medicine1"/>
    <jsp:setProperty name="addPatientBean" property="medicine2" param="medicine2"/>
    <jsp:setProperty name="addPatientBean" property="quantity1" param="quantity1"/>
    <jsp:setProperty name="addPatientBean" property="quantity2" param="quantity2"/>
</jsp:useBean>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
    </head>
    <body>

        ${addPatientBean.output}
        
        <br> 

        ${addPatientBean.medicine1}
        
        <br>
        
        ${addPatientBean.medicine2}
        
        <br>
        
        ${addPatientBean.billOutput}


    </body>
</html>
