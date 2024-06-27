package hhplus.clean.architecture.application.dto;

import hhplus.clean.architecture.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto {
    private Long lectureId;
    private String lectureName;
    private LocalDate lectureDate;
    private int maxCapacity;
    private int currentEnrollment;

    public LectureDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.lectureDate = lecture.getLectureDate();
        this.maxCapacity = lecture.getMaxCapacity();
        this.currentEnrollment = lecture.getCurrentEnrollment();
    }
}
