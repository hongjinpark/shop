package com.example.hong.entity;

import com.example.hong.constant.QuestionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name="board_id")
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Lob
    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(String name,String title,String content,QuestionStatus questionStatus,User user){
        this.name=name;
        this.title=title;
        this.content=content;
        this.questionStatus=questionStatus;
        this.user=user;
    }

    public void insertAnswer(String answer){
        this.answer=answer;
        this.questionStatus=QuestionStatus.ANSWER;
    }

}
