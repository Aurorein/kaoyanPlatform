package com.kaoyan.commonUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SING = "ldjfklajsfjas";

    public static String tokenProduces(String userId, String userName, String signature, String email,String phone) {
        Map<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        String token = JWT.create().withHeader(map)    //生成token的header这一部分为令牌类型和所使用得签名算法，这个是默认的，可以不写
                .withClaim("userId", userId) //生成token的payload部分，通常是放一些用户信息进去，如id，名字，权限等，千万不要放密码
                .withClaim("username", userName)
                .withClaim("signature", signature)
                .withClaim("email", email)
                .withClaim("phone", phone)
                .withExpiresAt(instance.getTime())   //设token的过期时间
                .sign(Algorithm.HMAC256("ksjfkjsakfjkajk"));  //签名的算法及密钥，这里面的一串字符串是不能泄露的

        return token;//将token打印出来可以看出它是三段式的

    }

    public static void tokenVerify(){

        //在验证过程中会报各种异常：1. 算法不一致 ---这个是最开始验证的
        //                         2. 签名不一致
        //                         3. token过期
        //                         4. 失效的payload

        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ksjfkjsakfjkajk")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTg0MjU5MzIsInVzZXJJZCI6MTExLCJ1c2VybmFtZSI6Ik1hcmtaUVAifQ.PTlOdRG7ROVJqPrA0q2ac7rKFzNNFR3lTMyP_8fIw9Q");

        //解出token里面传入的对象信息
        System.out.println(verify.getClaim("userId").asInt());//因为传入的是int所以需要改成asInt()
        System.out.println(verify.getClaim("username").asString());
        System.out.println("过期时间"+verify.getExpiresAt()); //打印过期时间

    }


    /**
     * 生成Token  header.payload.sign
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //默认7天过期
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });


        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    /**
     *  验证token的合法性
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);  //这一行代码就可以起到验证的作用，因为在验证不匹配时它自动会抛出异常
    }

    /**
     * 获取token的信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }
}
