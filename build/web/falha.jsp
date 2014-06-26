<%-- 
    Document   : falha
    Created on : 26/06/2014, 14:11:45
    Author     : jonasrla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Falha</title>
    </head>
    <body>
        <h1>
        <%
            out.println(request.getAttribute("error"));
        %>
        </h1>
        <p>
            <%
                out.println(request.getAttribute("message"));
            %>
        </p>
    </body>
</html>
