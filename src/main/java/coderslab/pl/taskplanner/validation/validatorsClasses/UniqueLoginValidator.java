package coderslab.pl.taskplanner.validation.validatorsClasses;

import coderslab.pl.taskplanner.entities.User;
import coderslab.pl.taskplanner.services.UserService;
import coderslab.pl.taskplanner.validation.validators.UniqueLogin;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@RequiredArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin,String> {

    private final UserService userService;

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        User user=userService.findByLogin(login);
        return user==null;
    }
}
