package com.project.contactmessage.service;

import com.project.contactmessage.domain.ContactMessage;
import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.mapper.ContactMessageMapper;
import com.project.contactmessage.messages.Messages;
import com.project.contactmessage.repository.ContactMessageRepository;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.response.business.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMessageMapper contactMessageMapper;

    public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest) {

        ContactMessage contactMessage = contactMessageMapper.requestToContactMessage(contactMessageRequest);
        ContactMessage savedData = contactMessageRepository.save(contactMessage);

        return ResponseMessage.<ContactMessageResponse>builder().message(Messages.CONTACT_MESSAGE_CREATED_SUCCESSFULLY).httpStatus(HttpStatus.CREATED)
                .object(contactMessageMapper.contactMessageToResponse(savedData)).build();
    }

    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {

        Pageable pageable = returnPageable(page,size,sort,type);

        return contactMessageRepository.findAll(pageable).map(contactMessageMapper::contactMessageToResponse);

    }

    public Page<ContactMessageResponse> getBySubject(String subject, int page, int size, String sort, String type) {

        Pageable pageable = returnPageable(page,size,sort,type);

        return contactMessageRepository.findBySubject(subject,pageable).map(contactMessageMapper::contactMessageToResponse);

    }

    private Pageable returnPageable(int page, int size, String sort, String type) {
        Pageable pageable;
        if (Objects.equals(type, "desc")) {
            return pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        } else {
            return pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        }
    }

    public Page<ContactMessageResponse> getByEmail(String email, int page, int size, String sort, String type) {

        Pageable pageable = returnPageable(page,size,sort,type);

        return contactMessageRepository.findByEmail(email,pageable).map(contactMessageMapper::contactMessageToResponse);
    }

    public String deleteById(Long contactMessageId) {

        if (contactMessageRepository.existsById(contactMessageId)){
            contactMessageRepository.deleteById(contactMessageId);
            return Messages.CONTACT_MESSAGE_DELETED_SUCCESSFULLY;
        }else {
            throw new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE);
        }
    }
}
