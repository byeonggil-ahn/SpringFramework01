package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	// ;(세미콜론) 없어야 한다 .
	@Select("select NOW()")
	String getTime();
}
