package mvc.servelt.web.servletmvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이 클래스가 MVC의 컨트롤러가 됨
@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet-mvc/members/create-form")
public class MemberFormServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("create-form");
		String viewPath = "/WEB-INF/views/create-form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // 컨트롤러에서 view 로 이동할 때 사용
		dispatcher.forward(request, response); // 컨트롤러에서 view로 제어권을 넘긴다.
		// forward 메서드는 다른 서블릿이나 jsp로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
				// redirect는 응답이 나갔다가 다시 클라이언트 측에서 redirect 경로로 요청하는 것, 클라이언트가 인지할 수 있다.
	}
	
}

//WEB-INF 에 있는 view 템플릿은 webapp에 있는 것 처럼 바로 호출되지 않는다. 컨트롤러를 통해서만 매핑이 된다. (WAS 서버에서 이렇게 처리해 줌)