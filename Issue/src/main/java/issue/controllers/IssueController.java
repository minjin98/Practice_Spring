package issue.controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/issue1")
public class IssueController extends HttpServlet {

	@Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("/issue1");
		
        response.setContentType("text/html");
        
        // RequestDispatcher disp = request.getRequestDispatcher("http://localhost:8584/Issue/WEB-INF/issues/issue1.jsp");
        RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/issues/issue1.jsp");
        disp.forward(request, response);
    }
}