package mvc.servelt.web.frontcontroller.ver1.controller;

import java.util.List;
import java.util.Map;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;
import mvc.servelt.web.frontcontroller.ModelView;
import mvc.servelt.web.frontcontroller.ver1.ControllerV3;

public class MemberListControllerV3 implements ControllerV3{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public ModelView process(Map<String, String> paramMap) {
		List<Member> member = memberRepository.getAll();
		
		for(int i=0;i<member.size();i++) {
			System.out.println("전체 member : " + member.get(i));
		}
		
		ModelView mv = new ModelView("members"); // uri 이름 
		mv.getModel().put("member", member);
		return mv;
	}
	
}
