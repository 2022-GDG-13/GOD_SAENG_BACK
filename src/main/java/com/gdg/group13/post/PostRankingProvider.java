package com.gdg.group13.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostRankingProvider {

  private final PostRepository postRepository;

  //todo :: 며칠 기준으로 가져올건지 필터 추가 할 것
  List<PostEntity> getRanking() {
    return postRepository.findAll(Sort.by(Sort.Direction.DESC, "likeCnt"));
  }
}
