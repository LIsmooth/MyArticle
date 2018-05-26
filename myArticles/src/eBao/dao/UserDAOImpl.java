package eBao.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import eBao.baseDAO.BaseDAO;
import eBao.baseDAO.BaseHibernateDAO;
import eBao.po.Articles;
import eBao.po.UserInfo;
import eBao.util.HibernateUtil;

public class UserDAOImpl extends BaseDAO implements UserDAO {
	BaseHibernateDAO bd = new BaseHibernateDAO();

	/**
	 * * 根据用户的用户名获得用户实体
	 */
	@Override
	public UserInfo getUserInfo(String userid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();

		UserInfo ui = (UserInfo) session.get(UserInfo.class, userid);
		return ui;
	}

	/**
	 * 用户登录，根据用户名账号密码返回用户实体
	 */
	@Override
	public UserInfo userLogin(String userid, String pwd) {
		//
		// Session session=bd.getSession();
		// Transaction ts=session.beginTransaction();
		Session session = HibernateUtil.currentSession();

		String hql = "from UserInfo as s where s.userid=? and s.userpwd=?";
		Query query = session.createQuery(hql);
		query.setString(0, userid);
		query.setString(1, pwd);

		@SuppressWarnings("unchecked")
		List<UserInfo> ul = query.list();

		// ts.commit();

		if (ul.size() != 0)
			return ul.get(0);
		else
			return null;
	}

	/**
	 * 根据用户名获得收藏列表
	 */
	@Override
	public List<Articles> getFavoriteArtis(String userid) {
		UserInfo ui = getUserInfo(userid);
		return ui.getFavoriteArtis();
	}

	/**
	 * 根据用户名获得用户自己的文章列表
	 */
	@Override
	public List<Articles> getOwnArtis(String userid) {
		UserInfo ui = getUserInfo(userid);
		return ui.getArticles();
	}

	/**
	 * 判断用户是否已经收藏这篇文章
	 */
	@Override
	public boolean isFavorite(String ano, String userid) {
		Session session = HibernateUtil.currentSession();
		String sql = "select count(userid) from favorites where userid=? and articleno=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0, userid);
		query.setParameter(1, ano);
		BigDecimal i = (BigDecimal) query.uniqueResult();
		return (i.intValue() > 0);
	}

	/**
	 * 添加文章到收藏列表
	 */
	@Override
	public void addFavorite(String ano, String userid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		UserInfo ui = getUserInfo(userid);
		ArticleDAOImpl adi = new ArticleDAOImpl();
		Articles arti = adi.getArticlebyNo(Integer.parseInt(ano));
		ui.getFavoriteArtis().add(arti);
		System.out.println(ui.getFavoriteArtis().size());
		session.merge(ui);
	}

	/**
	 * 删除文章
	 */
	@Override
	public void deleteArticle(UserInfo u, String ano) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		ArticleDAOImpl adi = new ArticleDAOImpl();
		Articles arti = adi.getArticlebyNo(Integer.parseInt(ano));
		arti.setIsDeleted(1);
		session.update(arti);
	}

	/**
	 * 用户注册
	 */
	@Override
	public int userRegister(UserInfo u) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		String uid = (String) session.save(u);
		System.out.println(uid);
		return 1;
	}

	/**
	 * 判断用户名是否被占用
	 */
	@Override
	public boolean isExist(String uid) {
		// TODO Auto-generated method stub
		if (getUserInfo(uid) != null)
			return true;
		else
			return false;
	}

	/**
	 * 记录阅读历史
	 * 
	 * @param ui
	 * @param arti
	 */
	@Override
	public void readRecord(UserInfo ui, Articles arti) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		String sql = "{call pro_readrecord (?,?,?)}";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0, ui.getUserid());
		query.setParameter(1, arti.getArticleno());
		query.setParameter(2, arti.getType().getTypeno());
		query.executeUpdate();

	}

	/**
	 * 获得阅读历史记录
	 * 
	 * @param ui
	 * @return
	 */
	@Override
	public List<Articles> getReadRecord(UserInfo ui) {
		Session session = HibernateUtil.currentSession();
		String sql="select a.* from articles a,(select articleno ano,readdate t from readrecord where userid=?) r where a.isdeleted=0 and a.articleno=r.ano order by t desc";
		SQLQuery query=session.createSQLQuery(sql).addEntity(Articles.class);
		query.setParameter(0, ui.getUserid());
		@SuppressWarnings("unchecked")
		List<Articles> artiList=query.list();
		return artiList;
	}

	/**
	 * 猜你喜欢
	 * @param uid
	 * @return
	 */
	@Override
	public List<Articles> getGuessLike(String uid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.currentSession();
		String sql = "select typeno from(select typeno from readrecord where userid=? order by readdate desc)where rownum=1";
		SQLQuery query = session.createSQLQuery(sql).addScalar("typeno",Hibernate.STRING);
		query.setParameter(0, uid);
		String tno=(String) query.uniqueResult();
		String sql2="select * from articles where isdeleted=0 and typeno=:tno and articleno not in(select articleno from readrecord where userid=:uid and typeno=:tno) and rownum<9";
		query=session.createSQLQuery(sql2).addEntity(Articles.class);
		query.setParameter("tno",tno);
		query.setParameter("uid", uid);
		
		@SuppressWarnings("unchecked")
		List<Articles> artiList=query.list();
		
		return artiList;
	}

}
