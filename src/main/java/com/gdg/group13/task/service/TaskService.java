package com.gdg.group13.task.service;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.TodoListEntity;
import com.gdg.group13.task.TodoListRepository;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.dto.request.TaskUpdateRequestDto;
import com.gdg.group13.task.dto.response.TaskSingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TodoListRepository todoListRepository;
  private final TaskRepository taskRepository;

  public TaskEntity make(TaskMakeRequestDto request) {
    TodoListEntity dailyTodoList = todoListRepository.findByUidAndDate(request.getUid(), LocalDate.now());
    var taskEntity = taskRepository.save(
      new TaskEntity(dailyTodoList.getId(), request.getTitle(), request.getDescription(), request.getImageUrl(), true,
        request.getTag()
      ));

    return taskEntity;
  }

  public TaskEntity fixCheckBox(Integer taskId){
    var taskEntity = taskRepository.findById(taskId)
      .orElseThrow(() -> new IllegalStateException("없는 task id 입니다."));

    taskEntity.setCheckBox(!taskEntity.getCheckBox());
    return taskRepository.save(taskEntity);
  }

  public TaskSingleResponseDto findSingleTask(Integer taskId) {
    TaskEntity task = taskRepository.findById(taskId)
        .orElseThrow(() -> new IllegalStateException("없는 task id 입니다."));
    return new TaskSingleResponseDto(task);
  }

  public TaskSingleResponseDto updateTask(TaskUpdateRequestDto taskUpdateRequestDto) {
    TaskEntity task = taskRepository.findById(taskUpdateRequestDto.getTaskId())
        .orElseThrow(() -> new IllegalStateException("없는 task id 입니다."));

    task.setTitle(taskUpdateRequestDto.getTitle());
    task.setDescription(taskUpdateRequestDto.getDescription());
    task.setImageUrl(taskUpdateRequestDto.getImageUrl());
    task.setTag(taskUpdateRequestDto.getTag());

    return new TaskSingleResponseDto(task);
  }

  public Integer deleteTask(Integer taskId) {
    taskRepository.deleteById(taskId);
    return taskId;
  }
}


