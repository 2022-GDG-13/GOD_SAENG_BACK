package com.gdg.group13.post;

import java.util.List;
import java.util.Set;

import com.gdg.group13.post.PostEntity;
import com.gdg.group13.task.Tag;
import com.gdg.group13.task.TaskEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSingleDto {

    private PostEntity post;
    private Boolean godSaeng;
    private String userName;
    private List<TaskEntity> taskEntityList;
    private Set<Tag> tags;

    public PostSingleDto(PostEntity post, Boolean godSaeng, String userName,
        List<TaskEntity> taskEntityList, Set<Tag> tags) {
        this.post = post;
        this.godSaeng = godSaeng;
        this.userName = userName;
        this.taskEntityList = taskEntityList;
        this.tags = tags;
    }
}
