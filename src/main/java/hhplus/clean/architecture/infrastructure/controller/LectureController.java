package hhplus.clean.architecture.infrastructure.controller;

import hhplus.clean.architecture.application.dto.LectureDto;
import hhplus.clean.architecture.application.dto.RegistrationDto;
import hhplus.clean.architecture.application.dto.RequestDto;
import hhplus.clean.architecture.application.dto.ResponseDto;
import hhplus.clean.architecture.application.usecase.LectureUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    private final LectureUseCase lectureUseCase;

    @Autowired
    public LectureController(LectureUseCase lectureUseCase) {
        this.lectureUseCase = lectureUseCase;
    }

    /**
     * 특강 신청 API
     *
     * @param requestDto 신청 요청 DTO (studentId, lectureId)
     * @return HTTP 상태 코드와 신청 결과를 담은 응답 DTO
     */
    @PostMapping("/apply")
    public ResponseEntity<RegistrationDto> applyForLecture(@RequestBody RequestDto requestDto) {
        RegistrationDto registrationDto = lectureUseCase.applyForLecture(requestDto);
        return ResponseEntity.ok(registrationDto);
    }

    /**
     * 특강 목록 조회 API
     *
     * @return 특강 목록을 포함한 응답
     */
    @GetMapping
    public ResponseEntity<List<LectureDto>> getAllLectures() {
        List<LectureDto> lectures = lectureUseCase.getAllLectures();
        return ResponseEntity.ok(lectures);
    }

    /**
     * 특강 신청 완료 여부 조회 API
     *
     * @param studentId 학생 ID
     * @param lectureId 특강 ID
     * @return 특강 신청 여부를 포함한 응답 (true: 신청 완료, false: 미신청)
     */
    @GetMapping("/application/{studentId}/{lectureId}")
    public ResponseEntity<ResponseDto> getRegistrationStatus(@PathVariable long studentId, @PathVariable long lectureId) {
        RequestDto requestDto = new RequestDto(studentId, lectureId);
        ResponseDto responseDto = lectureUseCase.getRegistrationStatus(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
