package com.ssafy.togetherhomt.article;

import com.ssafy.togetherhomt.config.articleConfig.GlobalConfig;
import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute("file") ArticleDto articleDto){

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principalDetails.getUsername();
        articleDto.setUsername(username);
        String result = articleService.create(articleDto);

        if (result.equals("success"))  return ResponseEntity.ok("success");
        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }

}
