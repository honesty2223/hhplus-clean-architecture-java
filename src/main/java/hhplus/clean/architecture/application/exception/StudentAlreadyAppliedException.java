package hhplus.clean.architecture.application.exception;

public class StudentAlreadyAppliedException extends RuntimeException {
    public StudentAlreadyAppliedException(String message) {
        super(message);
    }
}
