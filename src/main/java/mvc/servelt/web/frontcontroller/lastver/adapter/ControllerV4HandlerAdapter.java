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
import mvc.servelt.web.frontcontroller.ver2.ControllerV4;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter{
	
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV4);
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		ControllerV4 controller = (ControllerV4) handler;
		
		Map<String, String> paramMap = createParamMap(request);
		HashMap<String, Object> model = new HashMap<>();
		
		String viewName = controller.process(paramMap, model);
		
		ModelView mv = new ModelView(viewName);
		mv.setModel(model);
		
		return mv;
	}
	
	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			paramMap.put(paramName, request.getParameter(paramName));
		}
		return paramMap;
	}
}
