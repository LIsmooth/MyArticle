package eBao.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	private String resource;
	
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		String page = uri.substring(uri.lastIndexOf("/") + 1);
		
		//如果资源需要用户登录，则跳转到登录界面，如果不需要则继续
		if (isExcludeResource(page, resource)) { 
			if(hrequest.getSession().getAttribute("user")!=null)
				chain.doFilter(request, response);
			else
				hrequest.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	//字符串分解，然后判断是否存在资源
	public boolean isExcludeResource(String page, String source) {

		String[] r = source.split(";");

		for (String re : r) {
			if (page.equals(re)) {
				return true;
			}
		}
		return false;

	}

	public void init(FilterConfig fConfig) throws ServletException {
		resource = fConfig.getInitParameter("excludeResource");
	}

}
