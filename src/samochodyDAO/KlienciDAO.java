package samochodyDAO;

import cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import samochodydb.klienci;
import java.util.*;

public class KlienciDAO {

    public void saveKlient(klienci klient){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(klient);
            session.flush();
            transaction.commit();
        } catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }

    public List<klienci> getAllKlients(){
            Session session =HibernateUtil.getSessionFactory().openSession();
            List Klient = (List<klienci>) session.createQuery("from klienci ").list();
            return  Klient;
    }

    public void deleteKlient(int id){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM klienci WHERE id_klienta = " + id);
            q.executeUpdate();
            transaction.commit();
        } catch (Exception e){
        }
    }
}
