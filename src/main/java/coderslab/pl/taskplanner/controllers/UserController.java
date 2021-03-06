package coderslab.pl.taskplanner.controllers;

import coderslab.pl.taskplanner.dtos.user.NewUserDTO;
import coderslab.pl.taskplanner.entities.User;
import coderslab.pl.taskplanner.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.search.SearchTerm;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final String EMAIL_ALREADY_EXISTS_MSG = "Email already exists";

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity saveUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        userService.saveUser(newUserDTO);
        return ResponseEntity.created(URI.create("/api/login")).build();
    }
}
