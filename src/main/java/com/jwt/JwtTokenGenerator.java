package com.jwt;

import com.entity.UserInfo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 10:43 PM
 */
public class JwtTokenGenerator {

    private static final String JWT_SECRET_ALGO = "HS256";
    private static final String TYPE = "Jwt";
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "thisissecret";


//    public String encode(UserInfo token) throws Exception {
//        JwtHeader header = new JwtHeader(scala.Option.apply(JWT_SECRET_ALGO), Option.empty(), Option.empty());
//        Map<String, Object> map = beanTomap(token);
//        JwtClaimsSet();

//        return "";
//    }

    public static String encode(UserInfo userInfo) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", TYPE);
        header.put("alg", JWT_SECRET_ALGO);
        return JWT.create()
                .withHeader(header)
                .withClaim("userName", userInfo.getUserName())
                .withClaim("email", userInfo.getEmail())
                .withClaim("userId", userInfo.getUserId())
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean decode(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
