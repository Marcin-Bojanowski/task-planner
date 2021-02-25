package coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror;

import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class AddConstraintViolationsToErrors extends SpringValidatorAdapter {
    public AddConstraintViolationsToErrors() {
        super(Validation.buildDefaultValidatorFactory().getValidator());
    }

    @SuppressWarnings({"rawTypes","unchecked"})
    public void addConstraintViolations(Set<? super ConstraintViolation<?>> violations, Errors errors){
        super.processConstraintViolations((Set) violations,errors);
    }
}
