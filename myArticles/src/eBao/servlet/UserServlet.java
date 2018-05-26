package eBao.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eBao.bean.PageBean;
import eBao.dao.UserDAOImpl;
import eBao.po.Articles;
import eBao.po.UserInfo;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserDAOImpl udi;
    public UserServlet() {
        udi=new UserDAOImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String function=request.getParameter("func");
		if("favorite".equals(function))
			checkFavorites(request,response);//查看收藏文章
		else if("myartis".equals(function))
			checkOwnArtis(request,response);//查看自己的文章
		else if("addFavor".equals(function))
			addFavorite(request,response);//添加到收藏列表
		else if("delarti".equals(function))
			deleteArti(request,response);//删除文章
		else if("logoff".equals(function))
			userLogoff(request,response);//注销
		else if("guess".equals(function))
			guessLike(request,response);//猜你喜欢
		else if("history".equals(function))
			getHistory(request,response);//获得历史记录
	}
	
	//获得阅读历史记录列表
	private void getHistory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserInfo ui=(UserInfo) request.getSession().getAttribute("user");
		List<Articles> artiList=udi.getReadRecord(ui);
		
		PageBean pb=new PageBean();
		pb.setCurrentPage(1);
		pb.setTotalPage(1);
		pb.setPerPageRows(artiList.size());
		pb.setTotalSize(artiList.size());
		
		request.setAttribute("articles",artiList);
		request.setAttribute("pageBean",pb);
		request.setAttribute("pageTitle", "Your History");
		request.getRequestDispatcher("articles.jsp").forward(request, response);
	}

	//猜你喜欢
	private void guessLike(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserInfo ui=(UserInfo) request.getSession().getAttribute("user");
		List<Articles> artiList=udi.getGuessLike(ui.getUserid());
		
		PageBean pb=new PageBean();
		pb.setCurrentPage(1);
		pb.setTotalPage(1);
		pb.setPerPageRows(artiList.size());
		pb.setTotalSize(artiList.size());
		
		request.setAttribute("articles",artiList);
		request.setAttribute("pageBean",pb);
		request.setAttribute("pageTitle", "You May Like");
		request.getRequestDispatcher("articles.jsp").forward(request, response);
	}


	//退出登录
	private void userLogoff(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect("home.jsp");
		
	}


	//刪除文章
	private void deleteArti(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserInfo ui=(UserInfo)request.getSession().getAttribute("user");
		if(ui!=null){
			String ano=request.getParameter("ano");
			udi.deleteArticle(ui,ano);
		}
		
	}


	// 收藏文章
	private void addFavorite(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		UserInfo ui = (UserInfo) request.getSession().getAttribute("user");
		if (ui != null) {
			String ano = request.getParameter("ano");
			String userid = ui.getUserid();
			if (!udi.isFavorite(ano, userid)) {
				udi.addFavorite(ano, userid);
				out.print("succ");
				out.flush();
			}
			else{
				out.print("already");
			}
		} else
			out.print("login");
	}

	//查看自己的文章
	private void checkOwnArtis(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		UserInfo ui=(UserInfo) request.getSession().getAttribute("user");
		List<Articles> artiList=udi.getOwnArtis(ui.getUserid());
		//System.out.println("UserServlet/num="+artiList.size());
		
		PageBean pb=new PageBean();
		pb.setCurrentPage(1);
		pb.setTotalPage(1);
		pb.setPerPageRows(artiList.size());
		pb.setTotalSize(artiList.size());
		
		request.setAttribute("articles",artiList);
		request.setAttribute("pageBean",pb);
		request.getRequestDispatcher("manageArtis.jsp").forward(request, response);
		
	}

	//查看所有收藏的文章
	private void checkFavorites(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserInfo ui=(UserInfo) request.getSession().getAttribute("user");
		List<Articles> artiList=udi.getFavoriteArtis(ui.getUserid());
		//System.out.println("UserServlet/num="+artiList.size());
		
		PageBean pb=new PageBean();
		pb.setCurrentPage(1);
		pb.setTotalPage(1);
		pb.setPerPageRows(artiList.size());
		pb.setTotalSize(artiList.size());
		
		request.setAttribute("articles",artiList);
		request.setAttribute("pageBean",pb);
		request.setAttribute("pageTitle", "Favorite");
		request.getRequestDispatcher("articles.jsp").forward(request, response);
		
	}

}
