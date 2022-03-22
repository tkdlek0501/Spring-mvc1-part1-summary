package mvc.servelt.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Member 저장소
//동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야 한다.

public class MemberRepository {
	
	// store는 DB대신 사용하는 저장소 
	private static Map<Long, Member> store = new HashMap<>();
	// sequence: DB의 PK (1씩 autoincreased 되는 것처럼)
	private static long sequence = 0L;
	
	// final 로 객체 인스턴스 생성
	private static final MemberRepository instance = new MemberRepository();
	
	// 이 메서드로 instance 생성이 가능
	public static MemberRepository getInstance() {
		return instance;
	}
	
	// 다른 곳에서 접근 안되게 (다른 곳에서의 인스턴스 생성 막음)
	private MemberRepository() {
	}
	
	// Member 저장 메서드
	public Member save(Member member) {
		member.setId(++sequence); // PK 1 씩 증가
		store.put(member.getId(), member); // store에 data 넣기�
		return member; // member 반환
	}
	
	// id로 Member 찾아오기 메서드
	public Member getById(Long id) {
		return store.get(id); 
	}
	
	// store에 저장된 모든 Member 가져오기
	public List<Member> getAll(){
		return new ArrayList<>(store.values()); // store 의 변경 안되게 하기 위해
	}
	
	// store 초기화; test를 위해서
	public void clearStore() {
		store.clear();
	}
}
