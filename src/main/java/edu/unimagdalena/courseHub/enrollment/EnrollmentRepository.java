package edu.unimagdalena.courseHub.enrollment;


import edu.unimagdalena.courseHub.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Buscar estudiantes matriculados en un curso
    @Query("""
        select e.student
        from Enrollment e
        where e.course.id = :courseId
          and e.status = :status
    """)
    List<Student> findStudents(@Param("courseId") Long courseId, @Param("status") EnrollmentStatus status);

    //  Contar matrículas activas
    long countByStatus(EnrollmentStatus status);

    //  Obtener promedio de notas de un curso
    @Query("""
        select avg(e.finalGrade)
        from Enrollment e
        where e.course.id = :courseId
          and e.finalGrade is not null
    """)
    Double calculateAverageGrade(@Param("courseId") Long courseId);
}