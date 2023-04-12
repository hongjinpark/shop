package com.example.hong.mapper;

import com.example.hong.dto.CrudDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrudMapper {
    // 게시글을 생성하는 insert 쿼리를 호출하는 메서드
    public int insertBoard(CrudDto params);

    // 하나의 게시글을 조회하는 select 쿼리를 호출하는 메서드
    public CrudDto selectBoardDetail(Long idx);

    // 게시글을 수정하는 update 쿼리를 호출하는 메서드
    public int updateBoard(CrudDto params);

    // 게시글을 삭제하는 delete 쿼리를 호출하는 메서드
    public int deleteBoard(Long idx);

    // 게시글 목록을 조회하는 select 쿼리를 호출하는 메서드
    public List<CrudDto> selectBoardList();

    // 삭제 여부(delete_yn)가 'n'으로 지정된 게시글의 개수를 조회하는 select 쿼리를 호출하는 메서드
    public int selectBoardTotalCount();

}
