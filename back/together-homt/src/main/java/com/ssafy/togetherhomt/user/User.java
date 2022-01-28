package com.ssafy.togetherhomt.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.exercise.Record;
import com.ssafy.togetherhomt.feed.comment.Comment;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.like.Like;
import com.ssafy.togetherhomt.user.follow.Follow;
import lombok.*;

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

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Like> likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Record> records = new ArrayList<>();


<<<<<<< HEAD
//    @OneToMany(mappedBy = "fromId")
=======
    @OneToMany(mappedBy = "fromId")
    @JsonIgnoreProperties({"user"})
    private List<Notification> getNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "toId")
    @JsonIgnoreProperties({"user"})
    private List<Notification> sendNotifications = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
>>>>>>> 3a7f29ffc77186a690bde5121d7c56f9c9edd0e3
//    @JsonIgnoreProperties({"user"})
//    private List<Notification> getNotifications = new ArrayList<>();
//
//    @OneToMany(mappedBy = "toId")
//    @JsonIgnoreProperties({"user"})
<<<<<<< HEAD
//    private List<Notification> sendNotifications = new ArrayList<>();
=======
//    private List<TodayExercise> todayExercises = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnoreProperties({"user"})
//    private List<Attendance> attendances = new ArrayList<>();
//
//
>>>>>>> 3a7f29ffc77186a690bde5121d7c56f9c9edd0e3
}

