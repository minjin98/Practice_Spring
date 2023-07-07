package process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import process.ProcessBean;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessController {
	
	private ProcessDao processDao;
	
	public ProcessController(ProcessDao processDao) {
		this.processDao = processDao;
	}
	
	@GetMapping("/process") // 주소창에 /process 입력시 실행
    public String single_value(Model model) {
		List<ProcessBean> processList = processDao.selectAll(); 
		System.out.println("processList" + processList);
		model.addAttribute("processList", processList);
    	return "test3.jsp";
    }
	/*
	@GetMapping("/test3")
	public String one(Model model) {
		return test3
	} 
	*/
	
}
