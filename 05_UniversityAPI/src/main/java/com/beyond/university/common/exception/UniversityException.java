package com.beyond.university.common.exception;
import com.beyond.university.common.exception.message.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UniversityException extends RuntimeException {
    private final String type;

    private final HttpStatus status;

    public UniversityException(ExceptionMessage message) {
        super(message.getMessage());
        this.type = message.name();
        this.status = message.getStatus();
    }
}
