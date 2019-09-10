<%-- 
    Document   : allPosts
    Created on : Sep 9, 2019, 8:20:07 AM
    Author     : ravindu_c
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Posts</title>
    </head>
    <body>
        <h1>See all posts</h1> <h2><a href="/crud/AddNewPost.jsp">Create new post</a></h2>
        
        <div style="width:1000px;margin-left: auto;margin-right: auto;">
            <table cellpadding="10" > 
                <tr>
                    <th>Id</th>
                    <th>Post Title</th>
                    <th>Post Body</th>
                </tr>
                <c:forEach items="${AllPosts}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.title}</td>
                        <td>${p.postbody}</td>  
                        <td>
                            <a href="PostEditController?id=${p.id}">Edit</a>
                            <form action="/crud/PostDeleteController?id=${p.id}" method="post">
                              <button type="submit">Delete</button>  
                            </form>
                            
                        </td>  
                    </tr>

                </c:forEach>
            </table>
        </div>
    </body>
</html>
