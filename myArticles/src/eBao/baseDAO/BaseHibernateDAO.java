package eBao.baseDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseHibernateDAO {

	static SessionFactory sf = null;

	static {

		Configuration conf = new Configuration().configure();
		sf = conf.buildSessionFactory();
	}

	public Session getSession() {

		return sf.openSession();

	}
}
