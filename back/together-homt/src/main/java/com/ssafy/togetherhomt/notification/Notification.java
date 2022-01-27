package com.ssafy.togetherhomt.notification;

import com.ssafy.togetherhomt.baseEntity.BaseTimeEntity;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.group.Group;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseTimeEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notification_id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private User fromId;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private User toId;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
}
