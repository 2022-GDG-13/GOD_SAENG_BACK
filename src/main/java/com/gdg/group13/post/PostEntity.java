package com.gdg.group13.post;

import com.gdg.group13.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class PostEntity  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uid;

    private String title;

    private String imgUrl;

    private String description;

    private LocalDate date;

    @Column(name = "like_cnt")
    private Integer likeCnt;

    public PostEntity(Integer uid, String title, String imgUrl, String description, LocalDate date, Integer likeCnt) {
        this.uid = uid;
        this.title = title;
        this.imgUrl = imgUrl;
        this.description = description;
        this.date = date;
        this.likeCnt = likeCnt;
    }
}

