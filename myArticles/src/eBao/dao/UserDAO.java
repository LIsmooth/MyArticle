package eBao.dao;

import java.util.List;

import eBao.po.Articles;
import eBao.po.UserInfo;

public interface UserDAO {
	
	/**
	 * 根据用户id获得用户实体
	 * @param userid
	 * @return
	 */
	UserInfo getUserInfo(String userid);
	
	/**
	 * 用户登录核实账号密码
	 * @param userid
	 * @param pwd
	 * @return
	 */
	UserInfo userLogin(String userid,String pwd);
	
	/**
	 * 获得用户的收藏文章列表
	 * @param userid
	 * @return
	 */
	List<Articles> getFavoriteArtis(String userid);
	
	/**
	 * 获得用户的文章列表
	 * @param userid
	 * @return
	 */
	List<Articles> getOwnArtis(String userid);

	/**
	 * 判断是否已收藏
	 * @param ano
	 * @param userid
	 * @return
	 */
	boolean isFavorite(String ano, String userid);
	
	/**
	 * 添加到收藏列表
	 * @param ano
	 * @param userid
	 */
	void addFavorite(String ano, String userid);

	/**
	 * 删除文章
	 * @param u
	 * @param ano
	 */
	void deleteArticle(UserInfo u, String ano);
	
	/**
	 * 用户注册
	 * @param u
	 */
	int userRegister(UserInfo u);
	
	/**
	 * 判断用户名是否已被占用
	 * @param uid
	 * @return
	 */
	boolean isExist(String uid);
	
	/**
	 * 记录阅读历史
	 * @param ui
	 * @param arti
	 */
	void readRecord(UserInfo ui,Articles arti);
	
	/**
	 * 获得阅读历史记录
	 * @param ui
	 * @return
	 */
	List<Articles> getReadRecord(UserInfo ui);
	
	/**
	 * 猜你喜欢
	 * @param uid
	 * @return
	 */
	List<Articles> getGuessLike(String uid);
}
