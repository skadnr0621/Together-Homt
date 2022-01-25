package com.ssafy.togetherhomt.feed.like;

import javax.persistence.*;

@Entity
@Table(name = "`like`")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long like_id;
}