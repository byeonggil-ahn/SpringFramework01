package org.zerock.domain;

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
	
	
}
