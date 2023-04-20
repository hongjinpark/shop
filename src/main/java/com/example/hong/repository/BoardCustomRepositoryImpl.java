package com.example.hong.repository;

import com.example.hong.dto.BoardDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.hong.entity.QBoard.board;
import static com.example.hong.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public BoardDto findBoardByName(Long id,String email){
        return jpaQueryFactory.select(Projections.fields(BoardDto.class,
                board.name,
                board.title,
                board.content,
                board.questionStatus))
                .from(board)
                .join(board.user,user)
                .where(user.email.eq(email).and(board.id.eq(id)))
                .fetchOne();
    }

    @Override
    public List<BoardDto> findBoardAllByEmailIndividual(String email){
        return jpaQueryFactory.select(Projections.fields(BoardDto.class,
                board.name,
                board.title,
                board.content,
                board.questionStatus))
                .from(board)
                .join(board.user,user)
                .where(board.name.eq(user.name).and(user.email.eq(email)))
                .orderBy(board.id.desc())
                .fetch();
    }
}
