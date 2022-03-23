package mvc.servelt.web.frontcontroller.lastver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.servelt.web.frontcontroller.ModelView;
import mvc.servelt.web.frontcontroller.ViewRendering;
import mvc.servelt.web.frontcontroller.lastver.adapter.ControllerV3HandlerAdapter;
import mvc.servelt.web.frontcontroller.lastver.adapter.ControllerV4HandlerAdapter;
import mvc.servelt.web.frontcontroller.ver1.controller.MemberFormControllerV3;
import mvc.servelt.web.frontcontroller.ver1.controller.MemberListControllerV3;
import mvc.servelt.web.frontcontroller.ver1.controller.MemberSaveControllerV3;
import mvc.servelt.web.frontcontroller.ver2.controller.MemberFormControllerV4;
import mvc.servelt.web.frontcontroller.ver2.controller.MemberListControllerV4;
import mvc.servelt.web.frontcontroller.ver2.controller.MemberSaveControllerV4;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServlet extends HttpServlet {
	
	private final Map<String, Object> handlerMappingMap = new HashMap<>(); // value가 Object(최상위 객체니까 모든 객체 담을 수 있음)
	private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
	
	// 생성자 : 객체 인스턴스 초기화
	public FrontControllerServlet() {
		initHandlerMappingMap();
		initHandlerAdapters();
	}
	
	// 어떤 객체 인스턴스를 호출할지 전에 모두 담아줌 
	// uri 에 따라 다른 객체
	private void initHandlerMappingMap() { 
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
		
		// V4 추가
		handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
	}
	
	// 어떤 adapter를 사용할지 전에 모두 담아줌
	private void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV3HandlerAdapter());
		handlerAdapters.add(new ControllerV4HandlerAdapter());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Object handler = getHandler(request); // 어떤 객체 사용하는지
		if (handler == null) { // 꺼내온 게 null 이면(= 해당 key로 부터 value를 못받았으면 null)
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 요청한 페이지 없음을 띄움
			return;
		}
		
		MyHandlerAdapter adapter = getHandlerAdapter(handler); // 어떤 adapter를 지원하는 지 //alt + shift + t 로 리팩토링 (getHandlerAdapter로 따로 빼줌)
		ModelView mv = adapter.handle(request, response, handler);
		
		ViewRendering view = viewResolver(mv.getViewName());
		view.render(mv.getModel(), request, response); // Model도 넘겨줘야 함
	}
	
	// URI로 부터 가져온 객체 반환
	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI(); // 요청시 URI 가져오기
		return handlerMappingMap.get(requestURI); // URI에 맞는 객체 반환
	}
	
	// 객체(인스턴스)로 부터 가져온 adapter 반환
	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for (MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.supports(handler)) { // handler를 지원하는 지 확인 후 true 이면 대입 
				return adapter;
			}
		}
		throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler : " + handler);
	}

	
	
	private ViewRendering viewResolver(String viewName) {
		return new ViewRendering("/WEB-INF/views/" + viewName + ".jsp"); // 실제 URI 경로
	}
}
