<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="CourseController.Course" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Course</title>
</head>
<body>
    <h2>Edit Course</h2>
    <form action="editCourseServlet" method="post">
        <input type="hidden" name="courseId" value="<%= request.getAttribute("courseId") %>">
        Course Name: <input type="text" name="courseName" value="<%= request.getAttribute("courseName") %>"><br>
        Description: <input type="text" name="description" value="<%= request.getAttribute("description") %>"><br>
        <!-- Other fields for editing -->
        <button type="submit">Submit</button>
    </form>
</body>
</html>
