package mvc.servelt.web.frontcontroller.ver2.controller;

import java.util.Map;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;
import mvc.servelt.web.frontcontroller.ver2.ControllerV4;

public class MemberSaveControllerV4 implements ControllerV4{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		model.put("member", member);
		return "save-result";
	}
}
