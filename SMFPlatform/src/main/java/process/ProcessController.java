package process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import process.Process;

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
	
	@RequestMapping("/process")
    public String manage(Model model) {
		List<Process> processList = processDao.selectAll(); 
				
		model.addAttribute("processList", processList);
    	return "test3";
    }
	/*
	@GetMapping("/test3")
	public String one(Model model) {
		return test3
	} 
	*/
	
}
