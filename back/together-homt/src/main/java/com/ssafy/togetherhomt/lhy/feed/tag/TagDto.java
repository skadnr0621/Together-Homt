package com.ssafy.togetherhomt.lhy.feed.tag;

import com.ssafy.togetherhomt.feed.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Setter;

public class TagDto {

    private String tagName;

    public TagDto(Tag tag) {
        tagName = tag.getName();
    }

}
