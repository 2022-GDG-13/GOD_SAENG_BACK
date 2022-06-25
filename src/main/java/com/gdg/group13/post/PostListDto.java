package com.gdg.group13.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class PostListDto {
  public PostEntity post;
  public Boolean godSaeng;
  public String userName;
}
