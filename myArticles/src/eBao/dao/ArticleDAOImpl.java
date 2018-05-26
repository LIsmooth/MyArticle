package eBao.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import eBao.baseDAO.BaseDAO;
import eBao.bean.PageBean;
import eBao.po.ArticleLimit;
import eBao.po.Articles;
import eBao.po.TypeInfo;
import eBao.util.HibernateUtil;

public class ArticleDAOImpl extends BaseDAO implements ArticleDAO {


	/**
	 * 根据ArticleLimit获得文章列表
	 */
	@Override
	public List<Articles> getArticleList(ArticleLimit al,PageBean pb) {
		Session session = HibernateUtil.currentSession();
		int currentPage=pb.getCurrentPage()-1;
		int perPageRows=pb.getPerPageRows();
		//System.out.println("articleDAO"+al);
		String hql;
		if(al!=null)
			hql ="from Articles where "+al.getCriteria()+" and isdeleted=0 order by lastchange desc";
		else
			hql="from Articles where isdeleted=0 order by lastchange desc";
		System.out.println(hql);
		Query query = session.createQuery(hql);
		if(al!=null){
			System.out.println("limit="+al.getLimit());
		query.setParameter(0, al.getLimit());
		query.setParameter(1, "%"+al.getLimit()+"%");
		query.setParameter(2, al.getLimit());
		}
		query.setFirstResult(currentPage*perPageRows);
		query.setMaxResults(perPageRows);
		
		@SuppressWarnings("unchecked")
		List<Articles> artilist=query.list();
		
		return artilist;

	}

	/**
	 * 根据文章编号获取文章
	 */
	@Override
	public Articles getArticlebyNo(int articleno) {
		Session session=HibernateUtil.currentSession();
		
		Articles arti=(Articles) session.get(Articles.class, articleno);
		return arti;
	}

	/**
	 * 上传文章
	 */
	@Override
	public int uploadArti(Articles arti) {
		Session session=HibernateUtil.currentSession();
		
		Integer artino=(Integer)session.save(arti);
		return artino;
	}

	/**
	 * 修改文章
	 */
	@Override
	public int modifyArti(Articles arti) {
		Session session=HibernateUtil.currentSession();
		
		session.update(arti);
		
		return 1;
	}

	/**
	 * 删除文章
	 */
	@Override
	public int deleteArti(Articles arti) {
		Session session=HibernateUtil.currentSession();
		
		session.delete(arti);
		return 1;
	}
	
	/**
	 * 根据ArticleLimit获得文章数量
	 */
	@Override
	public int getArticleNum(ArticleLimit al) {
		Session session=HibernateUtil.currentSession();
		
		String hql;
		if(al!=null)
			hql ="select count(articleno) from Articles where "+al.getCriteria()+" and isdeleted=0";
		else
			hql="select count(articleno) from Articles where isdeleted=0";
		
		Query query = session.createQuery(hql);
		if(al!=null){
			query.setParameter(0, al.getLimit());
			query.setParameter(1, "%"+al.getLimit()+"%");
			query.setParameter(2, al.getLimit());
			}
		Number num=(Number) query.uniqueResult();
		return num.intValue();
	}

	/**
	 * 获得所有文章类型
	 * @return
	 */
	@Override
	public List<TypeInfo> getTypes() {
		Session session=HibernateUtil.currentSession();
		String hql="from TypeInfo";
		Query query=session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<TypeInfo> tl=query.list();
		return tl;
	}
}
