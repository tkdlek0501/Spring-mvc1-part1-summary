package mvc.servelt.web.frontcontroller.ver2.controller;

import java.util.Map;

import mvc.servelt.web.frontcontroller.ver2.ControllerV4;

public class MemberFormControllerV4 implements ControllerV4{
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		return "create-form";
	}
}
