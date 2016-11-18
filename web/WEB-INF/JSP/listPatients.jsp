<%-- 
    Document   : listPatients
    Created on : 01-Dec-2014, 15:01:09
    Author     : Sam
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>

<jsp:useBean id="listPatientBean" class="beans.ListTableBean" scope="request">
    <jsp:setProperty name="listPatientBean" property="db" value="${dbConnection}"/>
    <jsp:setProperty name="listPatientBean" property="table" value="patients"/>

</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Patients</title>
    </head>
    <body>
        <table border =1>
            <c:forEach var="row" items="${listPatientBean.patientOutput}">
                <tr>
                    <c:forEach var="data" items="${row}">
                        <td>
                            ${data}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
