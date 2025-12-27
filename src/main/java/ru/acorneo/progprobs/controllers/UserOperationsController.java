package ru.acorneo.progprobs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.acorneo.progprobs.data.dto.UserDTO;
import ru.acorneo.progprobs.data.records.MessageResponse;
import ru.acorneo.progprobs.services.CreateUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserOperationsController {
    private final CreateUserService createUserService;

    public UserOperationsController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createUserResponse(@RequestBody UserDTO info) {
        return createUserService.execute(info);
    }
}
