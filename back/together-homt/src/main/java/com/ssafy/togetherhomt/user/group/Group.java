package com.ssafy.togetherhomt.user.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.togetherhomt.feed.like.Like;
import com.ssafy.togetherhomt.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_id;

    private String main_category;

    private String middle_category;

    private String sub_category;

    private String name;

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties({"group"})
    private List<User> users = new ArrayList<>();
}
