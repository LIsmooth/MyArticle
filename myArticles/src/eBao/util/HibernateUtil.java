package eBao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
		//ThreadLocal保证线程独立性
		private static final ThreadLocal<Session> sessionTL = new ThreadLocal<Session>(); 
		private static Configuration configuration;
		private final static SessionFactory sessionFactory;
		
		static {
			try {
				configuration = new Configuration().configure();
				sessionFactory = configuration.buildSessionFactory();
			} catch (Throwable ex) {
				ex.printStackTrace();
				throw new ExceptionInInitializerError(ex);
			}
		}		
		/**
		 * 获取Session
		 */
		public static Session currentSession() {
			Session session = (Session) sessionTL.get(); 
			if (session == null) { 
				session = sessionFactory.openSession(); 
				sessionTL.set(session); 
			}
			return session; 
		}		
		/**
		 * 关闭session
		 */
		public static void closeSession(){
			Session session = (Session) sessionTL.get(); 
			sessionTL.set(null);
			session.close();
		}

}
