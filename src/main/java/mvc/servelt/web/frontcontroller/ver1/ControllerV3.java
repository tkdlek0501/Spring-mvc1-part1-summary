package mvc.servelt.web.frontcontroller.ver1;

import java.util.Map;

import mvc.servelt.web.frontcontroller.ModelView;

public interface ControllerV3 {
	
	// ModelView 객체를 생성하는 용도로 쓸 메서드
	ModelView process(Map<String, String> paramMap);
}
