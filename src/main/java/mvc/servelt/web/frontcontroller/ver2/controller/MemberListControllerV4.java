package mvc.servelt.web.frontcontroller.ver2.controller;

import java.util.List;
import java.util.Map;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;
import mvc.servelt.web.frontcontroller.ver2.ControllerV4;

public class MemberListControllerV4 implements ControllerV4{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		List<Member> members = memberRepository.getAll();
		
		model.put("members", members);
		return "members";
	}
}
