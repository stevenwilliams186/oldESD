<%-- 
    Document   : listMedicines
    Created on : 03-Dec-2014, 06:31:27
    Author     : Steve
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<jsp:useBean id="listMedicineBean" scope="request" class="beans.ListTableBean">
    <jsp:setProperty name="listMedicineBean" property="db" value="${dbConnection}"/>
    <jsp:setProperty name="listMedicineBean" property="table" value="medicine"/>
</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table>
            <c:forEach var="row" items="${listMedicineBean.output}">
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
