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
import java.util.List;

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

    /*@GetMapping("getBetweenDates")
    public ResponseMessage<List<ContactMessage>> getBetweenDates(@RequestParam(value = "beginDate") String beginDateString,
                                                                 @RequestParam(value = "enDate") String endDateString){
        return contactMessageService.getBetweenDates(beginDateString,endDateString);
    }*/

    @GetMapping("getBetweenDates")
    public ResponseMessage<List<ContactMessageResponse>> getBetweenDates(@RequestParam(value = "beginDate") String beginDateString,
                                                                 @RequestParam(value = "endDate") String endDateString){
        return contactMessageService.getBetweenDates(beginDateString,endDateString);
    }

    /*@GetMapping("/getBetweenTimes")
    public ResponseEntity<List<ContactMessage>> getBetweenTimes(@RequestParam(value = "startHour") String startHourString,
                                                                @RequestParam(value = "endHour") String endHourString,
                                                                @RequestParam(value = "startMinute") String startMinuteString,
                                                                @RequestParam(value = "endMinute") String endMinuteString){

        List<ContactMessage> allMessages = contactMessageService.getBetweenTimes(startHourString,startMinuteString,endHourString,endMinuteString);

        return ResponseEntity.ok(allMessages);
    }*/

    @GetMapping("/getBetweenTimes")
    public ResponseEntity<List<ContactMessageResponse>> getBetweenTimes(@RequestParam(value = "startHour") String startHourString,
                                                                @RequestParam(value = "endHour") String endHourString,
                                                                @RequestParam(value = "startMinute") String startMinuteString,
                                                                @RequestParam(value = "endMinute") String endMinuteString){

        List<ContactMessageResponse> allMessages = contactMessageService.getBetweenTimes(startHourString,startMinuteString,endHourString,endMinuteString);

        return ResponseEntity.ok(allMessages);
    }

    /*@GetMapping("/getById/{contactMessageId}")
    public ResponseMessage<ContactMessage> getById(@PathVariable Long contactMessageId){

        return contactMessageService.getById(contactMessageId);
    }*/

    @GetMapping("/getById/{contactMessageId}")
    public ResponseMessage<ContactMessageResponse> getById(@PathVariable Long contactMessageId){

        return contactMessageService.getById(contactMessageId);
    }

}