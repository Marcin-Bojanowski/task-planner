package coderslab.pl.taskplanner.validation.validators;

import coderslab.pl.taskplanner.validation.validatorsClasses.UniqueLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueLoginValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {
    String message() default "UniqueLogin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
