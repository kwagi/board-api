package myproj.api.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.experimental.UtilityClass;
import myproj.api.member.entity.Member;
import myproj.api.member.model.MemberLoginToken;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

@UtilityClass
public class JwtUtils {

    private static final String key = "tokenimade";
    private static final String CLAIM_MEMBER_ID = "member_id";

    public static String getIssuer(String token) {

        return JWT.require(Algorithm.HMAC512(key.getBytes(StandardCharsets.UTF_8)))
                .build()
                .verify(token)
                .getIssuer();
    }

    public static MemberLoginToken createToken(Member member) {

        if (member == null) {
            return null;
        }

        LocalDateTime expiredDt = LocalDateTime.now().plusHours(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredDt);

        String token = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim(CLAIM_MEMBER_ID, member.getId())
                .withSubject(member.getName())
                .withIssuer(member.getEmail())
                .sign(Algorithm.HMAC512(key.getBytes(StandardCharsets.UTF_8)));

        return MemberLoginToken.builder()
                .token(token)
                .build();
    }
}
