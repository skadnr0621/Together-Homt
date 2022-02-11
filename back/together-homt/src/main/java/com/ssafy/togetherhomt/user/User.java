package com.ssafy.togetherhomt.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.comment.Comment;
import com.ssafy.togetherhomt.feed.like.Like;
import com.ssafy.togetherhomt.user.follow.Follow;
import com.ssafy.togetherhomt.user.group.Group;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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

    @Column(name = "image_path")
    private String imagePath;

    // 나를 팔로우 하고 있는 사람들
    @OneToMany(mappedBy = "following")
    @ToString.Exclude
    private List<Follow> follower;

    // 내가 팔로우 하고 있는 사람들
    @OneToMany(mappedBy = "follower")
    @ToString.Exclude
    private List<Follow> following;


    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    @ToString.Exclude
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    @ToString.Exclude
    private List<Like> likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnoreProperties({"user"})
//    @ToString.Exclude
//    private List<Record> records = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    @JsonIgnoreProperties({"user"})
//    private List<Attendance> attendances = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

