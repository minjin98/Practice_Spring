package chap09;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("/hello") // HTTP : GET 방식으로 요청을 받음
	public String hello(Model model,
			@RequestParam(value = "name", required = false) String name) { // "name" 값을 받아서 String name에 주입
		// 앞의 "greeting"에 뒤의 "안녕하세요,"+name(위에서 전달받은 name 값) 주입
		System.out.println("[HelloController] hello() : /hello");
		model.addAttribute("greeting", "안녕하세요, " + name);  
		return "hello";	// 뷰 이름 : hello.jsp
	}

	@GetMapping("/hello2")
	public String hello2(Model model, HttpServletRequest request) {
		String name = request.getParameter("name");
		model.addAttribute("greeting", "Welcome to " + name);
		return "hello";	
	}

	@GetMapping("/hello3") // HTTP : GET 방식으로 요청을 받음
	public String hello3(Model model,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email){
	
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "hello3";	
	}
	
	@GetMapping("/hello4")
	public String hellopos3(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		model.addAttribute("greeting", "Welcome to " + name);
	
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "hello3";	
	}
	
	@PostMapping("/hellopost") // html 파일에서 action = "hellopost"로 전달을 받음(주소찾기)
	public String hellopost(Model model, HttpServletRequest request) {
		System.out.println("[HelloController] /hellopost");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		model.addAttribute("greeting", "Welcome to " + name);
	
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "hello3";	
	}

	
}