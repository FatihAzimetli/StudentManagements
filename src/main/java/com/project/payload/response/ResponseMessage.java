package com.project.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data //32
@AllArgsConstructor//33
@NoArgsConstructor//34
@Builder(toBuilder = true)//35
@JsonInclude(JsonInclude.Include.NON_NULL)//36
public class ResponseMessage<E> {

    private E object; //37
    private String message;//38
    private HttpStatus status;//39
}//31 //99 ile uyumlu olmali ResponseMessage<E>
/*bu klasta ögrenci, ögretmen veya ders hersey döndürebilirz. bu jenerik bir klas ve kolaylik saglar
 E objesinin data tipinde dönecek, buraya hem mesaj hemde obje gelecek*/