package com.project.contactmessage.repository;

import com.project.contactmessage.domain.ContactMessage;
import com.project.contactmessage.dto.ContactMessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
    Page<ContactMessage> findBySubject(String subject, Pageable pageable);

    Page<ContactMessage> findByEmail(String email, Pageable pageable);
}
