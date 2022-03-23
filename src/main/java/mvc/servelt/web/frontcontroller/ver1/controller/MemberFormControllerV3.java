package mvc.servelt.web.frontcontroller.ver1.controller;

import java.util.Map;

import mvc.servelt.web.frontcontroller.ModelView;
import mvc.servelt.web.frontcontroller.ver1.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3{
	
	@Override
	public ModelView process(Map<String, String> paramMap) {
		return new ModelView("create-form"); // viewName을 넣어서 객체 인스턴스 생성
	}
}
