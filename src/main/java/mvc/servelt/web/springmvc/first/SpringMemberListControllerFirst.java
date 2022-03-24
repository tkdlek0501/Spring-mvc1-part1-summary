package mvc.servelt.web.springmvc.first;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;

@Controller
public class SpringMemberListControllerFirst {
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@RequestMapping("/springmvc/v1/members")	
	public ModelAndView process() {
		List<Member> member = memberRepository.getAll();
		System.out.println("spring이용 가져온 member : " + member);
		
		ModelAndView mv = new ModelAndView("members");  // view 정보 
		mv.addObject("members", member); // model 정보
		return mv;
	}
}
