<%-- 
    Document   : mainView
    Created on : 01-Dec-2014, 16:49:38
    Author     : Example
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="3">
            <thead>
                <tr>
                    <th>Top Left Corner</th>
                    <th><jsp:include page = "header.jsp"/>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><jsp:include page ="menu.jsp"/></td>
                    <td>
                        <jsp:include page="${requestedResource}"/></td>
                </tr>
            </tbody>

        </table>
        <footer>
            <jsp:include page = "footer.jsp"/> 
        </footer>

    </body>
</html>
