package com.gdg.group13.task.dto.request;

import com.gdg.group13.task.Tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskMakeRequestDto {

  private String title;
  private String description;
  private String imageUrl;
  private Tag tag;
}
