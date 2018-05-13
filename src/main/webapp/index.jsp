
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> Hello ${param.name} </h2>


    <%
        for (int i = 0; i < 20; i++) {%>
    <p> <a href="view?title=<%= request.getAttribute("title" + i)%>"> <% out.println(request.getAttribute("title" + i)); %></a> </p>

    <%}%>


<a href="HelloUser.jsp">Back for new login</a>


</body>
</html>