package com.gdg.group13.task;

import java.time.LocalDate;

import com.gdg.group13.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
public class TaskEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer todoId;
  private String title;
  private String description;
  private String imageUrl;
  private Boolean checkBox;

  @Enumerated(value = EnumType.STRING)
  private Tag tag;

  public TaskEntity(
    Integer todoId, String title, String description, String imageUrl, Boolean checkBox, Tag tag) {
    this.todoId = todoId;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.checkBox = checkBox;
    this.tag = tag;
  }
}

