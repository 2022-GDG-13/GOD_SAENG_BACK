package com.gdg.group13.task;


import com.gdg.group13.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "todo_list",
  indexes = {
  @Index(name = "i_uid", columnList = "uid"),
  @Index(name = "i_date", columnList = "date")
})
public class TodoListEntity  extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer uid;

  private Boolean godSaeng;

  private LocalDate date;

  public void changeTrue() {
    this.godSaeng = true;
  }

  public void changeFalse() {
    this.godSaeng = false;
  }

  public TodoListEntity(Integer uid, LocalDate date) {
    this.uid = uid;
    this.date = date;
  }
}
