package coderslab.pl.taskplanner.validation.validatorsClasses;

import coderslab.pl.taskplanner.entities.User;
import coderslab.pl.taskplanner.services.UserService;
import coderslab.pl.taskplanner.validation.validators.UniqueEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.findByEmail(email);
        return user==null;
    }
}
