package com.xledbd.dao;

import com.xledbd.clients.Client;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ClientDAO {

	private static ClientDAO clientDAO;
	private static SessionFactory factory;

	private ClientDAO() {}

	public static ClientDAO getInstance() {
		if (clientDAO == null) {
			clientDAO = new ClientDAO();
			factory = SessionFactorySingleton.getInstance();
		}
		return clientDAO;
	}

	public List<Client> getList() {
		List<Client> list = null;
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			Query<Client> query =
					session.createQuery("from Client u order by u.id", Client.class);
			list = query.getResultList();

			session.getTransaction().commit();
		} catch (JDBCException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public int create(Client object) {
		int generatedId = -1;
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			generatedId = (Integer)session.save(object);
			session.getTransaction().commit();
		} catch (JDBCException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return generatedId;
	}

	public void save(Client object) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (JDBCException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Client get(int id) {
		Session session = factory.getCurrentSession();
		Client client = null;
		try {
			session.beginTransaction();
			client = session.get(Client.class, id);
			session.getTransaction().commit();
		} catch (JDBCException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return client;
	}

	public void delete(int id) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery("delete from Client where id=:userId");
			query.setParameter("userId", id);
			query.executeUpdate();

			session.getTransaction().commit();
		} catch (JDBCException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
