package com.gdg.group13.task.service;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import com.gdg.group13.task.TodoListEntity;
import com.gdg.group13.task.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyTaskProvider {

  private final TodoListRepository todoListRepository;
  private final TaskRepository taskRepository;

  public List<TaskEntity> getDailyTodoList(Integer uid, LocalDate date) {
    TodoListEntity todoListEntity = todoListRepository.findByUidAndDate(uid, LocalDate.now())
        .orElseGet(() -> {
          TodoListEntity todoList = new TodoListEntity(uid, LocalDate.now());
          return todoListRepository.save(todoList);
        });

    return taskRepository.findByTodoId(todoListEntity.getId());
  }

  public List<Integer> monthGodSaengList(Integer uid) {
    return todoListRepository.findByUid(uid)
      .stream()
      .filter(it -> it.getGodSaeng())
      .map(it -> it.getDate().getDayOfMonth())
      .collect(Collectors.toList());
  }

  public long getGodSaengRate(Integer uid){
    var godSaengCount = todoListRepository.findByUid(uid)
      .stream()
      .filter(it -> it.getGodSaeng())
      .collect(Collectors.toSet()).size();

    return Math.round(100.0 * godSaengCount / LocalDate.now().getDayOfMonth());
  }
}


