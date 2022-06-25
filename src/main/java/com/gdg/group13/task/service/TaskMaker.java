package com.gdg.group13.task.service;

import java.time.LocalDate;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.TodoListEntity;
import com.gdg.group13.task.TodoListRepository;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.dto.request.TaskUpdateRequestDto;
import com.gdg.group13.task.dto.response.TaskSingleResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskMaker {

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

  public TaskSingleResponseDto findSingleTask(Integer taskId) {
    TaskEntity task = taskRepository.findById(taskId)
        .orElseThrow(() -> new IllegalStateException("없는 task id 입니다."));
    return new TaskSingleResponseDto(task);
  }

  public TaskSingleResponseDto updateTask(Integer taskId, TaskUpdateRequestDto taskUpdateRequestDto) {
    TaskEntity task = taskRepository.findById(taskId)
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


