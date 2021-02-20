package coderslab.pl.taskplanner.exceptions.globalExceptionHandler;

import coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final String VALIDATION_MESSAGE = "Validation failed";

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        log.info("info");
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(VALIDATION_MESSAGE);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        violations.forEach(apiError::addValidationError);
        log.info(apiError.toString());

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgument(MethodArgumentNotValidException ex) {
        log.info("info");
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(VALIDATION_MESSAGE);
        List<FieldError> violations = ex.getBindingResult().getFieldErrors();
        violations.forEach(apiError::addValidationError);
        log.info(apiError.toString());

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
