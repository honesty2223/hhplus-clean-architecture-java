package hhplus.clean.architecture.application.exception;

public class LectureAlreadyFullException extends RuntimeException {
    public LectureAlreadyFullException(String message) {
        super(message);
    }
}
