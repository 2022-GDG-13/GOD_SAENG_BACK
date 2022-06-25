package com.gdg.group13.task;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.dto.request.TaskUpdateRequestDto;
import com.gdg.group13.task.service.DailyTaskProvider;
import com.gdg.group13.task.service.TaskMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

  private final TaskMaker taskMaker;
  private final DailyTaskProvider dailyTaskProvider;

  @PostMapping
  public ResponseDto makeTask(
    @RequestBody TaskMakeRequestDto request
  ) {
    System.out.println(taskMaker.make(request));
    return ResponseUtil.successResponse(taskMaker.make(request));
  }

  @GetMapping("/daily")
  public ResponseDto getDailyTask(

    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
  ) {
    return ResponseUtil.successResponse(dailyTaskProvider.getDailyTask(date));
  }

  @GetMapping("/{id}")
  public ResponseDto findSingleTask(@PathVariable("id") Integer taskId) {
    return ResponseUtil.successResponse(taskMaker.findSingleTask(taskId));
  }

  @PutMapping("/{id}")
  public ResponseDto updateTask(@PathVariable("id") Integer taskId, @RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
    return ResponseUtil.successResponse(taskMaker.updateTask(taskId, taskUpdateRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseDto deleteTask(@PathVariable("id") Integer taskId) {
    return ResponseUtil.successResponse(taskMaker.deleteTask(taskId));
  }}
