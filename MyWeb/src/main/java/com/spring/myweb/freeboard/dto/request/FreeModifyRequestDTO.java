package com.spring.myweb.freeboard.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor //받는 용도면 5개 정도 선언 //3번.
public class FreeModifyRequestDTO {

	private int bno;
	private String writer;
	private String title;
	private String content;
	//데이터 3개 날라옴. 직접선언해도되고 지금 처럼 dto만들어서 여기서 선언후 클래스로 선언해도됨
	
}
