package com.gdg.group13.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Integer> {
  Optional<TodoListEntity> findByUidAndDate(Integer uid, LocalDate date);
  List<TodoListEntity> findByUid(Integer uid);
}
