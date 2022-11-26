package myproj.api.member.entity;

import lombok.*;
import myproj.api.member.type.CurrentStatus;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;
    private String password;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private CurrentStatus status;

    private ZonedDateTime regDate;
    private ZonedDateTime recentDate;
}
