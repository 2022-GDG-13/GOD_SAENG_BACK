package com.gdg.group13.post;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

  private final PostMaker postMaker;

  @PostMapping
  public ResponseDto makePost(
    @RequestBody PostMakeRequestDto request
  ) {
    return ResponseUtil.successResponse(postMaker.make(request));
  }
}
