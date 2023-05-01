package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {

	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(
			MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@RequestMapping("/register/step1") // 요청을 받음
	public String handleStep1() {
		return "register/step1"; // webapp/WEB-INF/view/register/step1.jsp
	}

	@PostMapping("/register/step2")	// 요청을 받음
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree, Model model) {
		if (agree != true) {
			return "register/step1"; // step1.jsp
		}
		model.addAttribute("registerRequest", new RegisterRequest());	 // 값을 받아오기 위한 new RegisterRequest()를 만듦	
		return "register/step2";	// forward(step2.jsp)
	}
	
	@PostMapping("/register/step2EL")	// 요청을 받음
	public String handleStep2EL(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree, Model model) {
		if (agree != true) {
			return "register/step1"; // step1.jsp
		}

		RegisterRequest registerRequest = new RegisterRequest();
		registerRequest.setEmail("hgd@abc.co.kr");
		registerRequest.setName("홍길동");
		model.addAttribute("registerRequest", registerRequest);
		return "register/step2EL";	// forward(step2EL.jsp)
	}


	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}

	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq) {
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			return "register/step2";
		}
	}

}
