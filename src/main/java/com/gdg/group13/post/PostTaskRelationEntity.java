package com.gdg.group13.post;

import com.gdg.group13.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "post_task_relation")
@Getter
@NoArgsConstructor
@ToString
public class PostTaskRelationEntity extends BaseEntity {

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
