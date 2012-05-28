/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import hibernate.HibernateUtil;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import model.Receipt;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ns130291
 */
public class CookbookHelper {

    Session session = null;

    public CookbookHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /*
     * public void startSession(){ session =
     * HibernateUtil.getSessionFactory().getCurrentSession(); }
     */
    /*
     * public void closeSession() { session.close(); session =
     * HibernateUtil.getSessionFactory().getCurrentSession();
    }
     */
    public void addReceipt(Receipt newR) {
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.save(newR);
        //session.close();
        session.flush();
    }

    public List<Receipt> getAllReceipts() {
        List<Receipt> receipts = (List<Receipt>) queryDB("from Receipt as r");
        System.out.println(receipts.toString());
        return receipts;
    }

    /*
     * public Receipt getReceipt(int id) { Receipt receipt = (Receipt)
     * queryDB2("from Receipt receipt0_ where receipt0_.id=" + id);
     * System.out.println(receipt); return receipt; }
     */
    /**
     * Ruft aus der Datenbank Daten nach Anforderung ab, gibt eine Liste zurück
     *
     * @param queryString
     * @return
     */
    private List queryDB(String queryString) {
        List qList = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");//wird gebraucht damit der MySQL-Datenbanktreiber gefunden wird, ist ein sehr häufiger Fehler, Erklärungen gibts dafür keine...
            //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction trans = session.beginTransaction();
            Query dbQuery = session.createQuery(queryString);
            qList = (List) dbQuery.list();
            //trans.commit();
            //session.close();
            session.flush();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ed) {
            ed.printStackTrace();
        }
        return qList;
    }

    /**
     * Ruft aus der Datenbank Daten nach Anforderung ab, gibt eine Liste zurück
     *
     * @param queryString
     * @return
     */
    private Object queryDB2(String queryString) {
        Object qList = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");//wird gebraucht damit der MySQL-Datenbanktreiber gefunden wird, ist ein sehr häufiger Fehler, Erklärungen gibts dafür keine...
            //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction trans = session.beginTransaction();
            Query dbQuery = session.createQuery(queryString);
            qList = dbQuery.uniqueResult();
            //trans.commit();
            //session.close();
            session.flush();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ed) {
            ed.printStackTrace();
        }
        return qList;
    }

    public Receipt selectReceipt(HttpSession session, int id) {
        List<Receipt> receipts = (List<Receipt>) session.getAttribute("rezepte");
        ListIterator<Receipt> iter = receipts.listIterator();

        while (iter.hasNext()) {
            Receipt temp = iter.next();
            if (temp.getId() == id) {
                return temp;
            }
        }
        return null;
    }
}
