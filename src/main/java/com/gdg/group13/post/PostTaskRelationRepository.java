package com.gdg.group13.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTaskRelationRepository extends JpaRepository<PostTaskRelationEntity, Integer> {
//  List<PostTaskRelationEntity> find1(List<Integer> taskIdList);
}
