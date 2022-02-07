package com.ssafy.togetherhomt.lhy.feed.comment;

import com.ssafy.togetherhomt.feed.Feed;
import com.ssafy.togetherhomt.feed.comment.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService2 {

    private CommentRepository2 commentRepository2;


    public List<CommentDto2> getFeedComments(Feed feed) {
        List<CommentDto2> commentList = new ArrayList<>();
        for (Comment comment : commentRepository2.findByFeed(feed))
            commentList.add(new CommentDto2(comment));
        return commentList;
    }
}
