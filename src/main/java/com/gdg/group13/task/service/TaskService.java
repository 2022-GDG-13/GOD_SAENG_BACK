package com.gdg.group13.task.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.TodoListEntity;
import com.gdg.group13.task.TodoListRepository;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.dto.request.TaskUpdateRequestDto;
import com.gdg.group13.task.dto.response.TaskSingleResponseDto;

import lombok.RequiredArgsConstructor;

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

    updateGodSaengStatus(taskEntity.getTodoId());

    return taskEntity;
  }

  public TaskEntity fixCheckBox(Integer taskId){
    var taskEntity = taskRepository.findById(taskId)
      .orElseThrow(() -> new IllegalStateException("없는 task id 입니다."));

    updateGodSaengStatus(taskEntity.getTodoId());
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

    updateGodSaengStatus(task.getTodoId());

    task.setTitle(taskUpdateRequestDto.getTitle());
    task.setDescription(taskUpdateRequestDto.getDescription());
    task.setImageUrl(taskUpdateRequestDto.getImageUrl());
    task.setTag(taskUpdateRequestDto.getTag());

    return new TaskSingleResponseDto(task);
  }

  public Integer deleteTask(Integer taskId) {
    TaskEntity task = taskRepository.findById(taskId)
        .orElseThrow(() -> new IllegalStateException("없는 task id 입니다."));
    taskRepository.delete(task);
    updateGodSaengStatus(task.getTodoId());
    return taskId;
  }

  private void updateGodSaengStatus(Integer todoId) {
    TodoListEntity todoListEntity = todoListRepository.findById(todoId)
        .orElseThrow(() -> new IllegalStateException("없는 todoList입니다."));

    List<TaskEntity> taskList = taskRepository.findByTodoId(todoId);

    if (taskList.stream().allMatch(TaskEntity::getCheckBox)) {
      todoListEntity.changeTrue();
    } else {
      todoListEntity.changeFalse();
    }
  }
}


