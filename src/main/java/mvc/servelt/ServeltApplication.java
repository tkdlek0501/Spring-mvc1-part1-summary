package mvc.servelt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //서블릿 자동 등록; 스프링 부트는 서블릿을 직접 등록해서 사용할 수 있도록 이 어노테이션을 지원한다. -> 이 어노테이션 없으면 서블릿 직접 등록 못함
@SpringBootApplication
public class ServeltApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeltApplication.class, args);
	}

}

//servlet 이란?
//클라이언트의 요청을 처리하고, 그 결과를 반환하는 
//Servlet 클래스의 구현 규칙을 지킨 자바 웹 프로그래밍 기술
//출처: https://mangkyu.tistory.com/14 [MangKyu's Diary]