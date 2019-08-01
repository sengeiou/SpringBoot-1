package com.onejane;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Test
    public void CreateJwt(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("1112914572337090560")
                .setSubject("ashercode")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "codewj")
                .setExpiration(new Date(new Date().getTime()+24*60*60000))
                .claim("role", "admin");
        System.out.println("Bearer "+jwtBuilder.compact());
    }

    @Test
    public void ParseJwtTest(){
        Claims claims = Jwts.parser()
                .setSigningKey("codewj")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTEyOTE0NTcyMzM3MDkwNTYwIiwic3ViIjoiYXNoZXJjb2RlIiwiaWF0IjoxNTU5MTk5OTUxLCJleHAiOjE1NTkyODYzNTEsInJvbGUiOiJhZG1pbiJ9.QKjdjOTnT5Dv-9BVBMJLVTY0NTlQUgDW7GvSxnSamjQ")
                .getBody();
        System.out.println("用户id："+claims.getId());
        System.out.println("用户名："+claims.getSubject());
        System.out.println("登录时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色："+claims.get("role"));
    }
}
