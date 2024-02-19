package com.project.contactmessage.mapper;

import com.project.contactmessage.domain.ContactMessage;
import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactMessageMapper {
    //Request Dto ----> pojo
    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest) {

        return ContactMessage.builder().name(contactMessageRequest.getName()).subject(contactMessageRequest.getSubject())
                .message(contactMessageRequest.getMessage()).email(contactMessageRequest.getEmail())
                .dateTime(LocalDateTime.now()).build();

    }

    //pojo ---> response dto
    public ContactMessageResponse contactMessageToResponse(ContactMessage savedData) {

        return ContactMessageResponse.builder().name(savedData.getName()).subject(savedData.getSubject()).message(savedData.getMessage())
                .email(savedData.getEmail()).dateTime(savedData.getDateTime()).build();

    }

    public List<ContactMessageResponse> listContactMessagesToContactMessagesResponseList(List<ContactMessage> allContactMessages) {
        List<ContactMessageResponse> allContactMessagesResponse= new ArrayList<>();
        for (ContactMessage each:allContactMessages){
            allContactMessagesResponse.add(contactMessageToResponse(each));
        }
        return allContactMessagesResponse;

    }
}
