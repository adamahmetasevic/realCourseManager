<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="CourseController.Course" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Courses</title>
</head>
<body>
    <h2>View Courses</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Description</th>
                <th>Professor</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% List<Course> courses = (List<Course>) request.getAttribute("courses");
               for (Course course : courses) { %>
                <tr>
                    <td><%= course.getCourseId() %></td>
                    <td><%= course.getCourseName() %></td>
                    <td><%= course.getDescription() %></td>
                    <td><%= course.getProfessor().getName() %></td>
                    <td>
                        <form action="viewCoursesServlet" method="post">
                            <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit">Delete</button>
                        </form>
                        <form action="editCourseServlet" method="get">
                            <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
