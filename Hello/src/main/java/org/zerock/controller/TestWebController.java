package org.zerock.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 이걸쓰면 여기가 컨트롤러다~ 하는거임
@RequestMapping("/test") // 주소설정 하는 골뱅이
public class TestWebController {
	
	@GetMapping("/hello") //  주소가 "/test/hello" 인거임
	public String hello() {
		return "hello"; // hello.jsp로 forwarding 한다는 뜻
	}
	
//	@GetMapping("/hello2")  //  주소가 "/test/hello2" 가 되는거임
	@RequestMapping(value="/hello2",produces="application/json;charset=UTF-8") // 한글깨짐방지
	// value는 주소 , produces는 json으로 보내고 한글깨짐 방지할거다 인거임
	@ResponseBody // 화면으로 안간다! << 여기선 hello라는 글자를 출력하는거임
	public String hello2(String msg) throws UnsupportedEncodingException {
		return msg; // msg 값이 클라이언트로 전송. view로 가는게 아님
	}
	
//	이전버전 근데 출력하려는 문자가 한글이라 깨짐
//	public String hello2(@RequestParam(value="msg", required=false) String msg) {
//		return msg; // msg 값이 클라이언트로 전송. view로 가는게 아님
//	}
	
	@GetMapping("/hello3/{msg}") // 주소가 "/test/hello3/메시지" 지금은 메시지가 안녕임
										// => /test/hello3?msg=안녕	 이걸 깔끔하게 보이게 한거임
//	URL 동적 패러미터
	public String hello3(@PathVariable String msg, Model m) {
		m.addAttribute("msg", msg); // request.setAttribute("msg", msg) 와 같은 역할
		return "hello"; // hello.jsp로 가는 역할
	}
}
