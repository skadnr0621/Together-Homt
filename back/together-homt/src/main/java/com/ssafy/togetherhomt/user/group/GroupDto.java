package com.ssafy.togetherhomt.user.group;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    @ApiParam(value = "그룹 대분류")
    private String mainCategory;

    @ApiParam(value = "그룹 중분류")
    private String middleCategory;

    @ApiParam(value = "그룹 소분류")
    private String subCategory;

    @ApiParam(value = "그룹 이름")
    private String name;

}
