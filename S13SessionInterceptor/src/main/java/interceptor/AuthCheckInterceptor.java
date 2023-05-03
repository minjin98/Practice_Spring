package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {
	/*
	 * 컨트롤러 객체가 실행하기 전에 실행되어 가로챈다. 
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {
		// request.getSession(false) 파라미터
		// 1. 세션 객체가 없으면 null 리턴
		// 2. 세션 객체가 있으면 존재하는 세션 객체를 리턴
		HttpSession session = request.getSession(false);	
		if (session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if (authInfo != null) {
				return true;
			}
		}
		//response.sendRedirect(request.getContextPath() + "/login");
		//return false;
		return true;
	}

}
