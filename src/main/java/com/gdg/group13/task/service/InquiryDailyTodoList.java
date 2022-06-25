package com.gdg.group13.task.service;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class InquiryDailyTodoList {
  public Integer uid;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  public LocalDate date;
}
