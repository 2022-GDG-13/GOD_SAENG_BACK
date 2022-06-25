package com.gdg.group13.task.dto.response;

import com.gdg.group13.task.Tag;
import com.gdg.group13.task.TaskEntity;

public class TaskSingleResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private Tag tag;

    public TaskSingleResponseDto(TaskEntity task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.imageUrl = task.getImageUrl();
        this.tag = task.getTag();
    }
}
