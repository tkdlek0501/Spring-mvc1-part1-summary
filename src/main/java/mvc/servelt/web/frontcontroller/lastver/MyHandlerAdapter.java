package mvc.servelt.web.frontcontroller.lastver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.servelt.web.frontcontroller.ModelView;

public interface MyHandlerAdapter {
	
	// 어떤 handler를 지원하는지 알아보기 위해
	boolean supports(Object handler);
	
	// ModelView를 만들어주기 위해
	ModelView handle(HttpServletRequest request, HttpServletResponse response, Object object) throws ServletException, IOException;
}
