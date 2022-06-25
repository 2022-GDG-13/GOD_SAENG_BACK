package com.gdg.group13.post;

import lombok.Getter;

import java.util.List;

@Getter
public class PostMakeRequestDto {
  public Integer uid;
  public String title;
  public String imgUrl;
  public String description;
  public List<Integer> taskIdList;
}
