package CourseController;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String department;




    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Course> courses;

    // Getters and setters
    public long getProfessorId() {
        return id;
    }

    public void setProfessorId(long professorId) {
        this.id = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }
    

    public void setDepartment(String department) {
        this.department = department;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
