package com.gytech.Configuration.token;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gytech.Security.entity.UserInfo;
import org.apache.commons.lang.StringUtils;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LQ on 2019/9/11.
 * com.gytech.Configuration.token
 */
public class JwtUtil {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String SIGN_AUTH = "ltmap";

    private static final String PAYLOAD = "payload";

    private static final Integer maxAge = 100 * 60; //分


    public static String createToken(UserInfo userInfo) {
        //签发时间
        Date istDate = new Date();
        //设置过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, maxAge);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("userInfo", JSON.toJSONString(userInfo))
                .withIssuer(SIGN_AUTH)
                .withExpiresAt(expiresDate)
                .withIssuedAt(istDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }


    public static Map<String, Claim> verifyToken(String token) throws RuntimeException{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("凭证过期！");
        }
        return jwt.getClaims();
    }

    public static UserInfo extrack(String token){
        if (StringUtils.isNotBlank(token)) {
            try {
                Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
                return JSON.parseObject(claimMap.get("userInfo").asString(),UserInfo.class);
            } catch (RuntimeException e) {
                return null;
            }
        }
        return null;
    }

}
