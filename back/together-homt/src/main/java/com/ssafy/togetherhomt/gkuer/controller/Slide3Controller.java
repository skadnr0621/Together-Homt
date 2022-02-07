package com.ssafy.togetherhomt.gkuer.controller;

import com.ssafy.togetherhomt.gkuer.dto.FollowListDto;
import com.ssafy.togetherhomt.gkuer.dto.ProfileFeedDto;
import com.ssafy.togetherhomt.gkuer.service.Slide3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("슬라이드3")
@RestController
@RequestMapping("/slide3")
public class Slide3Controller {

    private Slide3Service slide3Service;

    @Autowired
    public Slide3Controller(Slide3Service slide3Service) {
        this.slide3Service = slide3Service;
    }

    @ApiOperation(value = "팔로워리스트", notes = "팔로워리스트")
    @ApiResponses({
            @ApiResponse(code = 500, message = "서버 에러입니다."),
            @ApiResponse(code = 200, message = "팔로워 리스트 조회 성공")
    })
    @GetMapping("/followerlist")
    public ResponseEntity<List<FollowListDto>> followerlist(@RequestBody String email) {
        List<FollowListDto> result = slide3Service.followerlist(email);

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "팔로잉리스트", notes = "팔로잉리스트")
    @ApiResponses({
            @ApiResponse(code = 500, message = "서버 에러입니다."),
            @ApiResponse(code = 200, message = "팔로잉 리스트 조회 성공")
    })
    @GetMapping("/followinglist")
    public ResponseEntity<List<FollowListDto>> followinglist(@RequestBody String email) {
        List<FollowListDto> result = slide3Service.followinglist(email);

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "팔로워 + 팔로잉 리스트", notes = "앞 리스트 : 팔로워 리스트 / 뒤 리스트 : 팔로잉 리스트")
    @ApiResponses({
            @ApiResponse(code = 500, message = "서버 에러입니다."),
            @ApiResponse(code = 200, message = "팔로워 + 팔로잉 리스트 조회 성공")
    })
    @GetMapping("/followlist")
    public ResponseEntity<HashMap<String,List<FollowListDto>>> followlist(@RequestBody String email) {
        List<FollowListDto> followerResult = slide3Service.followerlist(email);
        List<FollowListDto> followingResult = slide3Service.followinglist(email);

        HashMap<String, List<FollowListDto>> result = new HashMap<>();
        result.put("followerlist",followerResult);
        result.put("followinglist",followingResult);

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "유저가 작성한 피드 리스트", notes = "유저가 작성한 피드 리스트")
    @ApiResponses({
            @ApiResponse(code = 500, message = "서버 에러입니다."),
            @ApiResponse(code = 200, message = "유저가 작성한 피드 리스트 조회 성공")
    })
    @GetMapping("/profilefeed")
    public ResponseEntity<List<ProfileFeedDto>> profilefeed(@RequestBody String email) {
        List<ProfileFeedDto> result = slide3Service.profilefeed(email);

        return ResponseEntity.ok(result);
    }
}
