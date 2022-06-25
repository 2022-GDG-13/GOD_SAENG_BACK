package com.gdg.group13.task.dto.request;

import com.gdg.group13.task.Tag;
import lombok.Getter;

@Getter
public class TaskMakeRequestDto {

  public Integer todoId;
  public String name;
  public Tag tag;

}
