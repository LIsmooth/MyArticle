package eBao.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import eBao.bean.PageBean;
import eBao.dao.ArticleDAOImpl;
import eBao.dao.TypeInfoDAOImpl;
import eBao.dao.UserDAOImpl;
import eBao.po.ArticleLimit;
import eBao.po.Articles;
import eBao.po.TypeInfo;
import eBao.po.UserInfo;

/**
 * Servlet implementation class ArticleServlet
 */
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArticleDAOImpl adi;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String function = request.getParameter("func");

		try {
			if ("getall".equals(function))
				checkAllArticles(request, response);// 分页浏览所有文章
			else if ("checkArticle".equals(function))
				checkArticle(request, response);// 查看文章
			else if ("getTypes".equals(function))
				getTypes(request, response);// 获得类型列表
			else if ("modify".equals(function))
				modifyArticle(request, response);// 修改文章
			else if (ServletFileUpload.isMultipartContent(request)) {
				uploadArticle(request, response);// 上传文章
			}
		} catch (Exception e) {
			response.sendRedirect("home.jsp");
		}
	}

	/**
	 * 修改文章
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void modifyArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ano = request.getParameter("ano");
		Articles arti = adi.getArticlebyNo(Integer.parseInt(ano));
		request.setAttribute("arti", arti);
		request.getRequestDispatcher("editArticle.jsp").forward(request,
				response);

	}

	/**
	 * 上传文章
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void uploadArticle(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> fileType = new ArrayList<String>();
		fileType.add("gif");
		fileType.add("jpg");
		fileType.add("png");
		fileType.add("jpeg");

		String title = "";
		String content = "";
		String typeno = "";
		String isModify = "";

		String uploadFileName = ""; // 上传的文件名
		String fieldName = ""; // 表单字段元素的name属性值

		// 请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// 上传文件的存储路径（服务器文件系统上的绝对文件路径）
		String uploadFilePath = request.getSession().getServletContext()
				.getRealPath("/images/upload/");
		// String uploadFilePath =
		// "D:\\workspace\\myArticles\\WebContent\\images\\upload";

		// 获得项目个数
		int iNum = 0;

		if (isMultipart) {
			System.out.println("ismultipart");
			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setSizeMax(1024 * 1024 * 10);// 10M大小

			try {

				// 解析form表单中所有文件
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) { // 依次处理每个文件
					FileItem item = (FileItem) iter.next();

					String fileName = item.getName();

					if (item.isFormField()) {//
						fieldName = item.getFieldName(); // 表单字段的name属性值Twilight
						if (fieldName.equals("title")) {
							++iNum;
							title = item.getString("UTF-8");
						}
						if (fieldName.equals("content")) {
							++iNum;
							content = item.getString("UTF-8");
						}
						if (fieldName.equals("typeno")) {
							++iNum;
							typeno = item.getString("UTF-8");
						}
						if (fieldName.equals("isModify")) {
							// 用于判断新增文章还是更新文章
							if (!isModify.equals("0"))
								++iNum;

							isModify = item.getString("UTF-8");
							System.out.println("isModify=" + isModify);
						}
					} else {

						String ext = fileName.substring(fileName
								.lastIndexOf(".") + 1);

						if (fileType.contains(ext)) {

							if (fileName != null && !fileName.equals("")) {
								File fullFile = new File(item.getName());
								File saveFile = new File(uploadFilePath,
										fullFile.getName());
								item.write(saveFile);// 上传
								uploadFileName = fullFile.getName();
								++iNum;
								System.out
										.print("上传成功后的文件名是：" + uploadFileName);
							}
						} else {
							if (isModify.equals("0"))
								System.out.print("错误的图片格式");
							else
								++iNum;
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
				// System.out.println(e);
			}
			System.out.println(iNum);
			UserInfo ui = (UserInfo) request.getSession().getAttribute("user");
			if (ui != null) {
				if (iNum >= 4) {
					Articles arti;
					if (isModify.equals("0"))
						arti = new Articles();
					else
						arti = adi
								.getArticlebyNo(Integer.parseInt(isModify) / 10);
					arti.setA_content(content);
					if (!(uploadFileName.length() < 3))
						arti.setFigure(uploadFileName);
					arti.setLastchange(new Date());
					arti.setTitle(title);
					arti.setUser(ui);
					TypeInfoDAOImpl tidi = new TypeInfoDAOImpl();
					arti.setType(tidi.getTypeInfo(typeno));
					if (isModify.equals("0"))
						arti.setArticleno(adi.uploadArti(arti));
					else {
						arti.setArticleno(Integer.parseInt(isModify) / 10);
						adi.modifyArti(arti);
					}
					response.sendRedirect("ArticleServlet?func=checkArticle&ano="+arti.getArticleno());
				}
			}
		}
	}

	/**
	 * 获得文章类型列表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getTypes(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		List<TypeInfo> tl = adi.getTypes();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (TypeInfo t : tl) {
			sb.append("{\"value\":\"" + t.getTypeno() + "\",\"name\":\""
					+ t.getTypename() + "\"},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		out.print(sb);
		out.close();
	}

	/**
	 * 查看文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void checkArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int artino = Integer.parseInt(request.getParameter("ano"));

		Articles arti = adi.getArticlebyNo(artino);
		UserInfo ui = (UserInfo) request.getSession().getAttribute("user");
		if (ui != null && arti != null)
			new UserDAOImpl().readRecord(ui, arti);

		request.setAttribute("arti", arti);
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

	/**
	 * 分页浏览文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void checkAllArticles(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PageBean pb = new PageBean();

		String pageNum = request.getParameter("currentpage");
		if (pageNum != null) {
			pb.setCurrentPage(Integer.parseInt(pageNum));
		} else {
			pb.setCurrentPage(1);
		}

		String queryString = request.getParameter("queryStr");
		pb.setQueryString(queryString);
		System.out.println("queryString" + queryString);
		ArticleLimit al = null;

		if (queryString != null && !"".equals(queryString)) {
			al = new ArticleLimit();
			al.setLimit(queryString);
		}
		List<Articles> artiList = adi.getArticleList(al, pb);

		// 设置PageBean的总页数和总记录数
		int size = adi.getArticleNum(al);
		int perNum = pb.getPerPageRows();
		pb.setTotalSize(size);
		pb.setTotalPage(size % perNum == 0 ? size / perNum : size / perNum + 1);

		request.setAttribute("pageBean", pb);
		request.setAttribute("articles", artiList);
		request.setAttribute("pageTitle", "Articles");
		request.getRequestDispatcher("articles.jsp").forward(request, response);

	}

	@Override
	public void init() throws ServletException {
		super.init();
		adi = new ArticleDAOImpl();
	}

}
