
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Description</title>
</head>
<body>
<form action="index.jsp">

    <p> <% out.print(request.getParameter("view"));%> </p>
    <a href="films">Back</a>


</form>

</body>
</html>
