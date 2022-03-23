package mvc.servelt.web.frontcontroller.ver2;

import java.util.Map;

public interface ControllerV4 {
	
	/**
	 * @param paramMap
	 * @param model
	 * @return viewName
	 */
	String process(Map<String, String> paramMap, Map<String, Object> model);
}
