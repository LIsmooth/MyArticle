package eBao.dao;

import java.util.List;

import eBao.bean.PageBean;
import eBao.po.ArticleLimit;
import eBao.po.Articles;

public class Test {

	public static void main(String[] args) {
//		UserInfo ui=new UserInfo();
//		ui.setUserid("yudi_liu");
//		ui.setUserpwd("123");
//		UserDAOImpl udi=new UserDAOImpl();
//		UserInfo u=udi.userLogin(ui);
//		System.out.println(u==null?"wrong!":u.getNickname());
//		for(Articles a:u.getArticles()){
//			System.out.println(a.getTitle());
//		}
		
//		UserDAOImpl udi=new UserDAOImpl();
//		UserInfo u=udi.getUserInfo("yudi_liu");
//		System.out.println(u.getNickname());
		
		ArticleDAOImpl adi=new ArticleDAOImpl();
		ArticleLimit al=new ArticleLimit();
		al.setTitle("习近");
//		al.setTypeno("2");
//		List<Articles> artiList=adi.getArticleList(al);
//		for(Articles arti:artiList){
//			System.out.println(arti.getTitle());
//		}
//		Articles arti=adi.getArticlebyNo(4);
//		System.out.println(arti.getTitle());
		
//		Articles arti=new Articles();
//		arti.setTitle("NBA常规赛：骑士不敌老鹰");
//		int artino=adi.uploadArti(arti);
//		System.out.println(artino);
		
//		Articles arti=adi.getArticlebyNo(4);
//		System.out.println(arti.getTitle());
//		arti.setTitle("CBA");
//		adi.modifyArti(arti);
//		System.out.println();
		PageBean pb=new PageBean();
		pb.setCurrentPage(0);
		
		List<Articles> artiList=adi.getArticleList(al,pb);
		for(Articles a:artiList){
			System.out.println(a.getTitle());
		}
	}

}
