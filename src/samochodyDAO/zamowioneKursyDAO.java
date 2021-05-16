package samochodyDAO;

import cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import samochodydb.zamowione_kursy;
import java.util.List;

public class zamowioneKursyDAO {
    public void saveKurs(zamowione_kursy kurs) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(kurs);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<zamowione_kursy> getAllKourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List Kurs = (List<zamowione_kursy>) session.createQuery("from zamowione_kursy").list();
        return Kurs;
    }

    public void deleteKurs(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM zamowione_kursy WHERE numer_inwentarzowy = " + id);
            q.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
        }
    }
}

