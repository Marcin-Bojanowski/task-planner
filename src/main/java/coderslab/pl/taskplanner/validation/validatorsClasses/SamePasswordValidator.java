package coderslab.pl.taskplanner.validation.validatorsClasses;

import coderslab.pl.taskplanner.dtos.user.NewUserDTO;
import coderslab.pl.taskplanner.validation.validators.SamePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SamePasswordValidator implements ConstraintValidator<SamePassword,NewUserDTO> {
    @Override
    public void initialize(SamePassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewUserDTO newUserDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid=newUserDTO.getPassword().equals(newUserDTO.getRePassword());
        if (!valid){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("samePassword")
                    .addPropertyNode("password").addConstraintViolation();
        }
        return valid;
    }
}
