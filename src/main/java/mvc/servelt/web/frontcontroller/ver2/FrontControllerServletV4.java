package mvc.servelt.web.frontcontroller.ver2;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.servelt.web.frontcontroller.ViewRendering;
import mvc.servelt.web.frontcontroller.ver2.controller.MemberFormControllerV4;
import mvc.servelt.web.frontcontroller.ver2.controller.MemberListControllerV4;
import mvc.servelt.web.frontcontroller.ver2.controller.MemberSaveControllerV4;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet{
	
	// 매핑 정보
	// 어떤 URL을 받으면 어떤 컨트롤러를 호출할지 
	private Map<String, ControllerV4> controllerMap = new HashMap<>();
	
	// 생성자 호출 시 controllerMap에 String, ControllerV2(객체 인스턴스) 형식으로 미리 넣어준다.
	public FrontControllerServletV4() {
		controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
	}
	
	// request에 담긴 정보를 이용해 response 해주기! (요청에 따른 응답) 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String requestURI = request.getRequestURI(); // 요청시 URI 가져오기
		
		ControllerV4 controller = controllerMap.get(requestURI); // 위 생성자에서 담은 controllerMap에서 String(=URI)에 맞는 객체 인스턴스를 담아주기
		if (controller == null) { // 꺼내온 게 null 이면(= 해당 key로 부터 value를 못받았으면 null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 요청한 페이지 없음을 띄움
			return;
		}
		
//		MyView view = controller.process(request, response); 
//		view.render(request, response); 
		
		// paramMap  파라미터로 받아온 애들을 Map에 넣어준다
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<>(); // 추가됨
		
		String viewName = controller.process(paramMap, model);  
		ViewRendering view = viewResolver(viewName);	
		
		view.render(model, request, response); // Model도 넘겨줘야 함
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

