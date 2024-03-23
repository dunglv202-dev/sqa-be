package vn.edu.ptit.sqa.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.ptit.sqa.dto.Error;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleInvalidData(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getDetailMessageArguments())[0].toString();

        return Error.builder()
            .error(message)
            .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleConstrainViolation(ConstraintViolationException e) {
        return Error.builder()
            .error(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList().get(0))
            .build();
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Error handleAuthorization() {
        return Error.builder()
            .error("{access.unauthenticated}")
            .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Error handleAccessDenied() {
        return Error.builder()
            .error("{access.forbidden}")
            .build();
    }

    @ExceptionHandler(ClientVisibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleBusinessException(ClientVisibleException e) {
        return Error.builder()
            .error(e.getMessage())
            .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleUnknownError(Exception e) {
        log.error("Uncaught error", e);
        return Error.builder()
            .error("{server.internal_error}")
            .build();
    }
}
