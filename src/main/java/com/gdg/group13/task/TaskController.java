package com.gdg.group13.task;

import com.gdg.group13.task.service.DailyTaskProvider;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.service.TaskMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

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
    return ResponseUtil.successResponse(taskMaker.make(request));
  }

  @GetMapping("/daily")
  public ResponseDto getDailyTask(

    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
  ) {
    return ResponseUtil.successResponse(dailyTaskProvider.getDailyTask(date));
  }
}
