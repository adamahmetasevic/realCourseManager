package CourseController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import CourseController.Course;

@WebServlet("/addCourseServlet")
public class addCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addCourse.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String description = request.getParameter("description");
        Long professorId = Long.parseLong(request.getParameter("professorId"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagement");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Course course = new Course();
            course.setCourseName(courseName);
            course.setDescription(description);
            course.setProfessor(entityManager.find(Professor.class, professorId));

            entityManager.persist(course);

            entityManager.getTransaction().commit();

            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();

            response.sendRedirect("error.jsp");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
