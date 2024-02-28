package org.zerock.mapper;

import org.springframework.beans.factory.annotation.Autowired;

public interface TimeMapper {
	public String getTime2();
	// 추상메서드임 //이걸 사용하려면 클래스를 만들어서 상속받아야하지만 우린 MyBatis가 해줄거임zz
}
