package hhplus.clean.architecture.infrastructure.jpa;

import hhplus.clean.architecture.domain.entity.Lecture;
import hhplus.clean.architecture.domain.repository.LectureRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLectureRepository extends JpaRepository<Lecture, Long>, LectureRepository {
}
