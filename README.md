<h1 align="center" style="max-width: 100%;">
  <img width="550" alt="Logo" src="README.assets/Logo.png" style="max-width: 100%;" /><br/>
  <a href="http://www.3.38.103.222.com">www.togetherhomt.com</a>
</h1>



<h3 align="center">
 SSAFY 2학기 공통프로젝트 - 대전 2반 6팀 <남(욱)탓 금지><br/></h3>



<p align="center">
  <b>22.1.5 ~ 22.2.18</b><br /></p>



|                                   |                            |                               |                               |
| --------------------------------- | -------------------------- | ----------------------------- | ----------------------------- |
| :white_check_mark: Desktop Web    | :white_check_mark: Docker  | :white_check_mark: JavaScript | :white_check_mark: Java       |
| :white_check_mark: Mobile Web     | :white_check_mark: Jenkins | :white_check_mark: Vue        | :white_check_mark: Springboot |
| :white_check_mark: Responsive Web | :white_check_mark: Jira    | :white_check_mark: VueX       | :white_check_mark: JPA        |



## ✨ 투게더홈트의 Link!

### 팀 노션

https://www.notion.so/2-6-Home-fd1bb076ef5048c7a5b6c942df5d7b22

### 와이어프레임

https://www.figma.com/file/fSi9NPvyqNqPmjeloZaBYZ/Untitled?node-id=219%3A937

### API명세서

https://www.notion.so/API-SNS-030cbdc0fef44900a31e4d78a57518a6





<table style="table-layout: fixed; overflow-wrap: break-word;">
  <tbody>
    <tr>
      <td><a href="#" target="_blank"><img width="500" src="README.assets/voice.gif" alt="iPhone demo" style="max-width: 100%;" /></a></td>
      <td><a href="#" target="_blank"><img width="500" src="README.assets/choice.gif" alt="Music app demo" style="max-width: 100%;" /></a></td>
      <td rowspan="2" style="display:flex-column;"><img height="250" src="README.assets/detail3.gif" alt="Full page demo" style="max-width: 100%;" /><div style="height:15px;"></div></div><img height="250" src="README.assets/draw5.png" alt="Full page demo" style="max-width: 100%;" /></td>
    </tr>
    <tr>
      <td colspan="2"><a href="#" target="_blank"><img width="1000" src="README.assets/main5.png" alt="Parallax demo" style="max-width: 100%;" /></a></td>
    </tr>
  </tbody>
</table>
<h6 align="center">
  www.prismfilmstudio.com <a href="#"></a>
</h6>






-----


## ✨ Stack

|     Main     |     Sub      |                         Description                          |
| :----------: | :----------: | :----------------------------------------------------------: |
|     User     |   account    | 회원가입 / 로그인 / 회원정보 수정 / 비밀번호 찾기 / 비밀번호 수정 / 로그아웃 |
|     User     |    group     |               그룹, 멤버관리 생성, 조회, 삭제                |
|     User     |    follow    |               팔로잉, 팔로우 생성, 조회, 삭제                |
|     Feed     |     feed     |            피드 조회, 검색 조회, 생성, 수정, 삭제            |
|     Feed     |   comment    |                    댓글 조회, 생성, 삭제                     |
|     Feed     |     like     |          좋아요, 좋아요 취소, 좋아요 유저 목록 조회          |
|     Feed     |     tag      |                    태그 조회, 생성, 수정                     |
| Notification | notification |                   알림, 메세지, 조회, 생성                   |
|   Exercise   |   exercise   |                  운동 횟수, 시간, 영상 조회                  |
|   Exercise   |    record    |                  달력표시, attendance 연동                   |
|   Exercise   |  attendance  |   운동 장바구니 담기, 완료 표시, record연동, 퇴실완료 조회   |



## 📦 ERD

![erd](README.assets/erd.png)





## 🏃 팀원 정보 및 업무 분담 내역

#### 소속

* ##### SSAFY 대전 2반 6팀

#### 이름

