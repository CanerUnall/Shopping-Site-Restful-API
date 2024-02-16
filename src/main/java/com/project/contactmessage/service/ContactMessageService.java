package com.project.contactmessage.service;

import com.project.contactmessage.domain.ContactMessage;
import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.mapper.ContactMessageMapper;
import com.project.contactmessage.messages.Messages;
import com.project.contactmessage.repository.ContactMessageRepository;
import com.project.exception.ResourceNotFoundException;
import com.project.exception.WrongFormatException;
import com.project.payload.response.business.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    /*public ResponseMessage<List<ContactMessage>> getBetweenDates(String beginDateString, String endDateString) {

        try {
            LocalDate beginDate = LocalDate.parse(beginDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            List<ContactMessage> allContactMessages = contactMessageRepository.findMessagesBetweenDates(beginDate,endDate);

            return ResponseMessage.<List<ContactMessage>>builder().object(allContactMessages).message(Messages.HERE_ARE_ALL_MESSAGES).httpStatus(HttpStatus.OK).build();

        } catch (Exception e) {
            throw new WrongFormatException(Messages.WRONG_DATE_MESSAGE);
        }


    }*/

    public ResponseMessage<List<ContactMessageResponse>> getBetweenDates(String beginDateString, String endDateString) {

        try {
            LocalDate beginDate = LocalDate.parse(beginDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            List<ContactMessage> allContactMessages = contactMessageRepository.findMessagesBetweenDates(beginDate,endDate);

            List<ContactMessageResponse> allContactMessagesResponse = contactMessageMapper.listContactMessagesToContactMessagesResponseList(allContactMessages);

            return ResponseMessage.<List<ContactMessageResponse>>builder().object(allContactMessagesResponse)
                    .message(Messages.HERE_ARE_ALL_MESSAGES).httpStatus(HttpStatus.OK).build();

        } catch (Exception e) {
            throw new WrongFormatException(Messages.WRONG_DATE_MESSAGE);
        }
    }

    /*public List<ContactMessage> getBetweenTimes(String startHourString, String startMinuteString, String endHourString, String endMinuteString) {
        try {
            int startHour = Integer.parseInt(startHourString);
            int startMinute = Integer.parseInt(startMinuteString);
            int endHour = Integer.parseInt(endHourString);
            int endMinute = Integer.parseInt(endMinuteString);

            return contactMessageRepository.findMessagesBetweenTimes(startHour,startMinute,endHour,endMinute);
        } catch (NumberFormatException e) {
            throw new WrongFormatException(Messages.WRONG_TIME_MESSAGE);
        }

    }*/
    public List<ContactMessageResponse> getBetweenTimes(String startHourString, String startMinuteString, String endHourString, String endMinuteString) {
        try {
            int startHour = Integer.parseInt(startHourString);
            int startMinute = Integer.parseInt(startMinuteString);
            int endHour = Integer.parseInt(endHourString);
            int endMinute = Integer.parseInt(endMinuteString);

            List<ContactMessage> allContactMessages = contactMessageRepository.findMessagesBetweenTimes(startHour,startMinute,endHour,endMinute);

            return contactMessageMapper.listContactMessagesToContactMessagesResponseList(allContactMessages);

        } catch (NumberFormatException e) {
            throw new WrongFormatException(Messages.WRONG_TIME_MESSAGE);
        }

    }


    /*public ResponseMessage<ContactMessage> getById(Long contactMessageId) {

        ContactMessage foundedMessage = contactMessageRepository.findById(contactMessageId).orElseThrow(()->
                new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE));

        return ResponseMessage.<ContactMessage>builder().object(foundedMessage).message(Messages.HERE_YOUR_MESSAGE).httpStatus(HttpStatus.OK).build();

    }*/
    public ResponseMessage<ContactMessageResponse> getById(Long contactMessageId) {

        ContactMessage foundedMessage = contactMessageRepository.findById(contactMessageId).orElseThrow(()->
                new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE));

        ContactMessageResponse contactMessageResponse = contactMessageMapper.contactMessageToResponse(foundedMessage);

       return ResponseMessage.<ContactMessageResponse>builder().object(contactMessageResponse).message(Messages.HERE_YOUR_MESSAGE).httpStatus(HttpStatus.OK).build();
    }


}
