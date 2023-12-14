package com.project.service;

import com.project.repository.UserRepository06;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service//l05
@RequiredArgsConstructor//l06
public class UserService02lg {
    private final UserRepository06 userRepository06; //l07
}//l04
