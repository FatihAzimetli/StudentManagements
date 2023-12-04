package com.project.contactMessage.repository;

import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //19 zorunlu degil
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable); //112-113 burada DTO döndürüyor ancak PJO gelir

    Page<ContactMessage> findBySubjectEquals(String subject, Pageable pageable);//124 to 125 Yapi DTO gözüküyor Page<ContactMessageResponse> PJO cevirdik
    //114 bu nedenle Response siliyoruz <ContactMessageResponse>
} //18
//115 ContactMessageController kilasina gittim
//126 -->ContactMessageService