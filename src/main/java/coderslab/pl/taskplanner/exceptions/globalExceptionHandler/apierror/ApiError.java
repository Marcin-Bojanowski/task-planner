package coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private List<ApiSubError> subErrors;

    private void addSubError(ApiSubError subError){
        if (subErrors==null){
            subErrors=new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String object,String field, Object rejectValue, String message){
        addSubError(new ApiValidationError(object,field,rejectValue,message));
    }

    public void addValidationError(ConstraintViolation<?> ex){
        this.addValidationError(
                ex.getRootBeanClass().getSimpleName(),
                ((PathImpl) ex.getPropertyPath()).getLeafNode().asString(),
                ex.getInvalidValue(),
                ex.getMessage()
        );
    }

    public void addValidationError(FieldError error){
        this.addValidationError(
                error.getObjectName(),
                error.getField(),
                error.getRejectedValue(),
                error.getDefaultMessage()
        );
    }

}
