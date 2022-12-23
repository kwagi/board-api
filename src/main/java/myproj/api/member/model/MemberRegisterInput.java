package myproj.api.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRegisterInput {

    @NotBlank(message = "이메일을 입력하세요.")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "올바른 이메일양식을 입력하세요.")
    @Email(message = "잘못된 이메일양식")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, message = "비밀번호는 4자 이상 가능합니다.")
    private String password;

    @NotBlank(message = "이름을 입력하세요.")
    @Pattern(regexp = "^[가-힣]*$", message = "올바른 이름을 입력하세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력하세요.")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "올바른 전화번호를 입력하세요.(01011112222)")
    private String phone;
}
