package ru.mariknv86.medhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mariknv86.medhelper.dto.request.RequestDTO;
import ru.mariknv86.medhelper.dto.response.ResultTrueDto;
import ru.mariknv86.medhelper.model.Request;
import ru.mariknv86.medhelper.service.RequestService;

@RestController
@RequestMapping(value = "/api/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getRequestById(@PathVariable Long id) {
        Request request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/search")
    @CrossOrigin
    public ResponseEntity<?> getRequestByQuestion(@RequestParam(name="question") String query) {
        return ResponseEntity.ok(requestService.getByQuestion(query));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> addNewRequest(@RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok(requestService.addNewRequest(requestDTO));
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> editRequestById(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        Request request = requestService.getById(id);
        requestService.editRequest(requestDTO, request);
        return ResponseEntity.ok(new ResultTrueDto());
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteRequestById(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok(new ResultTrueDto());
    }

}
