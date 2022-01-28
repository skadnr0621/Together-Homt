package com.ssafy.togetherhomt.user.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("'그룹' 기능 접근 방법")
@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ApiOperation(value = "그룹 등록", notes = "그룹 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "그룹 생성에 성공하였습니다.")
    })
    @PostMapping("/create")
    public String create(@RequestBody GroupDto groupDto) {
        return groupService.create(groupDto);
    }

    @ApiOperation(value = "그룹-유저 등록", notes = "그룹-유저 등록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "그룹-유저 등록에 성공하였습니다.")
    })
    @PostMapping("/setting")
    public String setting(@RequestBody GroupSettingDto groupSettingDto) {
        return groupService.setting(groupSettingDto);
    }

    @ApiOperation(value = "유저-그룹 확인", notes = "유저-그룹 확인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저-그룹 결과조회 성공")
    })
    @GetMapping("/check")
    public GroupDto check(@RequestBody String email) {
        return groupService.check(email);
    }
}
