package org.zerock.mapper;

import java.io.Console;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArr = {9L,12L,14L,15L,17L,155L,171L,172L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 "+i);
			vo.setReplyer("replyer"+ i);
			
			mapper.insert(vo);
			
		});
	}
	
	@Test
	public void testMapper()	 {
		log.info(mapper);
	}
	
	@Test
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 1L;
//		int targetRnoAsInt = targetRno.intValue(); // Long을 int로 변환
	    
	    mapper.delete(targetRno);
	}
	
	@Test
	public void testUpdate()	{
		Long  targetRno = 10L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		vo.setReply("update Reply test ");
		
		int count = mapper.update(vo);
		
		log.info("UPDATE COUNT: " + count);
	}
	
	@Test
	public void testList() {
		// bno번호와 일치하는 댓글들 가져오는 테스트 코드 
		Criteria cri = new Criteria();
		log.info("cri 입니다 :"+cri);
		log.info("bno arr 테스트 "+bnoArr[7]);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[7]);
		
		replies.forEach(reply ->log.info(reply));
	}
	
	@Test
	public void testList2() {
		
		Criteria cri = new Criteria(2,10);
		log.info("cri 입니다 :"+cri);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 174L);
		
		replies.forEach(reply -> log.info(reply));
	}
}
