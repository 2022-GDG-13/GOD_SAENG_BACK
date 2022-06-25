package com.gdg.group13.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> upload(
    @RequestParam("img") MultipartFile img
    ) throws IOException {
    return ResponseEntity.ok(uploader.upload(img));
  }
}
