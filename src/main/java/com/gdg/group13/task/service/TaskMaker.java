package com.gdg.group13.task.service;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskMaker {

  private final TaskRepository taskRepository;

  public TaskEntity make(TaskMakeRequestDto request) {
    var taskEntity = taskRepository.save(
      new TaskEntity(request.todoId, request.name, false, request.tag)
    );

    return taskEntity;
  }
}


