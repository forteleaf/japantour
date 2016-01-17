package leaf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/user/*")
public class LoginFilter implements Filter{

	public LoginFilter() {
	}
	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		boolean isLogin = false;
		if(session!=null){
			String id = (String) session.getAttribute("id");
			if(id!=null){
				isLogin = true;
			}
		}
		if(isLogin){
			chain.doFilter(req, res);
		}else{
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect("layout.jsp");
		}
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
