package com.gdg.group13.common.s3;

import com.gdg.group13.common.ResponseDto;
import com.gdg.group13.common.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/common")
@RequiredArgsConstructor
public class CommonController {

  private final S3Uploader uploader;

  @PostMapping("/img")
  public ResponseDto upload(
    @RequestParam("img") MultipartFile img
    ) throws IOException {
    return ResponseUtil.successResponse(uploader.upload(img));
  }
}
