package mvc.servelt.web.frontcontroller.ver1.controller;

import java.util.Map;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;
import mvc.servelt.web.frontcontroller.ModelView;
import mvc.servelt.web.frontcontroller.ver1.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paramMap) {
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		ModelView mv = new ModelView("save-result");
		mv.getModel().put("member", member);
		return mv;
	}
}