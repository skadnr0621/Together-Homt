package com.ssafy.togetherhomt.feed;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.config.media.GlobalConfig;
import com.ssafy.togetherhomt.feed.comment.Comment;
import com.ssafy.togetherhomt.feed.comment.CommentDto;
import com.ssafy.togetherhomt.feed.comment.CommentRepository;
import com.ssafy.togetherhomt.feed.media.Media;
import com.ssafy.togetherhomt.feed.media.MediaRepository;
import com.ssafy.togetherhomt.user.User;
import com.ssafy.togetherhomt.user.UserRepository;
import com.ssafy.togetherhomt.user.follow.Follow;
import com.ssafy.togetherhomt.user.follow.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FeedService {

    public FeedRepository feedRepository;
    public UserRepository userRepository;
    public CommentRepository commentRepository;
    public MediaRepository mediaRepository;
    public FollowRepository followRepository;
    private GlobalConfig config;

    @Autowired
    public FeedService(FeedRepository feedRepository, UserRepository userRepository, CommentRepository commentRepository,
                       MediaRepository mediaRepository, FollowRepository followRepository, GlobalConfig config) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.mediaRepository = mediaRepository;
        this.followRepository = followRepository;
        this.config = config;
    }

    public List<FeedDto> all() {

        // Return Feed List
        List<FeedDto> feeds = new ArrayList<>();

        for (Feed tempFeed : feedRepository.findAll()) {

            FeedDto feedDto = new FeedDto();

            feedDto.setId(tempFeed.getFeed_id());
            feedDto.setTitle(tempFeed.getTitle());
            feedDto.setContent(tempFeed.getContent());
            feedDto.setMedia_url(tempFeed.getMedia_url());
            feedDto.setLike_cnt(tempFeed.getLike_cnt());
            feedDto.setUserName(tempFeed.getUser().getUsername());
            feedDto.setTag(tempFeed.getTag());

            feeds.add(feedDto);
        }

        return feeds;
    }

    public List<FeedDto> main() {

        // Get User
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        // Return Feed List
        List<FeedDto> feeds = new ArrayList<>();

        // Get Followings by User
        List<Follow> followings = followRepository.findByFollower(user);

        // Add Following's Feed
        for (Follow following : followings) {
            User toUser = following.getFollowing();
            List<Feed> tempFeeds = toUser.getFeeds();
            for (Feed tempFeed : tempFeeds) {
                FeedDto feedDto = new FeedDto();

                feedDto.setId(tempFeed.getFeed_id());
                feedDto.setTitle(tempFeed.getTitle());
                feedDto.setContent(tempFeed.getContent());
                feedDto.setMedia_url(tempFeed.getMedia_url());
                feedDto.setLike_cnt(tempFeed.getLike_cnt());
                feedDto.setUserName(tempFeed.getUser().getUsername());
                feedDto.setTag(tempFeed.getTag());

                feeds.add(feedDto);
            }
        }

        return feeds;
    }

    public List<FeedDto> getPersonalFeed(String email) {

        User user = userRepository.findByEmail(email);

        List<FeedDto> feeds = new ArrayList<>();
        for (Feed feed : feedRepository.findByUser(user)) {
            FeedDto feedDto = new FeedDto();

            feedDto.setId(feed.getFeed_id());
            feedDto.setTitle(feed.getTitle());
            feedDto.setContent(feed.getContent());
            feedDto.setMedia_url(feed.getMedia_url());
            feedDto.setLike_cnt(feed.getLike_cnt());
            feedDto.setUserName(feed.getUser().getUsername());
            feedDto.setTag(feed.getTag());

            feeds.add(feedDto);
        }

        return feeds;
    }

    public List<CommentDto> getComment(Long feed_id) {

        Optional<Feed> feed = feedRepository.findById(feed_id);


        List<CommentDto> comments = new ArrayList<>();
        for (Comment comment : commentRepository.findByFeed(feed.get())) {
            CommentDto commentDto = new CommentDto();
            commentDto.setComment_id(comment.getComment_id());
            commentDto.setContent(comment.getContent());
            commentDto.setFeed_id(comment.getFeed().getFeed_id());
            commentDto.setUsername(comment.getUser().getUsername());

            comments.add(commentDto);
        }

        return comments;
    }

    public String postComment(Long feed_id, CommentDto commentDto) {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        Optional<Feed> feed = feedRepository.findById(feed_id);
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .feed(feed.get())
                .user(user)
                .build();
        commentRepository.save(comment);

        return "success";
    }

    public String deleteComment(Long comment_id) {
        commentRepository.deleteById(comment_id);
        return "success";
    }

    public String create(FeedDto feedDto){

        MultipartFile multipartFile = feedDto.getMedia();

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
        Media media = Media.builder()
                .fileOriginName(multipartFile.getOriginalFilename())
                .videoPath(uploadFilePath)
                .videoUrl(pathname)
                .build();
        mediaRepository.save(media);

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userTemp = principalDetails.getUser();
        User user = userRepository.findByEmail(userTemp.getEmail());

        Feed feed = Feed.builder()
                .title(feedDto.getTitle())
                .content(feedDto.getContent())
                .like_cnt(0L)
                .media_url(resourcePathname)
                .user(user)
                .tag(feedDto.getTag())
                .build();

        feedRepository.save(feed);
        return "success";
    }

    public String feedDelete(Long feed_id) {
        Optional<Feed> feed = feedRepository.findById(feed_id);
        feedRepository.delete(feed.get());
        return "success";
    }
}
