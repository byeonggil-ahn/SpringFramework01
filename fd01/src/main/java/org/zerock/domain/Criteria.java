package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
    public int getStartRow() {
        return (pageNum - 1) * amount;
    }

	public String[] getTypeArr() {
		
//	빈 배열을 foreach로 반환해주거나 문자열을 분리하여 배열로 만들어서 foreach로 반
		return type == null? new String[] {}: type.split("");
	}
	
// UriComponentsBuilder객체 : 여러 파라미터를 연결하여 URL형태로 만듬 => 매번 파라미터 유지하는 번거로운 작업 해결 	
	public String getListLink()	{
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword",this.getKeyword());
		
		
		return builder.toUriString();
	}
	
	
}
