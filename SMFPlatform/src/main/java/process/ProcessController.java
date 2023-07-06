package process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import process.Process;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessController {
	
	@RequestMapping("/process")
    public String manage() {
    	return "test3";
    }
	@PostMapping("/test3")
	
	
}
