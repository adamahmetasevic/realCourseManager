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

@WebServlet("/editCourseServlet")
public class editCourseServlet extends HttpServlet {
	
	   @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String courseIdString = request.getParameter("courseId");
	        
	        if (courseIdString != null && !courseIdString.isEmpty()) {
	            Long courseId = Long.parseLong(courseIdString);
	            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagement");
	            EntityManager entityManager = entityManagerFactory.createEntityManager();

	            try {
	                Course course = entityManager.find(Course.class, courseId);
	                request.setAttribute("course", course);
	                request.getRequestDispatcher("/editCourse.jsp").forward(request, response);
	            } catch (Exception e) {
	                e.printStackTrace();
	                response.sendRedirect("error.jsp");
	            } finally {
	                entityManager.close();
	                entityManagerFactory.close();
	            }
	        } else {
	            response.sendRedirect("error.jsp");
	        }
	    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String courseIdString = request.getParameter("courseId");


	    Long courseId = Long.parseLong(courseIdString);
	    String courseName = request.getParameter("courseName");
	    String description = request.getParameter("description");
	    String professorIdString = request.getParameter("professorId");
	    Long professorId = null;
	    if (professorIdString != null && !professorIdString.isEmpty()) {
	        professorId = Long.parseLong(professorIdString);
	    }

	    EntityManagerFactory entityManagerFactory = null;
	    EntityManager entityManager = null;
	    try {
	        entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagement");
	        entityManager = entityManagerFactory.createEntityManager();
	        entityManager.getTransaction().begin();

	        // Find the course to edit
	        Course course = entityManager.find(Course.class, courseId);

	        if (course != null) {
	            // Update course details
	            course.setCourseName(courseName);
	            course.setDescription(description);

	            // Find the professor with the given ID and associate it with the course
	            Professor professor = entityManager.find(Professor.class, professorId);
	            course.setProfessor(professor);
	        }

	        entityManager.getTransaction().commit();
	        // Redirect to the viewCoursesServlet to refresh the page
	        response.sendRedirect(request.getContextPath() + "/viewCoursesServlet");
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle any errors and redirect to an error page
	        response.sendRedirect("error.jsp");
	    } finally {
	        // Close the EntityManager and EntityManagerFactory in a finally block
	        if (entityManager != null) {
	            entityManager.close();
	        }
	        if (entityManagerFactory != null) {
	            entityManagerFactory.close();
	        }
	    }
	}
}

