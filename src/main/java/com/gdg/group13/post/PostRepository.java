package com.gdg.group13.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
  List<PostEntity> findByIdInOrderByLikeCntDesc(List<Integer> idList);
}


