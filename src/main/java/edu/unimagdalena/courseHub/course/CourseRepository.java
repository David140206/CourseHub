package edu.unimagdalena.courseHub.course;



import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {


    boolean existsByCode(String code);


    List<Course> findByDepartmentName(String departmentName);
}
