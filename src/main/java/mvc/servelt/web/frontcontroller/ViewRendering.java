package mvc.servelt.web.frontcontroller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewRendering {
	private String viewPath;
	
	// 생성자 호출 시 viewPath 넣어주기
	public ViewRendering(String viewPath){
		this.viewPath = viewPath;
	}
	
	// view로 이동시키기 위한 부분을 이 컨트롤러에서 메서드로 만들어줌
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("render 호출됨 viewPath : " + viewPath);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("model: " + model);
		
		// model(Map) 안에 있는 data를 반복해서 request에 담음
		modelToRequestAttribute(model, request); 
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
	
	// model 에 있는 객체를 request에 담는 메서드
	private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		model.forEach((key, value) -> request.setAttribute(key, value));
	}
}
