package hhplus.clean.architecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private int maxCapacity;

    @Column(name = "current_enrollment")
    private int currentEnrollment;

}
