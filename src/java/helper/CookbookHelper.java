/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import hibernate.HibernateUtil;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import model.Equipment;
import model.IngredientTbl;
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
    
    /**
     * Fügt ein Rezept der Datenbank hinzu
     * @param newR 
     */
    public void addReceipt(Receipt newR) {
        session.close();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Class.forName("com.mysql.jdbc.Driver");//wird gebraucht damit der MySQL-Datenbanktreiber gefunden wird, ist ein sehr häufiger Fehler, Erklärungen gibts dafür keine...

            Transaction trans = session.beginTransaction();
            session.save(newR);
            trans.commit();
        } catch (ClassNotFoundException ex) {
        }
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void addIngredient(IngredientTbl newI) {
        session.close();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Class.forName("com.mysql.jdbc.Driver");//wird gebraucht damit der MySQL-Datenbanktreiber gefunden wird, ist ein sehr häufiger Fehler, Erklärungen gibts dafür keine...

            Transaction trans = session.beginTransaction();
            session.save(newI);
            trans.commit();
        } catch (ClassNotFoundException ex) {
        }
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void addEquipment(Equipment newE) {
        session.close();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            Class.forName("com.mysql.jdbc.Driver");//wird gebraucht damit der MySQL-Datenbanktreiber gefunden wird, ist ein sehr häufiger Fehler, Erklärungen gibts dafür keine...

            Transaction trans = session.beginTransaction();
            session.save(newE);
            trans.commit();
        } catch (ClassNotFoundException ex) {
        }
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Receipt> getAllReceipts() {
        List<Receipt> receipts = (List<Receipt>) queryDB("from Receipt as r");
        System.out.println(receipts.toString());
        return receipts;
    }

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
     * Gibt ein Receipt-Objekt zu einer id zurück
     * @param session
     * @param id
     * @return 
     */
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
