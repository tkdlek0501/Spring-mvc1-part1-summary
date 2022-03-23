package mvc.servelt.web.frontcontroller.lastver.adapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.servelt.web.frontcontroller.ModelView;
import mvc.servelt.web.frontcontroller.lastver.MyHandlerAdapter;
import mvc.servelt.web.frontcontroller.ver1.ControllerV3;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter{
	
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV3); // hadler가 v3일때만 true 반환 (타입이 같은지) 
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

		ControllerV3 controller = (ControllerV3) handler;
		
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);
		
		return mv;
	}
	
	// 디테일한 로직이 들어가므로 메서드를 따로 만들어줌
	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		// java8 에서 request에서 paramName으로 꺼내오기 위해
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			paramMap.put(paramName, request.getParameter(paramName));
		}
		return paramMap;
	}
}
