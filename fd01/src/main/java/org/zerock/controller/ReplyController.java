package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;

	@PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		// ResponseEntity는 Http에 대한 post 요청에 대한 응답을 더 자세히 반환
		// 받을 수 있는 객체

		// @RequestBody 사용하여 json으로 받은 데이터를 ReplyVO 타입으로 변환.
		log.info("ReplyVO" + vo);

		int insertCount = service.register(vo);

		log.info("Reply INSERT COUNT : " + insertCount);

		return insertCount == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// 삼항 연산자
	}

//	@GetMapping(value = "/pages/{bno}/{page}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, // JSON 형식의 응답
//			MediaType.APPLICATION_XML_VALUE // XML 형식의 응답
//	})
//	// jackson-dataformat-xml 이라는 dependency 추가해줘야 페이지 제대로 로드됨.
//
//	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
//
//		log.info("getlist ----------");
//		Criteria cri = new Criteria(page, 10);
//		log.info(cri);
//
//		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
//	}

	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {

		log.info("get-------" + rno);

		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);

	}

	@DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {

		log.info("remove------" + rno);

		return service.remove(rno) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = { RequestMethod.PUT,
			RequestMethod.PATCH }, value = "/{rno}", consumes = "application/json", produces = {
					MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {

		vo.setRno(rno);

		log.info("rno-----" + rno);

		log.info("modify: " + vo);

		return service.modify(vo) == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, // JSON 형식의 응답
						 MediaType.APPLICATION_XML_VALUE // XML 형식의 응답
	})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
		
		Criteria cri = new Criteria(page,10);
		
		log.info("get reply List bno : " + bno);
		
		log.info("cri"+cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
	}
	
}