- ##### 김남욱 (팀장, FE)

  - 

- ##### 이호열 (부팀장, BE)

  - 

- ##### 오수경 (FE)

  - 

- ##### 임채은 (FE)

  - 

- ##### 정명수 (BE)

  - 

- #####  이건희 (BE)

  - 피드, 댓글, 좋아요, 태그




## ⚙️ 개요

* ##### 목표 서비스 구현 및 실제 구현 정도

  * 80%
    * 목표 서비스 구현
    * 목표 디자인 구현
    * -20% : Google Teachable Machine을 활용한 학습의 완성도와 코드의 정제화, 속도의 향상이 이루어 지지 않음

* ##### 목차

  * Part 1, 개인
  * Part 2, 공통
  * 마치며



## 🏃 Part 1, 개인

> 개인별 구현 기술과 경험에 대한 회고



## 🏃 Sub 1-1, 김남욱

> 

## 🏃 Sub 1-2, 이호열

> 

## 🏃 Sub 1-3, 이건희

> 처음 접하는 Java, SpringBoot, JPA

## Feed

>> Spring Boot, JPA, Lombok

#### < Entity >

```java
@Entity                                                    // SpringBoot Entity
@Table(name = "feed")                                      // 테이블 명 지정
@Data                                                      // @Getter @Setter @RequiredArgsConstructor...
@AllArgsConstructor                                        // 모든 필드 값을 파라미터로 받는 생성자
@NoArgsConstructor                                         // 파라미터가 없는 기본 생성자
@Builder                                                   // 빌더 생성 가능하게
@ToString(callSuper = true)                                // 시간자동생성 (1)
@EqualsAndHashCode(callSuper = true)                       // 시간자동생성 (2)
public class Feed extends BaseTimeEntity {                 // 시간자동생성 (3)

    @Id                                                    // pk지정
    @Column(name = "feed_id")                              // db의 테이블명
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 시간자동생성 (1)
    private Long feedId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "media_url")                              // DB명 : DB 스네이크 케이스
    private String mediaUrl;                                 // 변수명 : Java 카멜케이스

    @Column(name = "like_cnt")
    private Long likeCnt;

    @ManyToMany
    @JoinTable(name = "feed_tag",                                // 새로 만들어질 테이블의 이름
            joinColumns = @JoinColumn(name = "feed_id"),         // 새로운 테이블의 컬럼명 1
            inverseJoinColumns = @JoinColumn(name = "tag_id"))   // 새로운 테이블의 컬럼명 2
    private List<Tag> tags = new ArrayList<Tag>();

    @ManyToOne(fetch = FetchType.LAZY)   		                     // == 1:N 주인 / Lazy: 지연로딩, 조회할 때 Proxy 사용
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "feed")   		                         // 진짜는 comments.feed
    @JsonIgnoreProperties({"feed"}) 		                         // 무한참조 방지
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "feed")
    @JsonIgnoreProperties({"feed"})
    private List<Like> likes = new ArrayList<>();
}

```



#### < DTO >

```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedListDto {

    @ApiParam(value = "피드아이디")   		                         // Swagger에 표시되는 이름
    private Long feedId;   		

    @ApiParam(value = "유저이름")
    private String username;

    @ApiParam(value = "프로필사진")
    private String profileUrl;

    @ApiParam(value = "피드사진")
    private String mediaUrl;

    @ApiParam(value = "좋아요개수")
    private Long likeCnt;

    @ApiParam(value = "피드내용")
    private String content;

    @ApiParam(value = "태그")
    private List<String> tags;

    @ApiParam(value = "게시 날짜시간")
    private LocalDateTime createdAt;

    @ApiParam(value = "수정 날짜시간")
    private LocalDateTime updatedAt;

    @ApiParam(value = "좋아요 여부")
    private Boolean likeStatus;
}

```



#### < Repository >

