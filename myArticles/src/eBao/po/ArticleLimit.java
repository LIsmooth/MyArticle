package eBao.po;

import eBao.dao.TypeInfoDAOImpl;

public class ArticleLimit {
	private int articleno;
	private String username;
	private String title;
	private String typeno;
	private String limit="";
	private String criteria="";
	
	public ArticleLimit(){
		criteria+=" 1=1";
		limit+="1=1";
	}
	public ArticleLimit(String indexType){
		
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
		limit+=" or articleno = "+articleno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		limit+=" and username = '"+username+"'";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		limit+=" and title like '%"+title+"%'";
	}
	public String getTypeno() {
		return typeno;
	}
	public void setTypeno(String typeno) {
		this.typeno = typeno;
		
		limit+=" or typeno = "+typeno;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit=limit;
		//判断关键字是不是文章类型
		TypeInfoDAOImpl tidi=new TypeInfoDAOImpl();
		TypeInfo type=tidi.getTypeInfoByName(limit);
		this.criteria = " user.nickname =? or title like ? or typeno =?";
		if(type!=null)
			this.criteria+=" or typeno = '"+type.getTypeno()+"'";
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	
}
