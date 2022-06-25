package com.gdg.group13.task.service;

import com.gdg.group13.task.TaskEntity;
import com.gdg.group13.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTaskProvider {

  private final TaskRepository taskRepository;

  public List<TaskEntity> getDailyTask(LocalDate date) {
    return taskRepository.findByDate(date);

  }
}
