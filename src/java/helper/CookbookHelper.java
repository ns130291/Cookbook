/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import hibernate.HibernateUtil;
import java.util.List;
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

    public List<Receipt> getAllReceipts() {
        List<Receipt> receipts = (List<Receipt>) queryDB("from Receipt as r");
        return receipts;
    }
    
    private List queryDB(String queryString) {
        List qList = null;
        try {
            Transaction trans = session.beginTransaction();
            Query dbQuery = session.createQuery(queryString);
            qList = (List) dbQuery.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return qList;
    }
}
