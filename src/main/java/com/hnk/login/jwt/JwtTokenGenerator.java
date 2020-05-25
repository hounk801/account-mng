package com.hnk.login.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hnk.login.entity.UserInfo;
import com.hnk.login.entity.UserInfoHolder;

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
    private static final long EXPIRE_TIME = 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "thisissecret";
    private static final String USERNAME = "userName";
    private static final String EMAIL = "email";
    private static final String USERID = "userId";

    public static String encode(UserInfo userInfo) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", TYPE);
        header.put("alg", JWT_SECRET_ALGO);
        return JWT.create()
                .withHeader(header)
                .withClaim(USERNAME, userInfo.getUserName())
                .withClaim(EMAIL, userInfo.getEmail())
                .withClaim(USERID, userInfo.getUserId())
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean decode(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userName = jwt.getClaim(USERNAME).asString();
            String email = jwt.getClaim(EMAIL).asString();
            String userId = jwt.getClaim(USERID).asString();
            UserInfo userInfo = new UserInfo(userId, userName, email);
            UserInfoHolder.setUserInfo(userInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
