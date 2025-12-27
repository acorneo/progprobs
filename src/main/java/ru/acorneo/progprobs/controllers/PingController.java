package ru.acorneo.progprobs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.acorneo.progprobs.data.records.MessageResponse;
import ru.acorneo.progprobs.services.PingService;

@RestController
@RequestMapping("/api/v1")
public class PingController {
    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/ping")
    public ResponseEntity<MessageResponse> pingResponse() {
        return pingService.execute(null);
    }
}