```java
          		                          		                          	 // JpaRepo 상속받을 땐 @Repository 생략 가능 
public interface FeedRepository extends JpaRepository<Feed, Long> {      // JpaRepository<Entity 클래스, ID> 상속

    List<Feed> findByUser(User user); 
}
```



#### < Controller >

```java
@Api("피드 관련 기능 접근 방법")                                            // Swagger 컨트롤러 제목
@RestController                                                       // ResponseBody 생략 + Controller
@RequestMapping("/feed")                                              // 공통 URL
public class FeedController {

    private FeedService feedService;

    @Autowired                                                        // 생성자 주입
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }


    //=========================Feed=========================//

    @ApiOperation(value = "피드 등록", notes = "피드 등록")            // Swagger - value: url 옆에, notes: 열면 상단에
    @ApiResponses({                                               // Swagger - Response 코드에 대한 메세지
            @ApiResponse(code = 200, message = "피드 생성에 성공하였습니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다. 계정을 명시하지 않았거나 로그인 정보와 맞지 않습니다. 또는 존재하지 않는 미디어 파일입니다."),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @PostMapping("/feeds") // ResponseEntity<>(message, headers, HttpStatus.OK) ==> ResponseEntity.ok(message)
    public ResponseEntity<?> postFeed(@ModelAttribute("file") FeedDto feedDto, TagDto tagDto){
        return feedService.postFeed(feedDto,tagDto);
    }

    @ApiOperation(value = "피드 전체 조회", notes = "전체 피드를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
	  // RestAPI : 자원에 대한 명시 + GET,POST,PUT,DELETE / Document, Collection, Store, Controller
    @GetMapping("/feeds") 
    public List<FeedDto> getAllFeeds() {
        return feedService.getAllFeeds();
    }

    @ApiOperation(value = "팔로우기반 피드 조회(메인)", notes = "로그인 한 사용자가 팔로우 하는 유저의 피드를 조회하여 목록으로 보여줍니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "팔로우 하는 피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/feeds/follower")
    public List<FeedListDto> getFollowerFeeds() {
        return feedService.getFollowerFeeds();
    }

    @ApiOperation(value = "유저 피드 조회(1)", notes = "내가 작성한 피드를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 사용자의 피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/feeds/profiles/{email}") // Get : PathVariable (O), RequestBody (X)
    public ResponseEntity<List<FeedDto>> getProfileFeeds(@PathVariable String email) {   // 변수명 같을 때는 생략 가능
        return ResponseEntity.ok(feedService.getProfileFeeds(email));
    }

    @ApiOperation(value = "피드 개별 조회", notes = "개별 피드를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/feeds/{feed_id}")
    public FeedListDto getFeed(@PathVariable("feed_id") Long feedId) { // 변수명 다를 때는 지정
        return feedService.getFeed(feedId);
    }

    @ApiOperation(value = "피드리스트 조회(검색)", notes = "전체 피드리스트(검색)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드리스트 조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @GetMapping("/feeds/search")
    public ResponseEntity<List<FeedListDto>> getSearchFeeds() {
        return ResponseEntity.ok(feedService.getSearchFeeds());
    }

    @ApiOperation(value = "유저 피드 조회(2)", notes = "유저가 작성한 피드 리스트(프로필)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저가 작성한 피드 리스트 조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @GetMapping("/feeds/profiles/{email}/temp")
    public ResponseEntity<List<FeedProfileDto>> getProfileFeeds_temp(@PathVariable String email) {
        return ResponseEntity.ok(feedService.getProfileFeeds_temp(email));
    }

    @ApiOperation(value = "피드 수정(조회)", notes = "피드 수정(조회)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 수정에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @GetMapping("/feeds/{feed_id}/temp")
    public ResponseEntity<?> getUpdateFeedInfo(@PathVariable Long feed_id){
        return ResponseEntity.ok(feedService.getUpdateFeedInfo(feed_id));
    }

    @ApiOperation(value = "피드 수정(실행)", notes = "피드 수정(실행)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 수정에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버 에러입니다.")
    })
    @PutMapping("/feeds/{feed_id}")
    public ResponseEntity<?> updateFeed(@PathVariable("feed_id") Long feedId, @RequestBody FeedDto feedDto){
        return feedService.updateFeed(feedId,feedDto);
    }

    @ApiOperation(value = "피드 삭제", notes = "요청한 피드를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드 삭제에 성공하였습니다."),
            @ApiResponse(code = 400, message = "요청한 피드가 없습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @DeleteMapping("/feeds/{feed_id}")
    public ResponseEntity<?> deleteFeed(@PathVariable("feed_id") Long feed_id){
        return feedService.deleteFeed(feed_id);
    }

    //=========================Comment=========================//

    @ApiOperation(value = "피드 댓글 작성", notes = "피드에 댓글을 작성하여 추가합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 피드에 성공적으로 댓글을 작성하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @PostMapping("/{feed_id}/comments")    // Post 요청 - RequestBody + DTO를 통한 입/출력
    public ResponseEntity<?> postComment(@PathVariable("feed_id") Long feed_id, @RequestBody CommentDto commentDto) {
        return feedService.postComment(feed_id, commentDto);
    }

    @ApiOperation(value = "피드 댓글 조회", notes = "피드에 등록된 댓글을 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 피드의 댓글 조회에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping("/{feed_id}/comments")
    public ResponseEntity<List<CommentListDto>> getComments(@PathVariable Long feed_id) {
        return ResponseEntity.ok(feedService.getComments(feed_id));
    }

    @ApiOperation(value = "피드 댓글 삭제", notes = "피드에 작성한 댓글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청한 댓글의 삭제에 성공하였습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @DeleteMapping("/{feed_id}/comments/{comment_id}")  // RestAPI : document + collection + document (delete)
    public ResponseEntity<?> deleteComment(@PathVariable("comment_id") Long comment_id) {
        return feedService.deleteComment(comment_id);
    }
}
```



