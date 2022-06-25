package com.gdg.group13.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "daily_task")
@Getter
@NoArgsConstructor
public class DailyTaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer postId;

  private Integer taskId;

  public DailyTaskEntity(Integer postId, Integer taskId) {
    this.postId = postId;
    this.taskId = taskId;
  }
}
