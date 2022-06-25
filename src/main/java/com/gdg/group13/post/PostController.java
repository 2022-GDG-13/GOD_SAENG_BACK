package com.gdg.group13.post;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import com.gdg.group13.task.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

  private final PostMaker postMaker;
  private final PostRankingProvider postRankingProvider;

  @PostMapping
  public ResponseDto makePost(
    @RequestBody PostMakeRequestDto request
  ) {
    return ResponseUtil.successResponse(postMaker.make(request));
  }

  @GetMapping("/rank")
  public ResponseDto rank(
    @RequestParam Tag tag
  ) {
    return ResponseUtil.successResponse(postRankingProvider.getRanking(tag));
  }
}
