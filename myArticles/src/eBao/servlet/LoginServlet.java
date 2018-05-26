package eBao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eBao.dao.UserDAOImpl;
import eBao.po.UserInfo;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getParameter("func");
		if("login".equals(function))
			userLogin(request,response);//用户登录
		else if("haslogin".equals(function))
			hasLogin(request,response);//判断是否登录
		else if("register".equals(function))
			userRegister(request,response);//用户注册
		else if("isexist".equals(function))
			isExist(request,response);//判断用户名是否已被注册
	}

	/**
	 * 判断用户名是否已被注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void isExist(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String uid=request.getParameter("uid");
		if(uid!=null&&new UserDAOImpl().isExist(uid)){
			out.print("ok");
		}else{
			out.print("no");
		}
		
	}


	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userRegister(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String uid=request.getParameter("userid");
		String nickname=request.getParameter("nickname");
		String pwd=request.getParameter("psw");
		UserInfo ui=new UserInfo(uid,pwd,nickname);
		UserDAOImpl udi=new UserDAOImpl();
		if(!udi.isExist(ui.getUserid())){
			udi.userRegister(ui);
			request.getSession().setAttribute("user",ui);
			response.sendRedirect("userFunction.jsp");
		}
		else
			response.sendRedirect("login.jsp");
		
	}

	/**
	 * 判断是否登录
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void hasLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if(request.getSession().getAttribute("user")==null)
			request.getRequestDispatcher("login.jsp").forward(request, response);
		else
			request.getRequestDispatcher("userFunction.jsp").forward(request, response);
		
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void userLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		UserDAOImpl udi=new UserDAOImpl();
		UserInfo ui=udi.userLogin(userid, pwd);
		if(ui!=null){
			request.getSession().setAttribute("user",ui);
			response.sendRedirect("userFunction.jsp");
		}
		else{
			response.sendRedirect("login.jsp");
		}
	}

}
