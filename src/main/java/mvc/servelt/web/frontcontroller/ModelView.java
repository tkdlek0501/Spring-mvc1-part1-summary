package mvc.servelt.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelView {
	
	
	private String viewName;
	
	// view에 보내줄 data를 Map 형태로
	private Map<String, Object> model = new HashMap<>();
	
	// 생성자 : viewName을 초기화
	public ModelView(String viewName) {
		this.viewName = viewName;
	}
}
