package mvc.servelt.web.springmvc.first;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;

@Controller
public class SpringMemberSaveControllerFirst {
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@RequestMapping("/springmvc/v1/members/save")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		ModelAndView mv = new ModelAndView("save-result"); // mv.setViewName("save-result");
		mv.addObject("member", member);
		return mv;
	}
	
}