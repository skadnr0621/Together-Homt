package com.ssafy.togetherhomt.article;

import com.ssafy.togetherhomt.config.articleConfig.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    private GlobalConfig config;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public String create(ArticleDto articleDto){

        MultipartFile multipartFile = articleDto.getVideo();
        if(multipartFile == null || multipartFile.isEmpty()){
            return "fail";
        }
        // 현재 날짜 폴더만들어서 저장
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String uploadFilePath = config.getUploadFilePath()+currentDate+"/";
        // 랜덤이름 + . 확장자 가져오기
        String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1,multipartFile.getOriginalFilename().length());
        String filename = UUID.randomUUID().toString()+"."+prefix;
        // 폴더 없으면 만들기
        File folder = new File(uploadFilePath);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String pathname = uploadFilePath+filename;

        String resourcePathname = config.getUploadResourcePath()+currentDate+"/"+filename;
        // url 경로 출력
        System.out.println("resourcePathname = " + resourcePathname);
        // 새로 파일 만들기
        File dest = new File(pathname);
        try {
            multipartFile.transferTo(dest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 경로
        System.out.println("pathname = " + pathname);

        Video video = Video.builder()
                .fileOriginName(multipartFile.getOriginalFilename())
                .videoPath(uploadFilePath)
                .videoUrl(pathname)
                .build();
        videoRepository.save(video);


        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .videoUrl(resourcePathname)
                .username(articleDto.getUsername())
                .build();
        articleRepository.save(article);




        return "success";
    }
}
