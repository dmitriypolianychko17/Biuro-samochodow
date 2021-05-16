package samochodyDAO;

import cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import samochodydb.zamowienia;
import java.util.List;

public class zamowieniaDAO {
    public void saveZamowienie(zamowienia zamowienie){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(zamowienie);
            session.flush();
            transaction.commit();
        } catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
        }
    }

    public List<zamowienia> getAllZamowienia(){
        Session session =HibernateUtil.getSessionFactory().openSession();
        List Zamowienie = (List<zamowienia>) session.createQuery("from zamowienia ").list();
        return Zamowienie;
    }

    public void deleteZamowienie(int id){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM zamowienia WHERE id_zamowienia = " + id);
            q.executeUpdate();
            transaction.commit();
        } catch (Exception e){
        }
    }
}
