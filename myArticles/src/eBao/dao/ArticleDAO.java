package eBao.dao;

import java.util.List;

import eBao.bean.PageBean;
import eBao.po.Articles;
import eBao.po.ArticleLimit;
import eBao.po.TypeInfo;

public interface ArticleDAO {
	/**
	 * 根据ArticleLimit和PageBean获得文章列表
	 * @param al
	 * @param pb
	 * @return
	 */
	List<Articles> getArticleList(ArticleLimit al,PageBean pb);
	
	/**
	 * 根据ArticleLimit获得文章数量
	 * @param al
	 * @return
	 */
	int getArticleNum(ArticleLimit al);
	
	/**
	 * 根据文章编号获取文章
	 */
	Articles getArticlebyNo(int articleno);
	
	/**
	 * 上传文章
	 * @param arti
	 * @return
	 */
	int uploadArti(Articles arti);
	
	/**
	 * 修改文章
	 * @param arti
	 * @return
	 */
	int modifyArti(Articles arti);
	
	/**
	 * 删除文章
	 * @param arti
	 * @return
	 */
	int deleteArti(Articles arti);

	/**
	 * 获得所有文章类型
	 * @return
	 */
	List<TypeInfo> getTypes();
	
}
