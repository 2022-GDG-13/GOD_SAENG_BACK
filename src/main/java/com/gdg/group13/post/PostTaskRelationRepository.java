package com.gdg.group13.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostTaskRelationRepository extends JpaRepository<PostTaskRelationEntity, Integer> {
    List<PostTaskRelationEntity> findAllByPostId(Integer id);

    List<PostTaskRelationEntity> findAllByPostIdIn(Set<Integer> postIdSets);

    // void findAllByPostIds();
    //  List<PostTaskRelationEntity> find1(List<Integer> taskIdList);
}
