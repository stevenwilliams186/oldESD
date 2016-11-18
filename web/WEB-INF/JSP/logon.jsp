<%-- 
    Document   : logon
    Created on : 30-Nov-2014, 20:03:42
    Author     : Steve
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<jsp:useBean id="logon" scope="request" class="beans.LogonBean">
    <jsp:setProperty name="logon" property="username" param="username"/>
    <jsp:setProperty name="logon" property="password" param="password"/>
    <jsp:setProperty name="logon" property="db" value="${dbConnection}"/>
    
</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        
    </body>
</html>
