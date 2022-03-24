package mvc.servelt.web.springmvc.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//기존 mvc 패턴의 구조를 최대한 변경하지 않으면서 spring만 적용한 버전
@Controller // 스프링이 자동으로 스프링 빈으로 등록 (스프링에서 컴포넌트 스캔의 대상이 됨), 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.(핸들러 매핑을 찾아냄)
//class 레벨에 @Component 와 @RequestMapping 이 같이 있으면 @Controller 처럼 사용가능  
//@RequestMapping
public class SpringMemberFormControllerFirst {
	
	@RequestMapping("/springmvc/v1/members/new-form") // 핸들러 매핑
	public ModelAndView process() { // ModelAndView 가 핸들러 어댑터, 뷰 리졸버까지 (모델과 뷰 정보를 처리해줌)
		return new ModelAndView("create-form"); // viewName을 넘김
	}
}
