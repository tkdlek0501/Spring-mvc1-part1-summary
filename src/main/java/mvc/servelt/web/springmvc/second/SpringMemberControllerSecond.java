package mvc.servelt.web.springmvc.second;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.servelt.domain.member.Member;
import mvc.servelt.domain.member.MemberRepository;

//@RequestMapping은 클래스 레벨이 아닌 메서드 레벨로 가능하기 때문에 하나의 컨트롤러 내에서 여러 매핑이 가능 (즉 컨트롤러 하나로 같은 주제?에 대해서 묶어 놓을 수 있음)
@Controller
@RequestMapping("/springmvc/v3/members") // 공통되는 매핑 주소
public class SpringMemberControllerSecond {
	
private MemberRepository memberRepository = MemberRepository.getInstance();
	
	// @RequestMapping(value = "/new-form", method = RequestMethod.GET) // RequestMapping을 쓰면서 HTTP 메서드를 지정해줘야 좋은 설계가 된다(URI에 리소스만 쓸 수 있는 설계)
	@GetMapping("/new-form")
	public String newForm() { 
		return "create-form"; // 문자로 반환해도 viewName으로 인식해서 이런식으로 간단하게 만들 수 있다.
	}

// ModelAndView를 사용하는 방법	
//	@RequestMapping("")	
//	public ModelAndView members() { // ModelAndView 가 핸들러 어댑터, 뷰 리졸버까지 (모델과 뷰 정보를 처리해줌)
//		List<Member> member = memberRepository.findAll();
//		System.out.println("spring이용 가져온 member : " + member);
//		
//		ModelAndView mv = new ModelAndView("members");  // view 정보 
//		mv.addObject("members", member); // model 정보 
//		return mv;
//	}
	
	// @RequestMapping(value = "", method = RequestMethod.GET)	
	@GetMapping("")
	public String members(
			Model model
			) { 
		List<Member> member = memberRepository.getAll();
		
		model.addAttribute("members", member);
		return "members";
	}
	
	
//	@RequestMapping("/save")
//	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
//		String username = request.getParameter("username");
//		int age = Integer.parseInt(request.getParameter("age"));
//		
//		Member member = new Member(username, age);
//		memberRepository.save(member);
//		
//		ModelAndView mv = new ModelAndView("save-result"); // mv.setViewName("save-result");
//		mv.addObject("member", member);
//		return mv;
//	}
	
	// 위코드를 유연하게 쓰기 위해 @RequestParam, String 반환 등을 사용해 수정
	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PostMapping("/save")
	public String save(
			@RequestParam("username") String username,
			@RequestParam("age") int age,
			Model model
			) {
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		model.addAttribute("member", member);
		return "save-result";
	}
}
