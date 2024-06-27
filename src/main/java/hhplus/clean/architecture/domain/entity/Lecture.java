package hhplus.clean.architecture.domain.entity;

import hhplus.clean.architecture.application.exception.LectureAlreadyFullException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "lecture_date")
    private LocalDate lectureDate;

    @Column(name = "max_capacity")
    @ColumnDefault("30")
    private int maxCapacity;

    @Column(name = "current_enrollment")
    @ColumnDefault("0")
    private int currentEnrollment;

    /**
     * 특강의 정원이 초과되었는지 확인하고, 초과되었을 경우 예외를 발생시킴
     *
     * @throws LectureAlreadyFullException 특강의 정원이 초과된 경우
     */
    public void validateLectureCapacity() {
        if (this.getCurrentEnrollment() >= this.getMaxCapacity()) {
            throw new LectureAlreadyFullException("해당 특강의 정원이 초과되었습니다: " + this.getLectureId());
        }
    }
}
