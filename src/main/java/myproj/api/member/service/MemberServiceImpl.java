package myproj.api.member.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import myproj.api.common.response.service.ServiceResult;
import myproj.api.common.utils.JwtUtils;
import myproj.api.common.utils.PasswordUtils;
import myproj.api.member.entity.Member;
import myproj.api.member.model.*;
import myproj.api.member.repository.MemberRepository;
import myproj.api.member.type.CurrentStatus;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ServiceResult register(MemberRegisterInput memberRegisterInput) {

        Optional<Member> optionalMember = memberRepository.findByEmail(memberRegisterInput.getEmail());

        if (optionalMember.isPresent()) {
            return ServiceResult.fail("이메일이 이미 존재합니다.");
        }

        memberRepository.save(Member.builder()
                .email(memberRegisterInput.getEmail())
                .password(PasswordUtils.getEncryptedPassword(memberRegisterInput.getPassword()))
                .name(memberRegisterInput.getName())
                .phone(memberRegisterInput.getPhone())
                .regDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build());

        return ServiceResult.success();
    }

    @Override
    public ServiceResult login(MemberLoginInput memberLoginInput) {

        Optional<Member> optionalMember = memberRepository.findByEmail(memberLoginInput.getEmail());
        Map<String, Object> data = new HashMap<>();

        if (optionalMember.isEmpty()) {
            return ServiceResult.fail("이메일이 존재하지않습니다.");
        } else if (!PasswordUtils.isPasswordEqualBetween(memberLoginInput.getPassword(), optionalMember.get().getPassword())) {
            return ServiceResult.fail("비밀번호가 일치하지않습니다.");
        } else if (optionalMember.get().getStatus() == CurrentStatus.DELETE) {
            return ServiceResult.fail("삭제된 계정입니다.");
        }

        Member member = optionalMember.get();
        MemberLoginToken memberLoginToken = JwtUtils.createToken(member);

        member.setStatus(CurrentStatus.RUNNING);
        member.setRecentDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        data.put("email", member.getEmail());
        data.put("name", member.getName());
        data.put("phone", member.getPhone());
        data.put("status", member.getStatus());
        data.put("regDate", member.getRegDate());
        data.put("recentDate", member.getRecentDate());

        memberRepository.save(member);

        data.put("token", memberLoginToken);
        if (memberLoginToken == null) {
            ServiceResult.fail("토큰 생성에 실패했습니다.");
        }
        return ServiceResult.success(data);
    }

    @Override
    public ServiceResult logout(MemberLogoutInput memberLogoutInput) {
        Optional<Member> optionalMember = memberRepository.findByEmail(memberLogoutInput.getEmail());

        if (optionalMember.isEmpty()) {
            return ServiceResult.fail("이메일이 존재하지않습니다.");
        }

        Member member = optionalMember.get();
        memberRepository.save(member);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult delete(MemberDeleteInput memberDeleteInput) {

        Optional<Member> optionalMember = memberRepository.findByEmail(memberDeleteInput.getEmail());

        if (optionalMember.isEmpty()) {
            return ServiceResult.fail("이메일이 존재하지않습니다.");
        } else if (!PasswordUtils.isPasswordEqualBetween(memberDeleteInput.getPassword(), optionalMember.get().getPassword())) {
            return ServiceResult.fail("비밀번호가 일치하지않습니다.");
        }
        Member member = optionalMember.get();
        member.setStatus(CurrentStatus.DELETE);

        memberRepository.save(member);
        return ServiceResult.success();
    }

    @Override
    public ServiceResult authToken(String token) {
        String email;

        try {
            email = JwtUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ServiceResult.fail("토큰정보가 다릅니다.");
        }

        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isEmpty()) {
            return ServiceResult.fail("이메일이 존재하지않습니다.");
        }

        Member member = optionalMember.get();

        return ServiceResult.success(member);
    }
}

