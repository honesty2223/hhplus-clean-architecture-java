package hhplus.clean.architecture.application.usecase;

import hhplus.clean.architecture.application.dto.LectureDto;
import hhplus.clean.architecture.application.dto.RegistrationDto;
import hhplus.clean.architecture.application.dto.RequestDto;
import hhplus.clean.architecture.application.dto.ResponseDto;
import hhplus.clean.architecture.application.exception.LectureAlreadyFullException;
import hhplus.clean.architecture.application.exception.LectureNotFoundException;
import hhplus.clean.architecture.application.exception.StudentAlreadyAppliedException;
import hhplus.clean.architecture.application.exception.StudentNotFoundException;
import hhplus.clean.architecture.application.service.LectureService;
import hhplus.clean.architecture.application.service.RegistrationService;
import hhplus.clean.architecture.application.service.StudentService;
import hhplus.clean.architecture.domain.entity.Lecture;
import hhplus.clean.architecture.domain.entity.Registration;
import hhplus.clean.architecture.domain.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureUseCase {

    private final LectureService lectureService;
    private final RegistrationService registrationService;
    private final StudentService studentService;

    @Autowired
    public LectureUseCase(LectureService lectureService, RegistrationService registrationService, StudentService studentService) {
        this.lectureService = lectureService;
        this.registrationService = registrationService;
        this.studentService = studentService;
    }

    /**
     * 특강 신청 처리
     * 특정 특강을 PESSIMISTIC_WRITE 락 모드로 조회
     *
     * @param requestDto 학생 ID, 특강 ID
     * @throws StudentNotFoundException       학생이 존재하지 않을 경우
     * @throws LectureNotFoundException       특강이 존재하지 않을 경우
     * @throws LectureAlreadyFullException    특강이 이미 정원 초과일 경우
     * @throws StudentAlreadyAppliedException 이미 신청한 학생인 경우
     */
    @Transactional
    public RegistrationDto applyForLecture(RequestDto requestDto) {
        long studentId = requestDto.getStudentId();
        long lectureId = requestDto.getLectureId();

        Lecture lecture = validateLecture(lectureId);
        Student student = validateStudent(studentId);
        validateStudentRegistration(studentId, lectureId);
        lecture.validateLectureCapacity();

        Registration result = registerStudentAndSave(student, lecture);
        return new RegistrationDto(result);
    }

    /**
     * 학생을 등록하고 특강의 현재 신청자 수를 업데이트
     *
     * @param student 등록할 학생 객체
     * @param lecture 업데이트할 특강 객체
     */
    private Registration registerStudentAndSave(Student student, Lecture lecture) {
        Registration result = registrationService.newRegistrationSave(student.getStudentId(), lecture.getLectureId());
        lectureService.updateCurrentEnrollment(lecture);

        return result;
    }

    /**
     * 특강 목록 조회
     *
     * @return 특강 목록
     */
    public List<LectureDto> getAllLectures() {
        return lectureService.findAll().stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 특강 신청 완료 여부 조회
     *
     * @param requestDto 학생 ID, 특강 ID
     * @return 특강 신청 완료 여부 (true: 신청 완료, false: 미신청)
     * @throws StudentNotFoundException 학생이 존재하지 않을 경우
     * @throws LectureNotFoundException 특강이 존재하지 않을 경우
     */
    public ResponseDto getRegistrationStatus(RequestDto requestDto) {
        long studentId = requestDto.getStudentId();
        long lectureId = requestDto.getLectureId();

        validateStudent(studentId);
        validateLecture(lectureId);

        boolean isRegistered = registrationService.isRegistered(studentId, lectureId);
        return new ResponseDto(isRegistered);
    }

    /**
     * 특강 존재 여부를 확인하고, 존재하지 않을 경우 예외를 발생시킴
     *
     * @param lectureId 확인할 특강 ID
     * @return 존재하는 특강 객체
     * @throws LectureNotFoundException 특강이 존재하지 않을 경우
     */
    private Lecture validateLecture(long lectureId) {
        return lectureService.findById(lectureId)
                .orElseThrow(() -> new LectureNotFoundException("해당 특강 ID로 등록된 특강이 없습니다: " + lectureId));
    }

    /**
     * 학생 존재 여부를 확인하고, 존재하지 않을 경우 예외를 발생시킴
     *
     * @param studentId 확인할 학생 ID
     * @return 존재하는 학생 객체
     * @throws StudentNotFoundException 학생이 존재하지 않을 경우
     */
    private Student validateStudent(long studentId) {
        return studentService.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("해당 학생 ID로 등록된 학생이 없습니다: " + studentId));
    }

    /**
     * 학생이 이미 특강에 신청했는지 확인하고, 신청했을 경우 예외를 발생시킴
     *
     * @param studentId 학생 ID
     * @param lectureId 특강 ID
     * @throws StudentAlreadyAppliedException 학생이 이미 특강에 신청한 경우
     */
    private void validateStudentRegistration(long studentId, long lectureId) {
        if (registrationService.isRegistered(studentId, lectureId)) {
            throw new StudentAlreadyAppliedException("이미 해당 특강에 신청한 학생입니다: " + studentId);
        }
    }
}
