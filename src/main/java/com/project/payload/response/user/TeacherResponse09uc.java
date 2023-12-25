package com.project.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.LessonProgram;
import com.project.payload.abstracts.BaseUserResponse06lg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter//uc144
@Setter//uc145
@NoArgsConstructor//uc146
@SuperBuilder//uc147
@JsonInclude(JsonInclude.Include.NON_NULL)//uc148
public class TeacherResponse09uc extends BaseUserResponse06lg {

    private Set<LessonProgram> lessonPrograms;//uc149

    private Boolean isAdvisorTeacher; //uc150
}//uc143 uc151<-- StudentResponse10uc class olusturduk
