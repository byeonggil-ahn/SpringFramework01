package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
// 페이징 처리 로
public class PageDTO {

	private int startPage,endPage;
	private boolean prev,next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri,int total) {
		this.cri = cri;
		this.total = total;
		
//		criteria에서 페이지 넘버가져와서 10.0 으로 나누면 double값이 반환 되니,
//		Math.ceil()로 올림하고 int타입의 endpage에 넣어야 하니 int로 형변환 
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		this.startPage = endPage - 9;
		
//		1보다크면 prev 에 true 할당 아니면 당연히 false 
		this.prev = this.startPage > 1 ;
//		total *1.0해서 소숫점 있는 상태로만들어줘서 나머지도 계산 후 추후에 Math.ceil으로 올림가능하게 하기 위
		int realEnd =(int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		this.endPage = realEnd <= endPage? realEnd : endPage;
		
		this.next = this.endPage <realEnd;
	}
}
