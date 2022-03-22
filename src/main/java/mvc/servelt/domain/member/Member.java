package mvc.servelt.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	
	private Long id;
	private String username;
	private int age;
	
	// 기본 생성자
	public Member() {
	}
	
	// usrname 과 age 초기화 생성자
	// Member 객체 인스턴스 생성시 username과 age 가 필수로 필요할 때
	public Member(String username, int age) {
		this.username = username;
		this.age = age;
	}
}
