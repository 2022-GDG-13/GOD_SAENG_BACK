package com.gdg.group13.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
  private final boolean success;
  private final Object response;
}