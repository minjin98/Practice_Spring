package home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String hello(Model model) {
		System.out.println("[HelloController] hello() : /hello");
		model.addAttribute("welcome", "환영합니다.");
		return "hello";
	}
	
} 
