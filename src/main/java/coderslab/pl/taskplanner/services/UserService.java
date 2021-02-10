package coderslab.pl.taskplanner.services;

import coderslab.pl.taskplanner.dtos.user.NewUserDTO;
import coderslab.pl.taskplanner.entities.User;
import coderslab.pl.taskplanner.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService {

    private final String ROLE_USER="ROLE_USER";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void saveUser(NewUserDTO newUserDTO){
        User user=new User();
        user.setEmail(newUserDTO.getEmail());
        user.setLogin(newUserDTO.getLogin());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.setEnabled(false);
        user.setLocked(true);
        user.getRoles().add(ROLE_USER);
        userRepository.save(user);

    }
}
