package eBao.dao;

import eBao.po.TypeInfo;

public interface TypeInfoDAO {

	/**
	 * 根据类别编号获得分类的属性
	 */
	TypeInfo getTypeInfo(String typeno);

	/**
	 * 根据分类名称得到分类实体
	 */
	TypeInfo getTypeInfoByName(String limit);
}
