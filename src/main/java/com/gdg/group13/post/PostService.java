package com.gdg.group13.post;


import com.gdg.group13.member.MemberEntity;
import com.gdg.group13.member.MemberRepository;
import com.gdg.group13.task.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final TaskRepository taskRepository;
  private final TodoListRepository todoListRepository;
  private final MemberRepository memberRepository;
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
  List<PostListDto> getRanking(Tag tag) {
    List<TaskEntity> taskList;
    if (tag != null){
      taskList = taskRepository.findAll();
    } else {
      taskList = taskRepository.findByTag(tag);
    }

    var postIdList = postTaskRelationRepository.findAllById(
        taskList.stream()
          .map(it -> it.getId()).collect(Collectors.toList())
      )
      .stream()
      .map(it -> it.getPostId())
      .collect(Collectors.toSet());


    List<PostEntity> postList = postRepository.findByIdInOrderByLikeCntDesc(new ArrayList<>(postIdList));

    return convertPostListResponse(postList);
  }

  public PostSingleDto findSinglePost(Integer postId) {
    PostEntity postEntity = postRepository.findById(postId)
      .orElseThrow(() -> new IllegalStateException("없는 post id 입니다."));

    TodoListEntity todoListEntity = todoListRepository.findByUidAndDate(postEntity.getUid(), postEntity.getDate())
      .orElseThrow(() -> new IllegalStateException("이정도면 서버 에러임"));

    var memberEntity = memberRepository.findById(postEntity.getUid());

    // task
    List<Integer> taskIds = postTaskRelationRepository.findAllByPostId(postEntity.getId())
        .stream().map(it -> it.getTaskId())
        .collect(Collectors.toList());

    List<TaskEntity> taskEntityList = taskRepository.findAllById(taskIds);

    // tag
    Set<Tag> collect = taskEntityList
        .stream()
        .map(TaskEntity::getTag)
        .collect(Collectors.toSet());

    String userName;
    if (memberEntity.isPresent()) {
      userName = memberEntity.get().getName();
    } else {
      userName = "갓생이";
    }
    return new PostSingleDto(postEntity, todoListEntity.getGodSaeng(), userName, taskEntityList, collect);

  }

  public List<PostListDto> findAllPost() {
    List<PostEntity> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

    return convertPostListResponse(postList);
  }

  private List<PostListDto> convertPostListResponse(List<PostEntity> postList) {
    Set<Integer> godSaengSet = postList.stream()
      .filter(it -> todoListRepository.findByUidAndDate(it.getUid(), it.getDate()).isPresent())
      .map(it -> it.getId())
      .collect(Collectors.toSet());

    Set<Integer> userNameMap = postList.stream()
      .map(it -> it.getUid())
      .collect(Collectors.toSet());

    Map<Integer, String> memberIdMap = memberRepository.findAllById(userNameMap)
      .stream().collect(
        Collectors.toMap(MemberEntity::getId, MemberEntity::getName)
      );

    // 태그 모두 조회
    // post, task
    Set<Integer> postIdSets = postList.stream()
        .map(PostEntity::getId)
        .collect(Collectors.toSet());

    List<PostTaskRelationEntity> taskRelations = postTaskRelationRepository.findAllById(postIdSets);

    // task post
    // 1    2
    // 2    2
    // 2    3
    // 2    4

    Map<Integer, List<PostTaskRelationEntity>> collect = taskRelations.stream()
        .collect(Collectors.groupingBy(PostTaskRelationEntity::getPostId));



    Set<Integer> taskId = taskRelations
        .stream().map(tR -> tR.getTaskId())
        .collect(Collectors.toSet());

    List<TaskEntity> taskEntities = taskRepository.findAllById(taskId);




    Set<Tag> tags = taskEntities.stream()
        .map(TaskEntity::getTag)
        .collect(Collectors.toSet());

    return postList.stream()
      .map(it -> new PostListDto(it, godSaengSet.contains(it.getId()), memberIdMap.get(it.getUid())))
      .collect(Collectors.toList());
  }

}


