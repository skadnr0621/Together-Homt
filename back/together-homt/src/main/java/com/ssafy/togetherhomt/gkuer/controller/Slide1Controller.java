package com.ssafy.togetherhomt.gkuer.controller;

import com.ssafy.togetherhomt.gkuer.dto.FeedListDto;
import com.ssafy.togetherhomt.gkuer.dto.UserListDto;
import com.ssafy.togetherhomt.gkuer.service.Slide1Service;
import com.ssafy.togetherhomt.user.info.SignupDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("슬라이드1")
@RestController
@RequestMapping("/slide1")
public class Slide1Controller {

    private Slide1Service slide1Service;

    @Autowired
    public Slide1Controller(Slide1Service slide1Service) {
        this.slide1Service = slide1Service;
    }

    @ApiOperation(value = "전체 유저리스트", notes = "전체 유저리스트")
    @ApiResponses({
            @ApiResponse(code = 500, message = "서버 에러입니다."),
            @ApiResponse(code = 200, message = "유저리스트 조회 성공")
    })
    @GetMapping("/userlist")
    public ResponseEntity<List<UserListDto>> userlist() {
        List<UserListDto> result = slide1Service.userlist();

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "전체 피드리스트", notes = "전체 피드리스트")
    @ApiResponses({
            @ApiResponse(code = 500, message = "서버 에러입니다."),
            @ApiResponse(code = 200, message = "피드리스트 조회 성공")
    })
    @GetMapping("/feedlist")
    public ResponseEntity<List<FeedListDto>> feedlist() {
        List<FeedListDto> result = slide1Service.feedlist();

        return ResponseEntity.ok(result);
    }
}
