package myproj.api.member.service;

import myproj.api.common.response.service.ServiceResult;
import myproj.api.member.model.MemberDeleteInput;
import myproj.api.member.model.MemberLoginInput;
import myproj.api.member.model.MemberLogoutInput;
import myproj.api.member.model.MemberRegisterInput;

public interface MemberService {

    /**
     * 회원가입
     */
    ServiceResult register(MemberRegisterInput memberRegisterInput);

    /**
     * 로그인
     */
    ServiceResult login(MemberLoginInput memberLoginInput);

    /**
     * 로그아웃
     */
    ServiceResult logout(MemberLogoutInput memberLogoutInput);


    /**
     * 회원삭제
     */
    ServiceResult delete(MemberDeleteInput memberDeleteInput);

    ServiceResult authToken(String token);
}
