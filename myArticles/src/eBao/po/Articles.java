package eBao.po;

import java.io.Serializable;
import java.util.Date;


public class Articles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5785282004006837354L;
	
	private int articleno;//文章编号
	private UserInfo user;//作者
	private String title;//文章标题
	private TypeInfo type;//文章类型
	private String figure;//配图
	private String a_content;//文章内容
	private Date lastchange;//上次修改时间
	private int modifytimes;//修改次数
	private int isDeleted;//是否删除
	
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TypeInfo getType() {
		return type;
	}
	public void setType(TypeInfo type) {
		this.type = type;
	}
	public String getFigure() {
		return figure;
	}
	public void setFigure(String figure) {
		this.figure = figure;
	}
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		a_content= a_content.replace("<","&lt;");
		a_content=a_content.replace(">", "&gt;");
		this.a_content=a_content;
	}
	public Date getLastchange() {
		return lastchange;
	}
	public void setLastchange(Date lastchange) {
		this.lastchange = lastchange;
	}
	public int getModifytimes() {
		return modifytimes;
	}
	public void setModifytimes(int modifytimes) {
		this.modifytimes = modifytimes;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Articles)){  
            return false;  
        } else {
        	Articles arti=(Articles)obj;
			return this.articleno==arti.articleno;
        }
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	
}