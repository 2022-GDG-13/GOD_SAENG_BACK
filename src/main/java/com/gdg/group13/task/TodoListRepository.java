package com.gdg.group13.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Long> {
  TodoListEntity findByUidAndDate(Integer uid, LocalDate date);
}
