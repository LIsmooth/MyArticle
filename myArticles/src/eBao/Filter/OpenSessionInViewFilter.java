package eBao.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import eBao.util.HibernateUtil;

/**
 * Servlet Filter implementation class OpenSessionInViewFilter
 */
public class OpenSessionInViewFilter implements Filter {

	public OpenSessionInViewFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		String path = hrequest.getRequestURI();
		if (path.length() > 7) { //根据命名规则长度小于Servlet的不用session
			if ("Servlet".equals(path.substring(path.length() - 7))) {
				System.out.println("OpenSessionInViewFilter");
				Session session = null;
				Transaction ts = null;
				try {
					session = HibernateUtil.currentSession();
					ts = session.beginTransaction();
					// 执行请求处理链
					chain.doFilter(request, response);
					// 返回响应时，提交事务
					if (session.isOpen()){//如果session还开启，则关闭
						System.out.println("OpenSessionInView关闭");
						ts.commit();
						HibernateUtil.closeSession();
					}
				} catch (HibernateException e) {
					e.printStackTrace();
					ts.rollback();//发生异常，回滚操作
				}
			} else
				chain.doFilter(request, response);
		} else
			chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
