package com.ssafy.togetherhomt.user.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("'그룹' 기능 접근 방법")
@RestController
@RequestMapping("/group/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @ApiOperation(value = "그룹 생성", notes = "그룹 생성")
    @ApiResponse(code = 200, message = "그룹 생성에 성공하였습니다.")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody GroupDto groupDto) {
        groupService.create(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "그룹 정보 조회", notes = "그룹의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 존재하지 않는 그룹입니다."),
            @ApiResponse(code = 200, message = "요청한 그룹의 정보를 성공적으로 조회했습니다.")
    })
    @GetMapping
    public ResponseEntity<?> search(@RequestParam Map<String, String> searchOption) {
        if (searchOption.containsKey("name"))
            groupService.search(searchOption.get("name"));
        else
            groupService.search(searchOption);
        return new ResponseEntity<>("현재 개발 중입니다.", HttpStatus.OK);
    }

}
