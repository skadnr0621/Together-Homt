package com.ssafy.togetherhomt.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role; // ROLE_USER, ROLE_ADMIN

    @Column(name = "username")
    private String username;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "profile_url")
    private String profile_url;

}
