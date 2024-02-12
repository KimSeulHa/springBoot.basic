package com.study.springBoot.basic.day4.controller;

import com.study.springBoot.basic.day4.domain.entity.User;
import com.study.springBoot.basic.day4.domain.model.UserForm;
import com.study.springBoot.basic.day4.exception.CustomException;
import com.study.springBoot.basic.day4.security.TokenProvider;
import com.study.springBoot.basic.day4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserForm form){
        String token = "로그인 실패";
        if(userService.login(form)){
            token = tokenProvider.generationToken(form);
        }
        return ResponseEntity.ok(token);
    }
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserForm joinForm){
        String result  = userService.join(joinForm);
        System.out.println("join 메소드의 결과는 >> "+result);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getMyInfo")
    public ResponseEntity<User> getMyInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token){
        if(!tokenProvider.checkValidToken(token)){
            throw new RuntimeException("유효한 기간이 지난 토큰입니다.");
        }
        String userId = tokenProvider.getUserId(token);
        return ResponseEntity.ok(userService.getMyInfo(userId));
    }
}
