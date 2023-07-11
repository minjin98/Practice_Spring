package process;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import process.ProcessBean;

import org.json.simple.JSONArray;
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
	//---------------------------------------------------------------------------------
	
	@PostMapping("/process") // 주소창에 /process 입력시 실행
	public void doChart_gauge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("[GaugeChart]");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		// DB에 저장된 값을 process_gauge 변수에 저장
		String process_gauge = processDao.selectGauge();
		System.out.println("Gauge Chart : " + process_gauge);
		// JSON 배열 선언
		JSONArray chart = new JSONArray();
		// xvals에 DB에서 받아온 변수값 저장
		String xvals = process_gauge;
		chart.add(xvals);
		
		String jsonInfo = chart.toJSONString();
		System.out.println(jsonInfo);
		writer.print(jsonInfo);
	}
	//---------------------------------------------------------------------------------
	
	@PostMapping("/process1") // 주소창에 /process 입력시 실행
	public void doChart_rate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("[PieChart]");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		// DB에 저장된 값을 process_gauge 변수에 저장
		// List<ProcessBean>process = processDao.select_rate();
		ProcessBean process = processDao.select_rate();
		System.out.println("Pie Chart : " + process);
		// JSON 배열 선언
		JSONArray chart = new JSONArray();
		// xvals에 DB에서 받아온 변수값 저장
		// for(ProcessBean k : process) {
		chart.add(process.getGoodprod_rate());
		chart.add(process.getBadprod_rate());
		// }
		String jsonInfo = chart.toJSONString();
		System.out.println(jsonInfo);
		writer.print(jsonInfo);
	}
	//---------------------------------------------------------------------------------
	@PostMapping("/process2")
	public void doChart_time(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("[timechart]");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		String process_leadtime = processDao.selectleadtime();
		System.out.println("Leadtime : " + process_leadtime);
		
		List<ProcessBean>process_cycletime = processDao.select_cycletime();
		int count = processDao.count(); 
		System.out.println("Cycletime : " + count);
		
		JSONArray chart = new JSONArray();
		chart.add(process_leadtime);
		
		for(ProcessBean p : process_cycletime) {
			chart.add(p.getCycletime());
		}
		
		/*
		JSONArray chart = new JSONArray();
		chart.add(process_leadtime);
		chart.add(process_cycletime);
		*/
		String jsonInfo = chart.toJSONString();
		System.out.println(jsonInfo);
		writer.print(jsonInfo);
	}
	//---------------------------------------------------------------------------------
	
	/*
	@GetMapping("/test3")
	public String one(Model model) {
		return test3
	} 
	*/
	
}
