package hhplus.clean.architecture.application.service;

import hhplus.clean.architecture.domain.entity.Registration;
import hhplus.clean.architecture.domain.entity.RegistrationPk;
import hhplus.clean.architecture.domain.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * 특강 신청 완료 여부 조회
     *
     * @param studentId 학생 ID
     * @param lectureId 특강 ID
     * @return 특강 신청 완료 여부 (true, false)
     */
    public boolean isRegistered(long studentId, long lectureId) {
        return registrationRepository.existsById(new RegistrationPk(studentId, lectureId));
    }

    /**
     * 새로운 특강 신청을 저장
     *
     * @param studentId 학생 ID
     * @param lectureId 특강 ID
     */
    public Registration newRegistrationSave(long studentId, long lectureId) {
        Registration newRegistration = new Registration(new RegistrationPk(studentId, lectureId), LocalDate.now());
        return registrationRepository.save(newRegistration);
    }
}