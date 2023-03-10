package com.example.oauth2.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private String email;

    private String password;

    private String name;

    private String firstName;

    private String lastName;

    private String nickname;

    private String image;

    private Boolean withdrawal;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Member(AuthProvider provider,
                   String providerId,
                   String email,
                   String password,
                   String name,
                   String firstName,
                   String lastName,
                   String nickname,
                   String image,
                   Role role) {
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.image = image;
        this.withdrawal = false;
        this.role = role;
    }

    public static Member of(AuthProvider provider,
                            String providerId,
                            String email,
                            String name,
                            String firstName,
                            String lastName,
                            String nickname,
                            String image) {
        return new Member(provider,
                providerId,
                email,
                null,
                name,
                firstName,
                lastName,
                nickname,
                image,
                Role.ROLE_USER);
    }

    public static Member of(String email,
                            String password,
                            String firstName,
                            String lastName,
                            String nickname,
                            String image,
                            PasswordEncoder encoder) {
        return new Member(AuthProvider.LOCAL,
                null,
                email,
                encoder.encode(password),
                firstName + " " + lastName,
                firstName,
                lastName,
                nickname,
                image,
                Role.ROLE_USER);
    }

    public static Member createDefaultAdmin(PasswordEncoder encoder) {
        return new Member(AuthProvider.LOCAL,
                null,
                "admin@admin.com",
                encoder.encode("admin"),
                "admin",
                null,
                null,
                "admin",
                null,
                Role.ROLE_ADMIN);
    }

    public void update(String name,
                       String firstName,
                       String lastName,
                       String nickname,
                       String image) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.image = image;
    }
}
