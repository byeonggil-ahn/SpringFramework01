package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	private final BoardService service;

	// 여기 getMapping는 /list 하든,list하든 상관없음
//	@GetMapping("/list")
//	public void list(Model model) {
//
//		log.info("list........");
//
//		model.addAttribute("list", service.getList());
//	}
//
//	@GetMapping("/register")
//	public void registerGET() {
//
//	}
	
	
	@GetMapping("/list")
	public void list(Criteria cri,Model model) {

		log.info(cri);
		log.info("list........");

		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker",new PageDTO(cri,service.getTotal(cri)));
	}

	@GetMapping("/register")
	public void registerGET() {

	}

	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {

		log.info("board:" + board);

		Long bno = service.register(board);

		log.info("BNO: " + bno);
		rttr.addFlashAttribute("result", bno);

		return "redirect:/board/list";
	}

	@GetMapping({"/get","/modify"})
//	ModelAttribute한 이유는 Criteria타입이 자동으로 소문자로 바뀌기 때문에 cri이름으로 바꿨음 
	public void get(@RequestParam("bno")Long bno,@ModelAttribute("cri") Criteria cri, Model model) {

		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board,Criteria cri, RedirectAttributes rttr) {

		int count = service.modify(board);
		 log.info("modify 메서드 호출됨");
		 log.info(board);
		 log.info(count);
		 log.info("페이지넘버 :"+cri.getPageNum());
		if(count == 1) {
			rttr.addFlashAttribute("result", "success");

		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());

		return "redirect:/board/list";

	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,Criteria cri, RedirectAttributes rttr) {

		int count = service.remove(bno);
		 log.info("remove 메서드 호출됨");
		 log.info(bno);
		 log.info(count);
		if (count == 1) {
			rttr.addFlashAttribute("result", "success");

		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/board/list";

	}
}
