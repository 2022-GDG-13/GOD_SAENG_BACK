package com.gdg.group13.task;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import com.gdg.group13.task.dto.request.TaskMakeRequestDto;
import com.gdg.group13.task.service.TaskMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskController {

  private final TaskMaker taskMaker;

  @PostMapping
  public ResponseDto makeTask(
    @RequestBody TaskMakeRequestDto request
  ) {
    System.out.println(taskMaker.make(request));
    return ResponseUtil.successResponse(taskMaker.make(request));
  }
}
