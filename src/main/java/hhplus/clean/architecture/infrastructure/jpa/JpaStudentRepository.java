package hhplus.clean.architecture.infrastructure.jpa;

import hhplus.clean.architecture.domain.entity.Student;
import hhplus.clean.architecture.domain.repository.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Long>, StudentRepository {
}
