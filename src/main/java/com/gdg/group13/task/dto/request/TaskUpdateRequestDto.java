package com.gdg.group13.task.dto.request;

import com.gdg.group13.task.Tag;

import lombok.Getter;

@Getter
public class TaskUpdateRequestDto {
    private String title;
    private String description;
    private String imageUrl;
    private Tag tag;

    public TaskUpdateRequestDto(String title, String description, String imageUrl, Tag tag) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tag = tag;
    }
}
