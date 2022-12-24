package myproj.api.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MemberLogoutInput {

    @Email(message = "잘못된 이메일양식")
    private String email;
}
