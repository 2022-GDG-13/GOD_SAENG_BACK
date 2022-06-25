package com.gdg.group13.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {
  private final AmazonS3Client amazonS3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.credentials.access-key}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secret-key}")
  private String secretKey;


  public String upload(MultipartFile multipartFile) throws IOException {
    File uploadFile = convert(multipartFile)
      .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

    var ranNum = new Random().nextInt(10000);
    var fileName = String.valueOf(System.currentTimeMillis() + ranNum) +
      multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

    amazonS3Client.putObject(
      new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));

    uploadFile.delete();
    var imgUrl = amazonS3Client.getUrl(bucket, fileName).toString();
    return imgUrl;
  }


  private Optional<File> convert(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    log.info("Long time: {}", System.currentTimeMillis());
    String newFileName = String.valueOf(System.currentTimeMillis()) + String.valueOf((int) (Math.random() * 1000)) + fileName.substring(fileName.lastIndexOf("."));
    log.info("newFileName: {}", newFileName);
    File convertFile = new File(newFileName);
    if (convertFile.createNewFile()) {
      try (FileOutputStream fos = new FileOutputStream(convertFile)) {
        fos.write(file.getBytes());
      }
      return Optional.of(convertFile);
    }

    return Optional.empty();
  }
}
