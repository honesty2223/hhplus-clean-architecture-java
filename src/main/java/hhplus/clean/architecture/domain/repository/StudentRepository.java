package hhplus.clean.architecture.domain.repository;

import hhplus.clean.architecture.domain.entity.Student;

import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(long studentId);

}
