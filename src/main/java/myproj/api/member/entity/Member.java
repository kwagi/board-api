package myproj.api.member.entity;

import lombok.*;
import myproj.api.member.type.CurrentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private final Long id = null;

    private String email;
    private String password;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private CurrentStatus status;

    private LocalDateTime regDate;
    private LocalDateTime recentDate;
}
