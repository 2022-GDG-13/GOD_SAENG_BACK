package com.gdg.group13.task;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private String title;
    private String description;
    private String imageUrl;
    private Boolean checkBox;

    @Enumerated(value = EnumType.STRING)
    private Tag tag;

    public TaskEntity(LocalDate date, String title, String description, String imageUrl, Boolean checkBox,
        Tag tag) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.checkBox = checkBox;
        this.tag = tag;
    }
}

