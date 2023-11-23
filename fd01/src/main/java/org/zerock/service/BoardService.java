package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {

	Long register(BoardVO board);

	BoardVO get(Long bno);

//	수정 성공시 1을 반환 실패시 0 을반환 
	int modify(BoardVO board);

//  삭제 성공시 1을 반환 실패시 0을 반
	int remove(Long bno);

	List<BoardVO> getList();
	
	List<BoardVO> getList(Criteria cri);
	
	int getTotal(Criteria cri);

}
