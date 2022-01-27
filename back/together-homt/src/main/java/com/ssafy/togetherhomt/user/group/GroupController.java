package com.ssafy.togetherhomt.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public String create(@RequestBody GroupDto groupDto) {
        return groupService.create(groupDto);
    }

    @PostMapping("/setting")
    public String setting(@RequestBody GroupSettingDto groupSettingDto) {
        return groupService.setting(groupSettingDto);
    }

    @GetMapping("/check")
    public GroupDto check(@RequestBody String email) {
        return groupService.check(email);
    }
}
