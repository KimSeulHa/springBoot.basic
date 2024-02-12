package com.study.springBoot.basic.day4.service;

import com.study.springBoot.basic.day4.domain.entity.User;
import com.study.springBoot.basic.day4.domain.model.UserForm;
import com.study.springBoot.basic.day4.domain.repository.UserRepository;
import com.study.springBoot.basic.day4.exception.CustomException;
import com.study.springBoot.basic.day4.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean login(UserForm userForm){
        boolean result = true;
        if(!userRepository.existsByUserId(userForm.getUserId())){
            result = false;
            throw new RuntimeException("존재하지 않는 아이디입니다.");
        }

        User user = userRepository.findByUserId(userForm.getUserId()).get();
        if(!passwordEncoder.matches(userForm.getPassword(),user.getPassword())){
            result = false;
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
        return result;
    }

    public String join(UserForm userForm){
        if(userRepository.findByUserId(userForm.getUserId()).isPresent()){
            //throw new RuntimeException("존재하는 아이디입니다.");
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            User user = userRepository.save(
                    User.builder()
                        .userId(userForm.getUserId())
                        .name(userForm.getName())
                        .phone(userForm.getPhone())
                        .password(passwordEncoder.encode(userForm.getPassword()))
                        .build()
            );
        }

        return "회원가입에 성공했습니다.";
    }

    public User getMyInfo(String userId){
        return userRepository.findByUserId(userId).get();
    }
}