----



### <Sub : 미디어파일 업로드>

#### Controller

```java
@PostMapping("/feeds")
public ResponseEntity<?> postFeed(@ModelAttribute FeedDto feedDto, TagDto tagDto){
  return feedService.postFeed(feedDto,tagDto);
}
```

* @RequestMapping 하위 어노테이션, @ModelAttribute



#### DTO

```java
...
 @ApiParam(value = "피드 첨부파일")
    private MultipartFile media;
...
```

* MultipartFile 속성의 객체를 받음



#### postFeed(Service)

```java
@Transactional                                                                // 작업이 완료되지 않으면 리셋
public ResponseEntity<?> postFeed(FeedDto feedDto, TagDto tagDto){

  MultipartFile multipartFile = feedDto.getMedia();

                                                                              // 미디어파일 필수 : 없으면 return
  if(multipartFile == null || multipartFile.isEmpty()){
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

                                                                              // 현재 날짜 폴더만들어서 저장
  String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
  String uploadFilePath = config.getUploadFilePath()+currentDate+"/";  // globalConfig에서 알맞는 실제 저장 경로 반환

  // 랜덤이름 + . 확장자 가져오기
  String postfix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1, multipartFile.getOriginalFilename().length());                         // 확장자 따기
  String filename = UUID.randomUUID().toString() + "." + postfix;      // UUID 생성후 확장자 붙이기

  // 폴더 없으면 만들기
  File folder = new File(uploadFilePath);                              // 실제 경로 파일 만들기
  if(!folder.isDirectory()){                                           // 그게 디렉토리가 아니면
    folder.mkdirs();                                                   // 경로가 지정된 폴더 만들기
  }

  String pathname = uploadFilePath+filename;                           // 실제 경로 + 파일 이름
  String resourcePathname = config.getUploadResourcePath()+currentDate+"/"+filename; // 보여줄 경로 + 파일

  // url 경로 출력
  System.out.println("resourcePathname = " + resourcePathname);      

  // 새로 파일 만들기
  File dest = new File(pathname);                                      // 실제경로+파일이름 으로 된 파일 만들기
  try {
    multipartFile.transferTo(dest);                                    // 입력된 파일로 바꿔치기 
  } catch (IOException e) {
    e.printStackTrace();
  }

  // Get User
  PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();   // 인증정보에 principal 주체 가져오기
  User userTemp = principalDetails.getUser();  // principal에 저장된 유저 정보 가져오기
  User user = userRepository.findByEmail(userTemp.getEmail());//그냥유저랑은 다르니 userTemp이메일로 제대로된 유저 객체 찾기

  // Tag
  List<Tag> resTags = new ArrayList<>();                                // 태그 담아둘 리스트

  for (String tempName : tagDto.getTagList()) {                         // 들어온 태그 돌면서
    Tag tempTag = tagRepository.findByName(tempName);                   // 해당 이름으로 태그 찾기
    if (tempTag != null) {                                              // 이미 있으면
      resTags.add(tempTag);                                             // 태그리스트에 있는 것 추가
    } else {
      Tag newTag = Tag.builder()                                        // 없으면 만들기
        .name(tempName)
        .build();
      tagRepository.save(newTag);                                       
      resTags.add(newTag);                            
    }
  }

  // Create Feed
  Feed feed = Feed.builder()
    .title(feedDto.getTitle())
    .content(feedDto.getContent())
    .likeCnt(0L)
    .mediaUrl(resourcePathname)                                          // 보여줄 경로만 저장
    .user(user)
    .tags(resTags)         // many to many이기 때문에 그냥 리스트로 저장하면 알아서 tag-feed 테이블에 저장(feed가 key의 주인임)
    .build();

  feedRepository.save(feed);
  return new ResponseEntity<>(HttpStatus.OK);
}
```



