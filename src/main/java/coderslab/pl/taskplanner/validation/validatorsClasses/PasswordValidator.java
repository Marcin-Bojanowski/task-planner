package coderslab.pl.taskplanner.validation.validatorsClasses;

import coderslab.pl.taskplanner.validation.validators.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password,String> {

    private final String PASSWORD_REGEX="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";


    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
