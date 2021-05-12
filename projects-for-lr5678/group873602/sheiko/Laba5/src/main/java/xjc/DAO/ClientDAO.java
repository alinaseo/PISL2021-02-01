package xjc.DAO;

import jdk.nashorn.internal.runtime.ECMAException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import xjc.HibernateUtils;
import xjc.generated.Client;

import java.util.List;

public class ClientDAO {

    private static ClientDAO clientDAO;
    private static SessionFactory factory;

    private ClientDAO(){}

    public static ClientDAO getInstance(){
        if (clientDAO == null){
            clientDAO = new ClientDAO();
        }
        return clientDAO;
    }

    private Session s;

    public List<Client> getClientsList() {
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

    public Client getClient(int id) {
        Session s = factory.getCurrentSession();
        Client client = null;
        try {
            s.beginTransaction();
            client = s.get(Client.class, id);
            s.getTransaction().commit();
        } catch (JDBCException e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            s.close();
        }
        return client;
    }


    public void deleteClient(int id) {
            Query query = s.createQuery("delete from Client where id=:userId");
            query.setParameter("userId", id);
            query.executeUpdate();
    }
    public void saveClient(Client c) throws Exception{
        s.saveOrUpdate(c);
    }
    public int createClient(Client c){
            int generatedId = -1;
            Session s = factory.getCurrentSession();
            try {
                s.beginTransaction();
                generatedId = (Integer)s.save(c);
                s.getTransaction().commit();
            } catch (JDBCException e) {
                s.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                s.close();
            }
            return generatedId;
    }



    /*

    public void close(){
        if (s != null){
            s.close();
        }
    }
    void destruct(){
        HibernateUtils.getSessionFactory().close();
    }

    public void rollback() throws Exception{
        s.getTransaction().rollback();
        s.close();
    }
    public void beginTransaction() throws Exception {
        if (s != null){
            throw new Exception("Session already opened!");
        }
        s = HibernateUtils.getSessionFactory().openSession();
        s.beginTransaction();
    }

    public void commit () throws Exception{
        try{
            s.getTransaction().commit();
            s.close();

        } finally{
            try{
                s.close();
            } catch (Exception exc){}
            s = null;
        }
    }
     */

}
