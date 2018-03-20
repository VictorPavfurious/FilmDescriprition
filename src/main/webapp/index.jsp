
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
        for (int i = 0; i < 20; i++) {%>
            <p><a href="view"> <%out.println(request.getAttribute("title" + i));%></a></p>

            <p><%out.print(request.getAttribute("overview"+ i));%> </p>
        <%}%>
</body>
</html>