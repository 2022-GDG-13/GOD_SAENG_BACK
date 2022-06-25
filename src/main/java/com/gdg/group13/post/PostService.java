package com.gdg.group13.post;


import com.gdg.group13.task.Tag;
import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final TaskRepository taskRepository;
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

  public PostEntity findSinglePost(Integer postId){
    return postRepository.findById(postId)
      .orElseThrow(() -> new IllegalStateException("없는 post id 입니다."));
  }

  public List<PostEntity> findAllPost(){
    return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
  }

}


