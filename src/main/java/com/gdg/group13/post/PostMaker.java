package com.gdg.group13.post;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMaker {

  private final PostRepository postRepository;
  private final PostTaskRelationRepository postTaskRelationRepository;

  public PostEntity make(PostMakeRequestDto request) {
    var postEntity = postRepository.save(
      new PostEntity(request.uid, request.title, request.imgUrl, request.description, LocalDate.now(), 0));

    var dailyTaskList = request.taskIdList
      .stream().map(it -> new PostTaskRelationEntity(postEntity.getId(), it))
      .collect(Collectors.toList());

    postTaskRelationRepository.saveAll(dailyTaskList);

    return postEntity;
  }
}


