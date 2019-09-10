<%-- 
    Document   : addNew
    Created on : Sep 9, 2019, 12:37:32 AM
    Author     : Ravindu Weerasnghe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Posts</title>
    </head>
    <body>
        <h1>Add New Post</h1>
        <form action="/crud/PostController" method="post">
            Title:<br/>
            <input type="text" name="title" style="width: 200px" />
            <br/><br/>
            Description:<br/>
            <textarea name="postbody" style="width: 200px;height: 200px"></textarea>
            <br/><br/>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
