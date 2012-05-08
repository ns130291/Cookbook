/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.CookbookHelper;
import hibernate.HibernateUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipment;
import model.IngredientTbl;
import model.Receipt;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author ns130291
 */
public class controller extends HttpServlet {

    /**
     * Die Funktion doGet behandelt alle Seitenaufrufe per GET-Methode, zeigt
     * bei gesetztem print-Parameter die Kochbuch Drucken Seite an
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /*org.hibernate.Session session2 = null;
        session2 = HibernateUtil.getSessionFactory().getCurrentSession();

        Receipt grillers = new Receipt("laaaber", "Grillers");
        IngredientTbl fleisch = new IngredientTbl(grillers, "Fleisch");
        Equipment messer = new Equipment(grillers, "Messer2");

        try {
            Transaction trans = session2.beginTransaction();
            session2.save(grillers);
            session2.save(fleisch);
            session2.save(messer);
            trans.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }*/
        
        
        System.out.println("Controller wird aufgerufen");
        //http-Session wird geladen, falls nicht vorhanden wird eine neue anglegt
        HttpSession session = request.getSession();
        //Session wird auf vorhandene Rezept-Liste geprüft, falls nicht vorhanden wird sie geladen und in der Session gespeichert
        if (session.getAttribute("rezepte") == null) {
            //Session wird auf CookbookHelper überprüft und falls vorhanden aus der Session geladen, ansonsten wird ein neuer erzeugt und in der Session gespeichert
            CookbookHelper helper;
            if (session.getAttribute("helper") != null) {
                helper = (CookbookHelper) session.getAttribute("helper");
            } else {
                helper = new CookbookHelper();
                session.setAttribute("helper", helper);
            }
            session.setAttribute("rezepte", helper.getAllReceipts());
        }
        
        //Überprüfung auf übergegebene Parameter
        if (request.getParameterMap().isEmpty()) {
            //kein Parameter, Rezept-Übersicht wird angezeigt
            forward(request, response, "/WEB-INF/pages/overview.jsp");
        } else if (request.getParameter("print") != null) {
            //Rezept drucken
            if (request.getParameter("print").equals("true")) {
                forward(request, response, "/WEB-INF/pages/print.jsp");
            }
        } else if (false) {
            //noch übrig
        }
        //falscher Parameter übergeben, Rezept-Übersicht wird angezeigt
        forward(request, response, "/WEB-INF/pages/overview.jsp");

    }

    /**
     * Die Funktion forward bearbeitet alle forwards, spart etwas Schreibarbeit
     *
     * @param request
     * @param response
     * @param url
     * @throws ServletException
     * @throws IOException
     */
    private void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //rezept drucken
        if (request.getParameter("print") != null) {
            if (request.getParameter("print").equals("true")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("printurl");
                dispatcher.forward(request, response);
            }
        } else if (false) {
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorurl");
            dispatcher.forward(request, response);
        }

    }
}
