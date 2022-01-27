package com.ssafy.togetherhomt.user.group;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    @ApiParam(value = "대분류")
    private String main_category;

    @ApiParam(value = "중분류")
    private String middle_category;

    @ApiParam(value = "소분류")
    private String sub_category;

    @ApiParam(value = "이름")
    private String name;
}
