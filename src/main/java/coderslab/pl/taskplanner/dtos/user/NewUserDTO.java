package coderslab.pl.taskplanner.dtos.user;

import coderslab.pl.taskplanner.validation.groups.BusinessLogic;
import coderslab.pl.taskplanner.validation.validators.Password;
import coderslab.pl.taskplanner.validation.validators.SamePassword;
import coderslab.pl.taskplanner.validation.validators.UniqueEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@SamePassword
public class NewUserDTO {

    @NotBlank
    private String login;
    @NotBlank
    @Email
    @UniqueEmail(groups = BusinessLogic.class)
    private String email;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String rePassword;
}
