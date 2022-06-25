package com.gdg.group13.common;

public class ResponseUtil {
  public static ResponseDto successResponse(Object data) {
    return ResponseDto.builder()
      .success(true)
      .response(data)
      .build();
  }

}

