package myproj.api.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginInput {

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "잘못된 이메일양식")
    private String email;

    @NotBlank
    @Size(min = 4, message = "4자리 이상이여야 합니다.")
    private String password;
}
