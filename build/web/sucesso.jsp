<%-- 
    Document   : sucesso
    Created on : 26/06/2014, 14:11:19
    Author     : jonasrla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso</title>
    </head>
    <body>
        <h1>Sucesso!</h1>
        <p>
            <%
                out.println(request.getAttribute("message"));
            %>
        </p>
    </body>
</html>
