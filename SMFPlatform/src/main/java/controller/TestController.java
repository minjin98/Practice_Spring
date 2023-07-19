package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test")
    public String test1() {
    	return "test/test";
    }
	/*
	@RequestMapping("/testOR")
    public String test2() {
    	return "test/testOR";
    }
 */
}
