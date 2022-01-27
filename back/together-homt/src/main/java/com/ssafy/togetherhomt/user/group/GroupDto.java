package com.ssafy.togetherhomt.user.group;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private String main_category;

    private String middle_category;

    private String sub_category;

    private String name;
}
