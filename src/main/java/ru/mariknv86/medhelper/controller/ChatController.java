package ru.mariknv86.medhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mariknv86.medhelper.dto.request.MessageDTO;
import ru.mariknv86.medhelper.dto.response.ResultTrueDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/chat")
@RequiredArgsConstructor
public class ChatController {

    List<String> chatLog = new ArrayList<>();

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> getChatUpdates() {
        return ResponseEntity.ok(chatLog);
    }

    @PostMapping("/consultant")
    @CrossOrigin
    public ResponseEntity<?> sendMessageFromConsultant(@RequestBody MessageDTO messageDTO) {
        chatLog.add("Консультант: " + messageDTO.getMessage());
        return ResponseEntity.ok(new ResultTrueDto());
    }

    @PostMapping("/client")
    @CrossOrigin
    public ResponseEntity<?> sendMessageFromUser(@RequestBody MessageDTO messageDTO) {
        chatLog.add("Клиент: " + messageDTO.getMessage());
        return ResponseEntity.ok(new ResultTrueDto());
    }


}