#### GlobalConfig

```java
@Configuration
public class GlobalConfig {

    private final ApplicationContext context;                                     // 실행되고 있는 환경 파악
    private final ResourceLoader resourceLoader;                                  // resource에 있는 파일 가져오기

    @Autowired
    public GlobalConfig(ApplicationContext context, ResourceLoader resourceLoader) {
        this.context = context;
        this.resourceLoader = resourceLoader;
    }

    private String uploadPath;                                                    // 실제 저장 경로
    private String resourcePath;                                                  // 보여주기 경로

    @PostConstruct                                                          // 빈 생성 후, 서비스 들어가기 전 1번만 실행
    public void init(){
        String[] activeProfiles = context.getEnvironment().getActiveProfiles(); //배포중이라면,activeProfiles=[dep]
        String activeProfile = "local";                                     // 기본 프로필은 local로 설정
        if(!ObjectUtils.isEmpty(activeProfiles)){                           // 배포 중이라면
            activeProfile = activeProfiles[0];                              // "dep"으로 설정
        }
        String resourcePath = String.format("classpath:globals/global-%s.properties",activeProfile); //설정파일경로
        try{
            Resource resource = resourceLoader.getResource(resourcePath);   // 설정경로로 자원 설정
            Properties properties = PropertiesLoaderUtils.loadProperties(resource); // 설정파일 값 가져오기

            this.uploadPath = properties.getProperty("uploadFile.path"); // 실제 저장 경로 설정=uploadPath
            this.resourcePath = properties.getProperty("uploadFile.resourcePath"); // 보여주기 경로설정=resourcePath
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }
                                                                                  // Getter
    public String getUploadPath() {
        return uploadPath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getUploadFilePath() {
        return uploadPath;
    }

    public String getUploadResourcePath() {
        return resourcePath;
    }
}
```



#### global-dep.properties / global-local.properties

```properties
# global-dep.properties                                                  // 배포서버 설정
uploadFile.path= /home/resources/                                        // 실제 저장 경로 설정
uploadFile.resourcePath= /upload/                                        // 볼 수 있는 url 설정

# global-local.properties                                                // 로컬서버 설정
uploadFile.path= C:/                                                     // 실제 저장 경로 설정
uploadFile.resourcePath= /upload/                                        // 볼 수 있는 url 설정
```



