<%-- 
    Document   : EditPost
    Created on : Sep 9, 2019, 3:17:18 PM
    Author     : ravindu_c
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Posts</title>
    </head>
    <body>
        <h3>Edit Post</h3>
        <br/>
        <c:forEach items="${FindById}" var="p">
            <form action="/crud/PostEditController" method="post">
                <input type="hidden" name="id" value="${p.id}"/>
                
                Title:<br/>
                <input type="text" name="title" value="${p.title}" style="width: 200px" />
                <br/><br/>
                Description:<br/>
                <textarea name="postbody" style="width: 200px;height: 200px">${p.postbody}</textarea>
                <br/><br/>
                <button type="submit">Edit</button>
            </form>
        </c:forEach>

    </body>
</html>
