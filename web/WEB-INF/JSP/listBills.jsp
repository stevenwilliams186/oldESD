<%-- 
    Document   : listBills
    Created on : 03-Dec-2014, 19:21:14
    Author     : Steve
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>

<jsp:useBean id="listBillsBean" class="beans.ListTableBean" scope="request">
    <jsp:setProperty name="listBillsBean" property="db" value="${dbConnection}"/>
    <jsp:setProperty name="listBillsBean" property="table" value="bills"/>

</jsp:useBean>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Bills</title>
    </head>
    <body>
        <table border =1>
            <c:forEach var="row" items="${listBillsBean.output}">
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
