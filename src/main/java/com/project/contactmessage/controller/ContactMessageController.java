package com.project.contactmessage.controller;

import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.service.ContactMessageService;
import com.project.payload.response.business.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contactMessage")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("/save") //http://localhost:8080/contactMessage/save + POST + JSON
    public ResponseMessage<ContactMessageResponse> save(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {
        return contactMessageService.save(contactMessageRequest);
    }

    @GetMapping("/getAll")
    public Page<ContactMessageResponse> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
                                               @RequestParam(value = "type", defaultValue = "desc") String type) {
        return contactMessageService.getAll(page, size, sort, type);
    }

    @GetMapping("/getBySubject")
    public Page<ContactMessageResponse> getBySubject(@RequestParam(value = "subject") String subject,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                                     @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
                                                     @RequestParam(value = "type", defaultValue = "desc") String type) {

        return contactMessageService.getBySubject(subject, page, size, sort, type);
    }

    @GetMapping("/getByEmail")
    public Page<ContactMessageResponse> getByEmail(@RequestParam(value = "email") String email,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
                                                   @RequestParam(value = "type", defaultValue = "desc") String type) {

        return contactMessageService.getByEmail(email, page, size, sort, type);
    }

    @DeleteMapping("/deleteById/{contactMessageId}")
    public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactMessageId) {
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }

}
