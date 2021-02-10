package coderslab.pl.taskplanner.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/home")
public class HomeController {

    private static String HELLO_MESSAGE="Welcome in task planner";

@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String homeController(){
    return HELLO_MESSAGE;
}
}
