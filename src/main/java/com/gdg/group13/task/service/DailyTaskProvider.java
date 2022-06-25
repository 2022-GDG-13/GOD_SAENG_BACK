package com.gdg.group13.task.service;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.TodoListEntity;
import com.gdg.group13.task.TodoListRepository;
import com.gdg.group13.task.dto.request.InquiryDailyTodoListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTaskProvider {

  private final TodoListRepository todoListRepository;
  private final TaskRepository taskRepository;

  public List<TaskEntity> getDailyTodoList(InquiryDailyTodoListRequest request) {
    TodoListEntity todoListEntity = todoListRepository.findByUidAndDate(request.getUid(), request.getDate());
    return taskRepository.findByTodoId(todoListEntity.getId());

  }
}


