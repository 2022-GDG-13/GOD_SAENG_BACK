package com.gdg.group13.task;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer todoId;

    private String title;

    private Boolean checkBox;

    @Enumerated(value = EnumType.STRING)
    private Tag tag;

    public TaskEntity(Integer todoId, String title, Boolean checkBox, Tag tag) {
        this.todoId = todoId;
        this.title = title;
        this.checkBox = checkBox;
        this.tag = tag;
    }

}

