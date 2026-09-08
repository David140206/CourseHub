package edu.unimagdalena.courseHub.enrollment;



import edu.unimagdalena.courseHub.course.Course;
import edu.unimagdalena.courseHub.student.Student;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "enrollment",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_enrollment_student_course",
                columnNames = {"student_id", "course_id"}
        )
)
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrolled_at", nullable = false)
    private LocalDateTime enrolledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnrollmentStatus status;

    @Column(name = "final_grade", precision = 3, scale = 2)
    private BigDecimal finalGrade;

    protected Enrollment() {}

    public Enrollment(Student student, Course course, LocalDateTime enrolledAt, EnrollmentStatus status) {
        this.student = student;
        this.course = course;
        this.enrolledAt = enrolledAt;
        this.status = status;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(LocalDateTime enrolledAt) { this.enrolledAt = enrolledAt; }
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
    public BigDecimal getFinalGrade() { return finalGrade; }
    public void setFinalGrade(BigDecimal finalGrade) { this.finalGrade = finalGrade; }
}