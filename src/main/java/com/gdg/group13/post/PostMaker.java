package com.gdg.group13.post;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMaker {

  private final PostRepository postRepository;
  private final DailyTaskRepository dailyTaskRepository;

  public PostEntity make(PostMakeRequestDto request) {
    var postEntity = postRepository.save(
      new PostEntity(request.uid, request.title, request.imgUrl, request.description, LocalDate.now(), 0));

    var dailyTaskList = request.taskIdList
      .stream().map(it -> new DailyTaskEntity(postEntity.getId(), it))
      .collect(Collectors.toList());

    dailyTaskRepository.saveAll(dailyTaskList);

    return postEntity;
  }
}


