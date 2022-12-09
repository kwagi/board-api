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
public final class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private final Long id = 0L;

    private String email;
    private String password;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private CurrentStatus status;

    private ZonedDateTime regDate;
    private ZonedDateTime recentDate;
}
