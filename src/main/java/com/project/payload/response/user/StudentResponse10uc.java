package com.project.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.entity.concretes.business.LessonProgram;
import com.project.payload.abstracts.BaseUserResponse06lg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter//uc152
@Setter //uc153
@NoArgsConstructor//uc154
@SuperBuilder //uc155
@JsonInclude(JsonInclude.Include.NON_NULL)//uc161


public class StudentResponse10uc extends BaseUserResponse06lg {

    private Set<LessonProgram> lessonProgramSet; //uc156
    private int studentNumber; //uc157
    private String motherName; //uc158
    private String fatherName; //uc159
    private boolean isActive; //uc160
} //uc151
