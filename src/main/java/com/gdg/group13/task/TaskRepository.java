package com.gdg.group13.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
  List<TaskEntity> findByTodoId(Integer todoId);
  List<TaskEntity> findByTag(Tag tag);
}
