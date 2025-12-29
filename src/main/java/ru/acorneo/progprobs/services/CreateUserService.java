package ru.acorneo.progprobs.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.acorneo.progprobs.Command;
import ru.acorneo.progprobs.data.dto.UserDTO;
import ru.acorneo.progprobs.data.model.User;
import ru.acorneo.progprobs.data.model.UserRoles;
import ru.acorneo.progprobs.data.records.MessageResponse;
import ru.acorneo.progprobs.repositories.UserRepository;
import ru.acorneo.progprobs.repositories.UserRolesRepository;
import ru.acorneo.progprobs.util.PasswordEncoder;
import ru.acorneo.progprobs.util.RegistrationConfiguration;

@Service
public class CreateUserService implements Command<UserDTO, MessageResponse> {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public CreateUserService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public ResponseEntity<MessageResponse> execute(UserDTO input) {

        String password = input.getPassword();
        if (password == null) {
            String message = "Provided password is null";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));
        }

        PasswordEncoder encoder = new PasswordEncoder();
        String hashedPassword = encoder.passwordEncoder().encode(password);

        User currentUser = new User();
        currentUser.setEmail(input.getEmail());
        currentUser.setUsername(input.getUsername());
        currentUser.setHashedPassword(hashedPassword);

        userRepository.save(currentUser);
        RegistrationConfiguration config = new RegistrationConfiguration();

        for (String role : config.getRolesGivenUponRegistration()) {
            UserRoles currentRelationship = new UserRoles();
            currentRelationship.setId(currentUser.getId());
            currentRelationship.setRole(role);
            userRolesRepository.save(currentRelationship);
        }

        String message = "Successfully created user: " + currentUser;
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(message));
    }
}
