package edu.unimagdalena.courseHub.course;



import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Consulta 2: Determinar si existe un curso por código (Query Method)
    boolean existsByCode(String code);

    // Consulta 3: Buscar cursos por departamento (Query Method navegando la relación)
    List<Course> findByDepartmentName(String departmentName);
}
