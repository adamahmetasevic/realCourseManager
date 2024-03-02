package CourseController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCourse")
public class deleteCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementPU");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Course course = entityManager.find(Course.class, Long.parseLong(courseId));
            if (course != null) {
                entityManager.remove(course);
                entityManager.getTransaction().commit();
                response.sendRedirect("deleteCourse.jsp");
            } else {
                response.sendRedirect("deleteCourseError.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            response.sendRedirect("deleteCourseError.jsp");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
