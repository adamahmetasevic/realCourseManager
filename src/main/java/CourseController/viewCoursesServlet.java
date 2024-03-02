package CourseController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewCoursesServlet")
public class viewCoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagement");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            List<Course> courses = entityManager.createQuery("SELECT c FROM Course c", Course.class).getResultList();

            request.setAttribute("courses", courses);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCourses.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdStr = request.getParameter("courseId");
        String action = request.getParameter("action");

        if (action != null && action.equals("delete")) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagement");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            try {
                entityManager.getTransaction().begin();
                Course course = entityManager.find(Course.class, Long.parseLong(courseIdStr));

                if (course != null) {
                    entityManager.remove(course);
                }
                entityManager.getTransaction().commit();
                response.sendRedirect(request.getContextPath() + "/viewCoursesServlet");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            } finally {
                entityManager.close();
                entityManagerFactory.close();
            }
        } else {
        }
    }
}
