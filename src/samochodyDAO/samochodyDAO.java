package samochodyDAO;

import cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import samochodydb.samochody;

import java.util.List;

public class samochodyDAO {
    public void saveSamochod(samochody sam) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(sam);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<samochody> getAllSamochody() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List Sam = (List<samochody>) session.createQuery("from samochody ").list();
        return Sam;
    }

    public void deleteSamochod (int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM samochody WHERE id_samochodu = " + id);
            q.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
        }
    }
}


