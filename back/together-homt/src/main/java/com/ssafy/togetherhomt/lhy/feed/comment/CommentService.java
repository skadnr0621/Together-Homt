package com.ssafy.togetherhomt.lhy.feed.comment;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.comment.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;


    public List<CommentDto> getFeedComments(Feed feed) {
        List<CommentDto> commentList = new ArrayList<>();
        for (Comment comment : commentRepository.findByFeed(feed))
            commentList.add(new CommentDto(comment));
        return commentList;
    }
}
