/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author ns130291
 */
public class CookbookHelper {
    Session session = null;
    
    public CookbookHelper(){
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
}
