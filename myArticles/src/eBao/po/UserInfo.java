package eBao.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5272044205095377207L;
	private String userid; //用户名
	private String userpwd; //密码
	private String nickname; //昵称
	private List<Articles> articles =new ArrayList<Articles>();
	private List<Articles> favoriteArtis=new ArrayList<Articles>();
	
	public UserInfo(){};
	public UserInfo(String userid, String userpwd, String nickname) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.nickname = nickname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<Articles> getArticles() {
		return articles;
	}
	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	public List<Articles> getFavoriteArtis() {
		return favoriteArtis;
	}
	public void setFavoriteArtis(List<Articles> favoriteArtis) {
		this.favoriteArtis = favoriteArtis;
	}
	
}
