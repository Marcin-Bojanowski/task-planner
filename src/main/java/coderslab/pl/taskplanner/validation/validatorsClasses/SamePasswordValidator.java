package coderslab.pl.taskplanner.validation.validatorsClasses;

import coderslab.pl.taskplanner.dtos.user.NewUserDTO;
import coderslab.pl.taskplanner.validation.validators.SamePassword;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Slf4j
public class SamePasswordValidator implements ConstraintValidator<SamePassword,NewUserDTO> {

    private final String MESSAGE="SamePassword.newUserDTO.rePassword";
    private final String FIELD="rePassword";

    @Override
    public void initialize(SamePassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewUserDTO newUserDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid=newUserDTO.getPassword().equals(newUserDTO.getRePassword());
        if (!valid){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(MESSAGE)
                    .addPropertyNode(FIELD).addConstraintViolation();
        }
        return valid;
    }
}
