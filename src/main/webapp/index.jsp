
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> Hello ${param.name} </h2>

<form action="films">

    <%
        for (int i = 0; i < 20; i++) {%>
    <p> <a href="descriptionF.jsp?id=<%= request.getAttribute("id" + i)%>&view=<%= request.getAttribute("overview" + i)%> "> <% out.println(request.getAttribute("title" + i)); %></a> </p>

    <%}%>

</form>
<a href="HelloUser.jsp">Back for new login</a>



</body>
</html>