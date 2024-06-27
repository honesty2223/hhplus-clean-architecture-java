package hhplus.clean.architecture.application.service;

import hhplus.clean.architecture.domain.entity.Lecture;
import hhplus.clean.architecture.domain.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    /**
     * 특정 ID에 해당하는 특강을 조회
     *
     * @param lectureId 특강 ID
     * @return 해당 ID를 가진 특강 객체, 없으면 Optional.empty()
     */
    public Optional<Lecture> findById(long lectureId) {
        return lectureRepository.findById(lectureId);
    }

    /**
     * 모든 특강을 조회
     *
     * @return 모든 특강 객체를 포함한 리스트
     */
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    /**
     * 특강의 현재 신청자 수를 업데이트
     *
     * @param lecture 업데이트할 특강 객체
     */
    public void updateCurrentEnrollment(Lecture lecture) {
        Lecture updateLecture = new Lecture(lecture.getLectureId(), lecture.getLectureName(), lecture.getLectureDate(), lecture.getMaxCapacity(),lecture.getCurrentEnrollment() + 1);
        Lecture result = lectureRepository.save(updateLecture);
    }
}
