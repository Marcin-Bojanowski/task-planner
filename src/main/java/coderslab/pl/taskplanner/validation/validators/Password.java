package coderslab.pl.taskplanner.validation.validators;

import coderslab.pl.taskplanner.validation.validatorsClasses.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "The password must have at least 9 characters and contain at least one digit";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
