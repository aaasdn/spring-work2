package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.dto.page.PageCreator;
import com.spring.myweb.freeboard.dto.request.FreeModifyRequestDTO;
import com.spring.myweb.freeboard.dto.request.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {

	private final IFreeBoardService service;
	
	//페이징이 들어간 목록 화면
	@GetMapping("/freeList")
	public void freeList(Page page, Model model) {
		System.out.println("/freeboard/freeList: GET!");
		PageCreator creator;
		int totalCount = service.getTotal(page);
		if(totalCount == 0) {
			page.setKeyword(null);
			page.setCondition(null);
			creator = new PageCreator(page, service.getTotal(page));
			model.addAttribute("msg", "search");
		} else {
			creator = new PageCreator(page, totalCount);
		}
		
		model.addAttribute("boardList", service.getList(page));
		model.addAttribute("pc", creator);
	}
	
	// 글쓰기 페이지를 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist() {}
	
	
	//글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);
		return "redirect:/freeboard/freeList";		
	}
	
	// 글 상세보기 처리
	@GetMapping("/content")
	public String getContent(int bno,
						Model model, @ModelAttribute("p") Page page) { //9번 String 타입으로 고치기
						model.addAttribute("article", service.getContent(bno)); //8번
//		service.getContent(bno); //2번
		return "freeboard/freeDetail"; //10번 리턴값주기
		
	}
		
	//글 수정 페이지 이동 요청
	@PostMapping("/modPage")
	public String modPage(@ModelAttribute("article") FreeModifyRequestDTO dto) {
		return "freeboard/freeModify";
	}
	
	// 글 수정 요청 2번.
	@PostMapping("/modify") //dto생성후 받아도 되고, dto안만들거면 세개를 직접받아도됨
	public String modify(FreeModifyRequestDTO dto) {
		service.update(dto);
		return "redirect:/freeboard/content?bno=" + dto.getBno(); //6번.
	}
	
	//글 삭제 요청
	@PostMapping("/delete")
	public String delete(int bno) {
		service.delete(bno);
		return "redirect:/freeboard/freeList";
	}
	
}

