----



#### < 조회 >

> 많은 조회가 있지만 기본 적인 흐름은 비슷하여, 팔로우 기반 조회 및 댓글 조회를 대표로 함

##### 팔로우 기반 조회

```java
public List<FeedListDto> getFollowerFeeds() {  

  // Get User
  PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  User userTemp = principalDetails.getUser();
  User user = userRepository.findByEmail(userTemp.getEmail());

  // Return Feed List
  List<FeedListDto> result = new ArrayList<>();

  // Get Followings by User
  List<Follow> followings = followRepository.findByFollower(user);//팔로우repo를 통해 user가 팔로워인 follow관계 가져오기

  // Add Following's Feed
  for (Follow following : followings) {                           // 팔로우 관계 순회하면서

    User toUser = following.getFollowing();                       // 팔로잉 하는 사람 가져오기
    List<Feed> feeds = toUser.getFeeds();                         // 그사람의 피드들 가져오기

    for (Feed feed : feeds) {                                     // 피드 돌면서
      FeedListDto feedListDto = new FeedListDto();                // Dto에 담아주고

      feedListDto.setFeedId(feed.getFeedId());                   
      feedListDto.setUsername(feed.getUser().getUsername());
      feedListDto.setProfileUrl(feed.getUser().getImagePath());
      feedListDto.setMediaUrl(feed.getMediaUrl());
      feedListDto.setLikeCnt(feed.getLikeCnt());
      feedListDto.setContent(feed.getContent());
      feedListDto.setEmail(feed.getUser().getEmail());

      List<String> tempTags = new ArrayList<>();//태그는 Tag클래스가 아닌 String형태로 담아줘야하기때문에 Name들만 따서 담아줌
      for (Tag tag : feed.getTags()) {
        tempTags.add(tag.getName());
      };
      feedListDto.setTags(tempTags);

      feedListDto.setCreatedAt(feed.getCreatedAt());
      feedListDto.setUpdatedAt(feed.getUpdatedAt());

      // Check like_status
      Like like_flag = likeRepository.findByUserAndFeed(user, feed);    //likeRepo에 like관계 확인후 체크하고 담기
      if (like_flag != null) {
        feedListDto.setLikeStatus(true);
      } else {
        feedListDto.setLikeStatus(false);
      }

      result.add(feedListDto);
    }
  }

  // Add My Feed
  List<Feed> feeds = feedRepository.findByUser(user);               // 로그인한 유저의 피드도 필요
  for (Feed feed : feeds) {
    FeedListDto feedListDto = new FeedListDto();

    feedListDto.setFeedId(feed.getFeedId());
    feedListDto.setUsername(feed.getUser().getUsername());
    feedListDto.setProfileUrl(feed.getUser().getImagePath());
    feedListDto.setMediaUrl(feed.getMediaUrl());
    feedListDto.setLikeCnt(feed.getLikeCnt());
    feedListDto.setContent(feed.getContent());
    feedListDto.setEmail(feed.getUser().getEmail());

    List<String> tempTags = new ArrayList<>();
    for (Tag tag : feed.getTags()) {
      tempTags.add(tag.getName());
    };
    feedListDto.setTags(tempTags);

    feedListDto.setCreatedAt(feed.getCreatedAt());
    feedListDto.setUpdatedAt(feed.getUpdatedAt());

    // Check like_status
    Like like_flag = likeRepository.findByUserAndFeed(user, feed);
    if (like_flag != null) {
      feedListDto.setLikeStatus(true);
    } else {
      feedListDto.setLikeStatus(false);
    }

    result.add(feedListDto);
  }

  // Sorting By Created time
  result.sort(new Comparator<FeedListDto>() {
    @Override
    public int compare(FeedListDto o1, FeedListDto o2) {
      return o2.getFeedId().intValue() - o1.getFeedId().intValue();   // pk값을 통해 정렬, Long값이라 int로 변환 후 계산
    }
  });

  return result;
}
```



