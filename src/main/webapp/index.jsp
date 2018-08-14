
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> Hello ${param.name} </h2>
<div align="center">
<table border="1" cellpadding="5">
    <tr>
        <th> Title </th>
    </tr>

<c:forEach var = "film" items = "${list}" >
    <tr>
    <td> <a href="view?id=<c:out value="${film.id}"/>"> <c:out value="${film.title}"/> </a> <td>
    </tr>
</c:forEach>
</table>
</div>
<form action="FromDelete.jsp">
<p><input type = "submit" value="delete" name = "delete" /></p>
<p><input type = "submit" value="add" name = "add" />  </p>
</form>


<a href="HelloUser.jsp">Back for new login</a>

</body>
</html>