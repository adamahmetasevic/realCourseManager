package CourseController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import CourseController.Professor;

@WebServlet("/addProfessorServlet")
public class addProfessorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addProfessor.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String professorName = request.getParameter("name");
        String department = request.getParameter("department");        

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagement");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Professor professor = new Professor();
            professor.setName(professorName);
            professor.setDepartment(department);

            entityManager.persist(professor);

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
