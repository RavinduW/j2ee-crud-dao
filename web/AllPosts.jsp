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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <h4><a href="/crud/AddNewPost.jsp">Create new post</a></h4>
        <br/><br/>
        
        <h4>Download Posts</h4>
        <br/>
        <a href="PdfDownloadController">Download as pdf</a>
        <br/>
        <a href="ExcelDownloadController">Download as excel</a>
        <br/><br/>
        
        <h4>Select the number of records you want to display</h4>
        <form method="get" action="PostController">
            <input type="number" name="numberOfRows" min="5" max="20"/>
            <input type="hidden" name="currentPageNumber" value="${currentPageNumber}"/>
            <button type="submit">Select</button>
        </form>
        <br/>
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
            <nav aria-label="Navigation for countries">
                <ul class="pagination">
                    
                    <!-- previous tab -->
                    <!-- if currentPagteNumber is not equal to 1, we need to go to the previous pages  -->
                    <c:if test="${currentPageNumber != 1}">
                        <li class="page-item"><a class="page-link" 
                            href="PostController?numberOfRows=${numberOfRows}&currentPageNumber=${currentPageNumber-1}">Previous</a>
                        </li>
                    </c:if>
                    
                   <!-- numbered tabs -->
                   <!-- run a foreach loop begins with 1 to numberOfPages -->
                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPageNumber eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" 
                                    href="PostController?numberOfRows=${numberOfRows}&currentPageNumber=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    
                    <!-- next tab -->            
                    <c:if test="${currentPageNumber < numberOfPages}">
                        <li class="page-item"><a class="page-link" 
                            href="PostController?numberOfRows=${numberOfRows}&currentPageNumber=${currentPageNumber+1}">Next</a>
                        </li>
                    </c:if>              
                </ul>
            </nav>
        </div>
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    </body>
</html>
