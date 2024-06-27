package hhplus.clean.architecture;

import hhplus.clean.architecture.application.dto.RegistrationDto;
import hhplus.clean.architecture.application.dto.RequestDto;
import hhplus.clean.architecture.application.service.LectureService;
import hhplus.clean.architecture.application.usecase.LectureUseCase;
import hhplus.clean.architecture.domain.entity.Lecture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // 각 테스트 메소드 실행 후 컨텍스트 초기화
@Execution(ExecutionMode.CONCURRENT) // 병렬 실행 설정
public class LectureUseCaseConcurrencyTest {

    @Autowired
    private LectureUseCase lectureUseCase;

    @Autowired
    private LectureService lectureService;

    @Test
    public void testConcurrentLectureApplication() throws InterruptedException {
        int numThreads = 100; // 동시에 처리할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // 동시에 여러 요청을 보내서 동시성 문제를 재현
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);
        for (int i = 0; i < numThreads; i++) {
            long studentId = i + 1;
            long lectureId = 1L;

            RequestDto requestDto = new RequestDto();
            requestDto.setStudentId(studentId);
            requestDto.setLectureId(lectureId);

            executorService.submit(() -> {
                try {
                    RegistrationDto result = lectureUseCase.applyForLecture(requestDto);
                    successCount.incrementAndGet();
                    System.out.println("등록 성공한 학생 ID : " + result.getId().getStudentId());
                } catch (Exception e) {
                    failCount.incrementAndGet();
                }
            });
        }

        // 모든 작업이 완료될 때까지 기다림
        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            executorService.shutdownNow(); // 작업이 완료되지 않았다면 강제 종료
        }

        System.out.println("성공한 학생 수 : " + successCount.get());
        System.out.println("실패한 학생 수 : " + failCount.get());
        Optional<Lecture> result = lectureService.findById(1L);
        result.ifPresent(lecture -> assertThat(lecture.getCurrentEnrollment()).isEqualTo(30));
    }
}