package edu.unimagdalena.courseHub.student;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Consulta 1: Buscar estudiante por email (Query Method)
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Student> findByNameContainingIgnoreCaseAndActiveTrue(String name);

}