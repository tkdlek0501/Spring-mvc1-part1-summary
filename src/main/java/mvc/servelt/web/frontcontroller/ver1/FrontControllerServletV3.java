package mvc.servelt.web.frontcontroller.ver1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.servelt.web.frontcontroller.ModelView;
import mvc.servelt.web.frontcontroller.ViewRendering;
import mvc.servelt.web.frontcontroller.ver1.controller.MemberFormControllerV3;
import mvc.servelt.web.frontcontroller.ver1.controller.MemberListControllerV3;
import mvc.servelt.web.frontcontroller.ver1.controller.MemberSaveControllerV3;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // /front-controller/v3/ ~ 으로 시작하는 모든 URL은 이 servlet이 호출된다
public class FrontControllerServletV3 extends HttpServlet{
	
	// 매핑 정보
		// 어떤 URL을 받으면 어떤 컨트롤러를 호출할지 
		private Map<String, ControllerV3> controllerMap = new HashMap<>();
		
		// 생성자 호출 시 controllerMap에 String, ControllerV2(객체 인스턴스) 형식으로 미리 넣어준다.
		public FrontControllerServletV3() {
			System.out.println("V2 생성자"); // 어느 시점에 생기는 지 확인 (최초 run할 때; ServletApplication에서 설정돼있음을 기억!)
			controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
			controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
			controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
		}
		
		// request에 담긴 정보를 이용해 response 해주기! (요청에 따른 응답) 
		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			System.out.println("FrontControllerServletV3.service 출력되나요?"); // 현재 컨트롤러를 호출하는 URL로 요청시 실행 되는지 확인용
			
			String requestURI = request.getRequestURI(); // 요청시 URI 가져오기
			
			System.out.println("V3 requestURI : " + requestURI);
			
			ControllerV3 controller = controllerMap.get(requestURI); // 위 생성자에서 담은 controllerMap에서 String(=URI)에 맞는 객체 인스턴스를 담아주기
			if (controller == null) { // 꺼내온 게 null 이면(= 해당 key로 부터 value를 못받았으면 null)
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 요청한 페이지 없음을 띄움
				return;
			}
			
//			MyView view = controller.process(request, response); 
//			view.render(request, response); 
			
			// paramMap  파라미터로 받아온 애들을 Map에 넣어준다
			Map<String, String> paramMap = createParamMap(request);
			ModelView mv = controller.process(paramMap); // 확인 
			String viewName = mv.getViewName(); // 논리 이름 ex. new-form
			ViewRendering view = viewResolver(viewName);	
			
			view.render(mv.getModel(), request, response); // Model도 넘겨줘야 함
		}
		
		private ViewRendering viewResolver(String viewName) {
			return new ViewRendering("/WEB-INF/views/" + viewName + ".jsp"); // 실제 URI 경로
		}

		// 디테일한 로직이 들어가므로 메서드를 따로 만들어줌
		private Map<String, String> createParamMap(HttpServletRequest request) {
			Map<String, String> paramMap = new HashMap<>();
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				System.out.println("paramName : " + paramName);
				paramMap.put(paramName, request.getParameter(paramName));
			}
			return paramMap;
		}
}
