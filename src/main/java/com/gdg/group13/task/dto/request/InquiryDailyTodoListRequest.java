package com.gdg.group13.task.dto.request;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class InquiryDailyTodoListRequest {
  public Integer uid;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public LocalDate date;
}
