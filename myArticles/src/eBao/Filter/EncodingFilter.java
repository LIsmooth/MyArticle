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
 * Servlet Filter implementation class EncodingFilter
 * 编码过滤器
 */
public class EncodingFilter implements Filter {

	private String charSet;
	private String resource;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hrequest = (HttpServletRequest) request;
		String uri = hrequest.getRequestURI();
		String page = uri.substring(uri.lastIndexOf("/") + 1);
		
		if (isExcludeResource(page, resource)) {
			chain.doFilter(request, response);
		} else {
			System.out.println("EncodingFilter--"+page+"--"+charSet);
			request.setCharacterEncoding(charSet);
			response.setCharacterEncoding(charSet);
			chain.doFilter(request, response);
		}
	}

	public boolean isExcludeResource(String page, String source) {

		String[] r = source.split(";");

		for (String re : r) {
			if (page.endsWith(re)) {
				return true;
			}
		}
		return false;

	}

	public void init(FilterConfig fConfig) throws ServletException {
		charSet = fConfig.getInitParameter("charset");
		resource = fConfig.getInitParameter("excludeResource");
	}

}
