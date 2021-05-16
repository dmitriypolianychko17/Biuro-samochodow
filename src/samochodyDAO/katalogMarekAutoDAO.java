package samochodyDAO;

import cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import samochodydb.katalog_marek_auto;

import java.util.List;

public class katalogMarekAutoDAO {
    public void saveAuto(katalog_marek_auto katalog){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(katalog);
            session.flush();
            transaction.commit();
        } catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }

    public List<katalog_marek_auto> getAllKatalog(){
        Session session =HibernateUtil.getSessionFactory().openSession();
        List Auto = (List<katalog_marek_auto>) session.createQuery("from katalog_marek_auto ").list();
        return  Auto;
    }

    public void deleteKatalog(int id){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM katalog_marek_auto WHERE id_marki = " + id);
            q.executeUpdate();
            transaction.commit();
        } catch (Exception e){
        }
    }
}
