package myproj.api.member.controller;

import lombok.RequiredArgsConstructor;
import myproj.api.common.response.ResponseResult;
import myproj.api.common.response.error.ResponseErrors;
import myproj.api.common.response.service.ServiceResult;
import myproj.api.member.model.MemberDeleteInput;
import myproj.api.member.model.MemberLoginInput;
import myproj.api.member.model.MemberLogoutInput;
import myproj.api.member.model.MemberRegisterInput;
import myproj.api.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public final class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/member/register")
    public ResponseEntity<?> register(@RequestBody @Valid MemberRegisterInput memberRegisterInput, Errors errors) {
        List<ResponseErrors> responseErrors = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrors.add(ResponseErrors.of((FieldError) e)));
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = memberService.register(memberRegisterInput);

        return ResponseResult.result(result);
    }

    @PutMapping("/api/member/login")
    public ResponseEntity<?> login(@RequestBody @Valid MemberLoginInput memberLoginInput, Errors errors) {
        List<ResponseErrors> responseErrors = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrors.add(ResponseErrors.of((FieldError) e)));
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = memberService.login(memberLoginInput);

        return ResponseResult.result(result);
    }

    @PostMapping("/api/member/authToken")
    public ResponseEntity<?> authToken(@RequestHeader("token") String token) {
        ServiceResult result = memberService.authToken(token);

        return ResponseResult.result(result);
    }

    @PatchMapping("/api/member/logout")
    public ResponseEntity<?> logout(@RequestBody MemberLogoutInput memberLogoutInput) {
        ServiceResult result = memberService.logout(memberLogoutInput);
        return ResponseResult.result(result);
    }

    @DeleteMapping("/api/member/delete")
    public ResponseEntity<?> delete(@RequestBody @Valid MemberDeleteInput memberDeleteInput, Errors errors) {
        List<ResponseErrors> responseErrors = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(e -> responseErrors.add(ResponseErrors.of((FieldError) e)));
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = memberService.delete(memberDeleteInput);

        return ResponseResult.result(result);
    }
}
