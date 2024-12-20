package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testGetList() {

		log.info("------------");
		boardMapper.getList();
	}

	@Test
	public void testInsert() {

		BoardVO vo = new BoardVO();
		vo.setTitle("title 테스트");
		vo.setContent("content 테스트");
		vo.setWriter("wirter 테스트");

		boardMapper.insert(vo);
		;
		log.info("---------------");
		log.info("after iinsert" + vo.getBno());
	}

	@Test
	public void testInsertSelectKey() {

		BoardVO vo = new BoardVO();
		vo.setTitle("selectkey 테스트");
		vo.setContent("selectkey 테스트");
		vo.setWriter("selecetkey 테스트");

		boardMapper.insertSelectKey(vo);

		log.info("----------------");
		log.info("after insert selectKey" + vo.getBno());

	}

	@Test
	public void testRead() {

		BoardVO vo = boardMapper.read(5L);
		log.info(vo);
	}

	@Test
	public void testDelete() {

		int count = boardMapper.delete(1L);

		log.info("count:" + count);

	}

	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(2L);
		vo.setTitle("업데이트 타이틀 ");
		vo.setContent("update content");
		vo.setWriter("user00");

		log.info("-=======count:" + boardMapper.update(vo));
	}
	
	@Test
	public void testPaging() {
		// 1 10
		Criteria cri = new Criteria();
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		list.forEach(b -> log.info(b));
	}
	
	@Test
	public void testPageDTO() {
		
		Criteria cri = new Criteria() ;
		cri.setPageNum(25);
		PageDTO pageDTO = new PageDTO(cri,251);
		
		log.info(pageDTO);
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("setkeyword테스트 ");
		cri.setType("TC");
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
	
	@Test
	public void testlistLink()	{
	     Criteria criteria = new Criteria();
	        criteria.setPageNum(1);
	        criteria.setAmount(10);
	        criteria.setType("exampleType");
	        criteria.setKeyword("exampleKeyword");

	        String listLink = criteria.getListLink();

	        System.out.println("Generated List Link: " + listLink);
	}
	
	
	
	
	
}
