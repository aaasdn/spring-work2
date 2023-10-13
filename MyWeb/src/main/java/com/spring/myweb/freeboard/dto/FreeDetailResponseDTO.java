package com.spring.myweb.freeboard.dto;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class FreeDetailResponseDTO {
	//5번
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String date;
	
	//6번
	public FreeDetailResponseDTO(FreeBoard board) {
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		if(board.getUpdateDate() == null) {
			this.date = FreeListResponseDTO.makePrettierDateString(board.getRegDate());
		} else {
			this.date 
			= FreeListResponseDTO.makePrettierDateString(board.getUpdateDate()) + "(수정됨)";   
		}
	}
	
}



