##### 댓글 조회

```java
public List<CommentListDto> getComments(Long feed_id) {

  // Get Feed
  Optional<Feed> feed = feedRepository.findById(feed_id);
  if (!feed.isPresent()) {
    return null;
  }
  List<CommentListDto> result = new ArrayList<>();

  for (Comment comment : commentRepository.findByFeed(feed.get())) {   //댓글Repo에서 feed로 댓글들 찾고 순회
    CommentListDto commentListDto = new CommentListDto();

    commentListDto.setCommentId(comment.getCommentId());
    commentListDto.setProfileUrl(comment.getUser().getImagePath());
    commentListDto.setEmail(comment.getUser().getEmail());
    commentListDto.setUsername(comment.getUser().getUsername());
    commentListDto.setContent(comment.getContent());
    commentListDto.setCreatedAt(comment.getCreatedAt());

    result.add(commentListDto);
  }

  return result;                                                        // 댓글들 Dto에 넣어 반환
}
```





#### < 수정 >

> 대표로 피드 수정

##### 피드 수정

```java
@Transactional                                                 // 모든 작업이 성공하지 않으면 모든 로직을 처리 하지 않음
public ResponseEntity<?> updateFeed(Long feedId, FeedDto feedDto) {     

  // Get Feed
  Optional<Feed> optFeed = feedRepository.findById(feedId);    
  if (!optFeed.isPresent()) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  Feed feed = optFeed.get();          

  List<Tag> resTags = new ArrayList<>();

  for (String tempName : feedDto.getTags()) {                  
    Tag tempTag = tagRepository.findByName(tempName);          
    if (tempTag != null) {
      resTags.add(tempTag);
    } else {
      Tag newTag = Tag.builder()
        .name(tempName)
        .build();
      tagRepository.save(newTag);
      resTags.add(newTag);
    }
  }

  // Update Feed
  feed.setTitle(feedDto.getTitle());
  feed.setContent(feedDto.getContent());
  feed.setTags(resTags);                                   // key의 주인이라 바로 넣어주면 관계매핑 알아서 됨
  feedRepository.save(feed);

  return new ResponseEntity<>(HttpStatus.OK);
}
```



#### < 삭제 >

##### 피드 삭제

```java
@Transactional
    public ResponseEntity<?> deleteFeed(Long feed_id) {

        // Get Feed
        Optional<Feed> feed = feedRepository.findById(feed_id);
        if (!feed.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Check Existence
        if (!feed.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            feedRepository.delete(feed.get());                            // JPA 피드Repo delete메서드 활용 삭제
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
```



## Tag

#### < Entity >

```java
@Entity
@Table(name = "tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "name")
    private String name;

    @Builder.Default//@Builder는 기본적으로 생성되있는것을 받지않는데 현재리스트로초기화해주는중,따라서이거무시하라고@Builder.Default
    @ManyToMany(mappedBy = "tags",cascade = CascadeType.ALL)  // 키의 주인은 feeds, feed가사라질때같이사라지도록 Cascade
    private List<Feed> feeds = new ArrayList<>();   // ManytoMany라서 초기 값은 빈 리스트로 설정
}
```



#### < DTO >

```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

    @ApiParam(value = "태그 이름")
    private List<String> tagList;       // String 값으로 왔다갔다 할 수 있어야 하고, 여러개를 동시에 받을 수 있어 List<String>
}
```



#### < Repository >

```java
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);         // 이름으로 찾기
}
```



## Like

#### < Entity >

```java
@Entity
@Table(name = "`like`")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne                                 // 여러개의 유저가 있을 수 있고
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne                                 // 여러개의 피드가 있을 수 있고 있다.
    @JoinColumn(name = "feed_id")
    private Feed feed;
}
```



#### < Repository > 

```java
public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByUserAndFeed(User user, Feed feed);                 // user, feed 두개의 매핑으로 Like관계 찾기 가능
    List<Like> findAllByFeed(Feed feed);
}
```



