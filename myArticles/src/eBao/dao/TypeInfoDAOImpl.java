package eBao.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import eBao.po.TypeInfo;
import eBao.util.HibernateUtil;

public class TypeInfoDAOImpl implements TypeInfoDAO {

	/**
	 * 根据类别编号获得分类的属性
	 */
	@Override
	public TypeInfo getTypeInfo(String typeno) {
		if(typeno==null)
			return null;
		Session session=HibernateUtil.currentSession();
		TypeInfo type=(TypeInfo) session.get(TypeInfo.class,typeno);
		return type;
	}

	/**
	 * 根据分类名称得到分类实体
	 */
	@Override
	public TypeInfo getTypeInfoByName(String limit) {

		if(limit==null)
			return null;
		Session session=HibernateUtil.currentSession();
		String hql="from TypeInfo where typename=?";
		Query query=session.createQuery(hql);
		query.setParameter(0, limit);
		TypeInfo type=(TypeInfo) query.uniqueResult();
		return type;
	}

}
