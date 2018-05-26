package eBao.po;

import java.util.List;

public class TypeInfo {
	private String typeno;//类型编号
	private String typename;//类型名
	private List<Articles> articles;//此类型的文章
	
	public String getTypeno() {
		return typeno;
	}
	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public List<Articles> getArticles() {
		return articles;
	}
	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}

}
