<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Professor</title>
</head>
<body>
    <h1>Add Professor</h1>
    <form action="addProfessorServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
     
        <label for="department">Department:</label>
        <input type="text" id="department" name="department"><br><br>
        

        
        <input type="submit" value="Add Professor">
    </form>
    
     <%
        String professorId = (String) request.getAttribute("professorId");
        if (professorId != null) {
    %>
            <p>Professor ID: <%= professorId %></p>
    <%
        }
    %>
</body>
</html>
