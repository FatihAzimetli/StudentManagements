package com.project.contactMessage.repository;

import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository //19 zorunlu degil
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable); //112-113 burada DTO döndürüyor ancak PJO gelir
    //114 bu nedenle Response siliyoruz <ContactMessageResponse>

    Page<ContactMessage> findBySubjectEquals(String subject, Pageable pageable);//124 to 125 Yapi DTO gözüküyor Page<ContactMessageResponse> PJO cevirdik

    @Query("SELECT c FROM ContactMessage c WHERE FUNCTION ('DATE', c.dateTime) BETWEEN ?1 and ?2") //166 GBQL olarak yazdik
    List<ContactMessage> findMessagesBetweenDates(LocalDate beginDate, LocalDate endDate); //158 GTA hatasi vermiyor kastim kelimeleri anlamiyor


    @Query("SELECT c FROM ContactMessage c WHERE " +
            "(EXTRACT(HOUR FROM c.dateTime) BETWEEN :startH AND :endH) ANT " +
            "(EXTRACT(HOUR FROM c.dateTime) != :startH OR (EXTRACT(MINUTE FROM c.dateTime) >= :startM)) AND " +
            "(EXTRACT(HOUR FROM c.dateTime) != :endH OR (EXTRACT(MINUTE FROM c.dateTime) <= : endM))")//181
    List<ContactMessage> findMessagesBetweenTimes(@Param("startH") int startH, @Param("startM") int startM,
                                                 @Param("endH") int endH, @Param("endM") int endM); //177 ContactMessageServiceden geldik
} //18
//115 ContactMessageController kilasina gittim
//126 -->ContactMessageService
//158 GTA evception önlemek icin ContactMessageService klasina gidiyorum.
//166 (164-165) yazdiklarimiz icin ContactMessageService katindan geldim
//to167 ContactMessageController gittik
//177 numberFormatException firlatiyor GBQL yazmamiz gerekiyor bu yüzden Tekrar contactMessageService gittik ve onu hendil edecegiz