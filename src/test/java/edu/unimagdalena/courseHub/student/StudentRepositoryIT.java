package edu.unimagdalena.courseHub.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@SpringBootTest
class StudentRepositoryIT {


    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:18.6-alpine");

    @Autowired
    StudentRepository studentRepository;

    @Test
    void shouldSaveStudent() {
        Student student = new Student(
                "Laura Gómez",
                "laura@coursehub.edu",
                LocalDate.of(2003, 5, 12)
        );

        Student saved = studentRepository.save(student);

        assertThat(saved.getId()).isNotNull();

        Optional<Student> result =
                studentRepository.findById(saved.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getEmail())
                .isEqualTo("laura@coursehub.edu");
    }

    @Test
    void shouldFindStudentByEmail() {
        studentRepository.save(
                new Student(
                        "Carlos Pérez",
                        "carlos@coursehub.edu",
                        LocalDate.of(2002, 8, 15)
                )
        );

        Optional<Student> result =
                studentRepository.findByEmail(
                        "carlos@coursehub.edu"
                );

        assertThat(result).isPresent();
        assertThat(result.get().getName())
                .isEqualTo("Carlos Pérez");
    }

    @Test
    void shouldRejectDuplicatedEmail() {

        studentRepository.saveAndFlush(
                new Student(
                        "Ana",
                        "ana@coursehub.edu",
                        LocalDate.of(2003, 1, 1)
                )
        );


        assertThatThrownBy(() ->
                studentRepository.saveAndFlush(
                        new Student(
                                "Otra Ana",
                                "ana@coursehub.edu",
                                LocalDate.of(2001, 2, 2)
                        )
                )
        ).isInstanceOf(DataIntegrityViolationException.class);
    }
}