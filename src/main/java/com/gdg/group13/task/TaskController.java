package com.gdg.group13.task;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.dto.request.TaskUpdateRequestDto;
import com.gdg.group13.task.service.DailyTaskProvider;
import com.gdg.group13.task.dto.request.InquiryDailyTodoListRequest;
import com.gdg.group13.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

  private final TaskService taskService;
  private final DailyTaskProvider dailyTaskProvider;

  @PostMapping
  public ResponseDto makeTask(
    @RequestBody TaskMakeRequestDto request
  ) {
    System.out.println(taskService.make(request));
    return ResponseUtil.successResponse(taskService.make(request));
  }

  @GetMapping("/daily")
  public ResponseDto getDailyTask(
    @RequestBody InquiryDailyTodoListRequest request
  ) {
    return ResponseUtil.successResponse(dailyTaskProvider.getDailyTodoList(request));
  }

  @GetMapping("/{id}")
  public ResponseDto findSingleTask(@PathVariable("id") Integer taskId) {
    return ResponseUtil.successResponse(taskService.findSingleTask(taskId));
  }

  @PutMapping
  public ResponseDto updateTask(@RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
    return ResponseUtil.successResponse(taskService.updateTask(taskUpdateRequestDto));
  }

  @DeleteMapping("/{id}")
  public ResponseDto deleteTask(@PathVariable("id") Integer taskId) {
    return ResponseUtil.successResponse(taskService.deleteTask(taskId));
  }


  @GetMapping("/god-saeng")
  public ResponseDto inquiryGodSaengList(@RequestParam Integer uid) {
    return ResponseUtil.successResponse(dailyTaskProvider.monthGodSaengList(uid));
  }

  @PatchMapping("/check")
  public ResponseDto fixCheckStatus(@RequestParam Integer taskId) {
    return ResponseUtil.successResponse(taskService.fixCheckBox(taskId));
  }

  @GetMapping("god-saeng/rate")
  public ResponseDto inquiryGodSaengRate(@RequestParam Integer uid) {
    return ResponseUtil.successResponse(dailyTaskProvider.getGodSaengRate(uid));
  }
}
