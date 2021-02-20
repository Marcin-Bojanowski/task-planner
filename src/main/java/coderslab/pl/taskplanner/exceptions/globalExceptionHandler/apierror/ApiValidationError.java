package coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror;

import coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror.ApiSubError;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectValue;
    private String message;
}
