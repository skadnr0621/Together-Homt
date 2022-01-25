package com.ssafy.togetherhomt.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.follow.Follow;
import com.ssafy.togetherhomt.like.Like;
import lombok.*;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // 나를 팔로우 하고 있는 사람들
    @OneToMany(mappedBy = "following")
    private List<Follow> follower;

    // 내가 팔로우 하고 있는 사람들
    @OneToMany(mappedBy = "follower")
    private List<Follow> following;


    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "like_id")
    @ToString.Exclude
    private List<Like> like_articles = new ArrayList<>();
}
