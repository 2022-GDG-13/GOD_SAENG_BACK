package com.gdg.group13.task.service;

import com.gdg.group13.post.DailyTaskRepository;
import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.TodoListEntity;
import com.gdg.group13.task.TodoListRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTaskProvider {

  private final TodoListRepository todoListRepository;
  private final TaskRepository taskRepository;

  public List<TaskEntity> getDailyTodoList(InquiryDailyTodoList request) {
    TodoListEntity todoListEntity = todoListRepository.findByUidAndDate(request.getUid(), request.getDate());
    return taskRepository.findByTodoId(todoListEntity.getId());

  }
}


