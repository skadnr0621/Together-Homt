package com.ssafy.togetherhomt.user.group;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupSettingDto {

    @NotNull
    private String email;

    @NotNull
    private String groupName;
}
