package com.project.service.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component//uc125
public class PageableHelper08uc {
    public Pageable getPageableWithProperties(int page, int size, String sort, String type){
       Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());//uc127
        if(Objects.equals(type, "desc")){
            pageable = PageRequest.of(page, size, Sort.by(sort).descending());//uc129 equels ile null komtrolü yapiliyor
            //burada iki yapi esitmi Objects.equals(type, "desc" yani burada exception almadan kontrol yapmis oluyoruz
        }//uc128
        return pageable;//uc130
    }//uc126
}//uc124
/* todo uc131 bu clas UserService02uc klasina injection yapilacak */

/*(Objects.equals(type, "desc") type degeri null olarak gelirse null point exception aliriz bu nedenle equals metodunu yaziyoruz*/