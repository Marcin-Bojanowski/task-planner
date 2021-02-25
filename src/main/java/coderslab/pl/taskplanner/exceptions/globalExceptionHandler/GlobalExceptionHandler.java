package coderslab.pl.taskplanner.exceptions.globalExceptionHandler;

import coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror.AddConstraintViolationsToErrors;
import coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror.ApiError;
import coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror.ApiErrorService;
import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final String VALIDATION_MESSAGE = "Validation failed";
    private final ApiErrorService apiErrorService;


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(VALIDATION_MESSAGE);
        BindingResult bindingResult = toBindingResult(ex);
        List<FieldError> violations = bindingResult.getFieldErrors();
        violations.forEach(v -> apiError.addSubError(apiErrorService.getValidationError(v)));

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgument(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(VALIDATION_MESSAGE);
        List<FieldError> violations = ex.getBindingResult().getFieldErrors();
        violations.forEach(v -> apiError.addSubError(apiErrorService.getValidationError(v)));

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    private BindingResult toBindingResult(ConstraintViolationException e) {
        Object object = e.getConstraintViolations().stream().findFirst().get().getLeafBean();
        String objectName = ((PathImpl) e.getConstraintViolations().stream().findFirst().get().getPropertyPath()).getLeafNode().getParent().getName();
        BindingResult bindingResult = new BeanPropertyBindingResult(object, objectName);
        new AddConstraintViolationsToErrors().addConstraintViolations(e.getConstraintViolations(), bindingResult);
        return bindingResult;
    }
}
