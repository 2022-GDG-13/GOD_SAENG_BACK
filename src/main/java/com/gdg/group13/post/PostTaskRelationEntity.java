package com.gdg.group13.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "post_task_relation")
@Getter
@NoArgsConstructor
public class PostTaskRelationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer postId;

  private Integer taskId;

  public PostTaskRelationEntity(Integer postId, Integer taskId) {
    this.postId = postId;
    this.taskId = taskId;
  }
}
