package mvc.servelt.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest { // public 생략 가능
	
	MemberRepository memberRepository = MemberRepository.getInstance();
	
	// test 끝났을때 초기화 (테스트 마다 초기화 시켜줌)
	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void save() {
		//test에 필요한 data 세팅
		Member member = new Member("hj", 29); // 멤버 객체 인스턴스 생성
		
		//test할 부분
		Member savedMember = memberRepository.save(member); // 저장 메서드
		
		//test
		Member findMember = memberRepository.getById(savedMember.getId()); // id 로 저장된 멤버 찾아오기
		assertThat(findMember).isEqualTo(savedMember); // 저장할 멤버와 저장된 멤버가 동일한지 확인
	}
	
	@Test
	void findAll() {
		//given
		Member member1 = new Member("member1", 20);
		Member member2 = new Member("member2", 30);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		//when
		List<Member> result = memberRepository.getAll();
		
		//then
		assertThat(result.size()).isEqualTo(2); // result에는 2개의 Member 가 들어있음을 확인
		assertThat(result).contains(member1, member2); // member1과 member2를 포함하는 지 확인
	}
}
