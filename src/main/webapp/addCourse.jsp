<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Course</title>
</head>
<body>
    <h2>Add Course</h2>
    <form action="addCourseServlet" method="post">
        <label for="courseName">Course Name:</label><br>
        <input type="text" id="courseName" name="courseName" required><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br>

        <label for="professorId">Professor ID:</label><br>
        <input type="number" id="professorId" name="professorId" required><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