#### < Controller > 

```java
@Api("'좋아요' 기능 접근 방법")
@RestController
@RequestMapping("/feed")                                      // 피드 기능 속에 like
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    //=========================Like=========================//

    @ApiOperation(value = "좋아요 추가", notes = "좋아요 추가")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드를 성공적으로 조회했습니다."),
            @ApiResponse(code = 400, message = "이미 좋아요 상태입니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @PostMapping({"/{feed_id}/likes"})                         // REST: Document + Collection [POST]
    public ResponseEntity likeFeed(@PathVariable Long feed_id) {
        return likeService.likeFeed(feed_id);
    }

    @ApiOperation(value = "좋아요한 사람들 조회", notes = "좋아요한 사람들 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회를 성공적으로 실행했습니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @GetMapping({"/{feed_id}/likes"})                         // REST: Document + Collection [GET]
    public ResponseEntity<List<LikeUserDto>> getLikeUsers(@PathVariable Long feed_id) {
        return ResponseEntity.ok(likeService.getLikeUsers(feed_id));
    }

    @ApiOperation(value = "좋아요 취소", notes = "좋아요 취소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "피드를 성공적으로 실행했습니다."),
            @ApiResponse(code = 400, message = "요청한 피드가 없거나, 좋아요 상태가 아닙니다."),
            @ApiResponse(code = 500, message = "서버에러가 발생했습니다.")
    })
    @DeleteMapping({"/{feed_id}/likes"})                         // REST: Document + Collection [Delete]
    public ResponseEntity<?> unlikeFeed(@PathVariable Long feed_id) {
        return likeService.undoLikeFeed(feed_id);
    }
}
```



#### < Service > 

> 대표 : 좋아요 취소, 이외 Feed 참조

##### 좋아요 취소

```java
@Transactional
public ResponseEntity<?> undoLikeFeed(Long feed_id) {

  // Get Feed
  Optional<Feed> optFeed = feedRepository.findById(feed_id);// Check Like Status
  if (!optFeed.isPresent()) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);     // 좋아요가 없다면 잘못된 요청처리
  }

  Feed feed = optFeed.get();

  // Get User
  User me = commonService.getLoginUser();

  // Get Like
  Like like = likeRepository.findByUserAndFeed(me, feed);

  if (like != null) {

    likeRepository.delete(like);

    // Update Feed's like_cnt
    feed.setLikeCnt(feed.getLikeCnt() - 1);                     
    feedRepository.save(feed);

    return new ResponseEntity<>(HttpStatus.OK);
  } else {

    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
```



## Comment

#### < Entity >

```java
@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;
}
```





#### < DTO >

```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentListDto {

    @ApiParam(value = "댓글 id")
    private Long commentId;

    @ApiParam(value = "프로필사진")
    private String profileUrl;

    @ApiParam(value = "유저이메일")
    private String email;

    @ApiParam(value = "유저이름")
    private String username;

    @ApiParam(value = "댓글내용")
    private String content;

    @ApiParam(value = "댓글 작성 시각")
    private LocalDateTime createdAt;

}
```



#### < Repository >

```java
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByFeed(Feed feed);
```

## 🏃 Sub 1-4, 정명수

> 

## 🏃 Sub 1-5, 오수경

> 

## 🏃 Sub 1-6, 임채은

> 



## 🏃 Part 2, 공통

> 6명 한 팀의 공통 기록



## 📖 Reference

formdata - https://developer.mozilla.org/ko/docs/Web/API/FormData

그림판 - https://github.com/shlee0882/painting-js

blob Data 변환 - https://codebb.tistory.com/22

배경 테마 - https://codepen.io/trending

Axios - https://github.com/axios/axios

Base -  https://edu.ssafy.com

컬러바코드 - https://happycoding.io/gallery/movie-colors/index

대표 색채 - https://airows.com/culture/color-palettes-from-famous-movie-scenes

티어 시스템 - https://www.acmicpc.net/, https://solved.ac/
