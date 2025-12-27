package ru.acorneo.progprobs.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.acorneo.progprobs.Query;
import ru.acorneo.progprobs.data.records.MessageResponse;

@Service
public class PingService implements Query<Void, MessageResponse> {

    @Override
    public ResponseEntity<MessageResponse> execute(Void input) {
        String message = "Pong!";
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    }

}
