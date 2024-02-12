package com.study.springBoot.basic.day4.security;

import com.study.springBoot.basic.day4.domain.model.UserForm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private static final long EXPIRATION_DATE = 1000 * 60 * 60;

    public String generationToken(UserForm userForm){
        Claims claims = Jwts.claims().setSubject(userForm.getUserId());

        Date now = new Date();
        Date ExpireDate = new Date(now.getTime()+EXPIRATION_DATE);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(ExpireDate)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    /**
     * token으로 jwt 클레임 정보 가져오기
     * @param token
     * @return
     */
    public Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        /**
         * parseClaimsJws()라는 메서드를 사용하고 있는 걸 볼 수 있는데,
         * 이거 대신에 parseClaimsJwt()를 사용하면 오류가 발생한다.
         * 이는, 우리가 처음에 토큰을 생성할 때 signWith을 통해서 서명을 진행했기 때문에
         * 복호화 시에도 서명에 대한 검증을 진행해야 하기 때문이다.
         * (Jwt()의 경우 서명 검증 없이 단순히 헤더와 클레임만 추출함)
         * parseClaimsJwt()을 사용하고 싶다면 토큰 생성 시에
         * signWith을 통해서 서명에 대한 정보를 넘겨주지 않으면 된다.
         *
         */
    }

    /**
     * 토큰으로 사용자 ID 가져오기
     * @param token
     * @return
     */
    public String getUserId(String token){
        return parseClaims(token).getSubject();
    }

    /**
     * 유효한 token인지 확인
     * @param token
     * @return
     */
    public boolean checkValidToken(String token){
        //유효하면 true, 아니면 false
        return !parseClaims(token).getExpiration().before(new Date());
    }
}
