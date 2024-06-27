package hhplus.clean.architecture.domain.repository;

import hhplus.clean.architecture.domain.entity.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Lecture> findById(long lectureId);

    List<Lecture> findAll();

    Lecture save(Lecture lecture);

}
