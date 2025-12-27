package ru.acorneo.progprobs.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.acorneo.progprobs.Command;
import ru.acorneo.progprobs.data.dto.UserDTO;
import ru.acorneo.progprobs.data.model.User;
import ru.acorneo.progprobs.data.records.MessageResponse;
import ru.acorneo.progprobs.repositories.UserRepository;
import ru.acorneo.progprobs.util.PasswordEncoder;

@Service
public class CreateUserService implements Command<UserDTO, MessageResponse> {
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        String message = "Successfully created userr: " + currentUser.toString();
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(message));
    }
}
