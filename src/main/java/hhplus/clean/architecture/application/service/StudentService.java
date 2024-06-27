package hhplus.clean.architecture.application.service;

import hhplus.clean.architecture.domain.entity.Lecture;
import hhplus.clean.architecture.domain.entity.Student;
import hhplus.clean.architecture.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * 특정 ID에 해당하는 학생을 조회
     *
     * @param studentId 학생 ID
     * @return 해당 ID를 가진 학생 객체, 없으면 Optional.empty()
     */
    public Optional<Student> findById(long studentId) {
        return studentRepository.findById(studentId);
    }

}
