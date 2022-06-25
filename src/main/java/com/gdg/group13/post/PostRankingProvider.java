package com.gdg.group13.post;

import com.gdg.group13.task.Tag;
import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostRankingProvider {

  private final TaskRepository taskRepository;
  private final PostTaskRelationRepository postTaskRelationRepository;
  private final PostRepository postRepository;

  //todo :: 며칠 기준으로 가져올건지 필터 추가 할 것
  List<PostEntity> getRanking(Tag tag) {
    List<TaskEntity> taskList = taskRepository.findByTag(tag);

    var postIdList = postTaskRelationRepository.findAllById(
        taskList.stream()
          .map(it -> it.getId()).collect(Collectors.toList())
      )
      .stream()
      .map(it -> it.getPostId())
      .collect(Collectors.toSet());


    return postRepository.findByIdInOrderByLikeCntDesc(new ArrayList(postIdList));
  }
}
