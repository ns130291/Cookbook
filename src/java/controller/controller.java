/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.CookbookHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Receipt;

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

        System.out.println("Controller wird aufgerufen");
        //http-Session wird geladen, falls nicht vorhanden wird eine neue anglegt
        HttpSession session = request.getSession();
        //Session wird auf CookbookHelper überprüft und falls vorhanden aus der Session geladen, ansonsten wird ein neuer erzeugt und in der Session gespeichert
        CookbookHelper helper;
        if (session.getAttribute("helper") != null) {
            helper = (CookbookHelper) session.getAttribute("helper");
        } else {
            helper = new CookbookHelper();
            session.setAttribute("helper", helper);
        }
        //Session wird auf vorhandene Rezept-Liste geprüft, falls nicht vorhanden wird sie geladen und in der Session gespeichert
        if (session.getAttribute("rezepte") == null) {
            session.setAttribute("rezepte", helper.getAllReceipts());
        }
        //Session wird auf Kochbuch des Benutzers überprüft, falls nicht vorhanden wird sie in der Session gespeichert
        if (session.getAttribute("kochbuch") == null) {
            session.setAttribute("kochbuch", new ArrayList<Receipt>(0));
        }

        //Überprüfung auf übergegebene Parameter
        if (request.getParameterMap().isEmpty()) {
            //kein Parameter, Rezept-Übersicht wird angezeigt
            forward(request, response, "/WEB-INF/pages/overview.jsp");
        } else if (request.getParameter("print") != null) {
            //Rezept drucken
            if (request.getParameter("print").equals("true")) {
                forward(request, response, "/WEB-INF/pages/print.jsp");
            } else {
                forward(request, response, "/WEB-INF/pages/overview.jsp");
            }
        } else if (request.getParameter("article") != null) {
            //Artikel laden
            session.setAttribute("rezept", helper.selectReceipt(session, Integer.parseInt(request.getParameter("article"))));
            forward(request, response, "/WEB-INF/pages/recipe.jsp");
        } else if (request.getParameter("newrecipe") != null) {
            forward(request, response, "/WEB-INF/pages/addrecipe.jsp");
        } else {
            //falscher Parameter übergeben, Rezept-Übersicht wird angezeigt
            forward(request, response, "/WEB-INF/pages/overview.jsp");
        }
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
        //http-Session wird geladen, falls nicht vorhanden wird eine neue anglegt
        HttpSession session = request.getSession();
        //Session wird auf Kochbuch des Benutzers überprüft, falls nicht vorhanden wird sie in der Session gespeichert
        if (session.getAttribute("kochbuch") == null) {
            session.setAttribute("kochbuch", new ArrayList<Receipt>(0));
        }
        //Session wird auf CookbookHelper überprüft und falls vorhanden aus der Session geladen, ansonsten wird ein neuer erzeugt und in der Session gespeichert
        CookbookHelper helper;
        if (session.getAttribute("helper") != null) {
            helper = (CookbookHelper) session.getAttribute("helper");
        } else {
            helper = new CookbookHelper();
            session.setAttribute("helper", helper);
        }

        if (request.getParameter("add") != null) {
            //neues Rezept hinzufügen
            Receipt newr = new Receipt(request.getParameter("desc"), request.getParameter("title"));
            helper.addReceipt(newr);
        } else if (request.getParameter("toCookbook") != null) {
            //Rezept zu Kochbuch hinzufügen
            System.out.println("kochbuch hinzufügen anfang");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            try {
                int id = Integer.parseInt(request.getParameter("toCookbook"));
                ArrayList<Receipt> kochbuch = (ArrayList<Receipt>) session.getAttribute("kochbuch");
                Iterator<Receipt> iter = kochbuch.listIterator();
                boolean present = false;
                while (iter.hasNext()) {
                    if (iter.next().getId() == id) {
                        present = true;
                    }
                }
                if (present) {
                    response.setStatus(500);
                    out.println("Rezept schon vorhanden");
                } else {
                    Receipt temp = helper.selectReceipt(session, id);
                    kochbuch.add(temp);
                    session.setAttribute("cookbookitem", temp);
                    forward(request, response, "/WEB-INF/pages/cookbookitem.jsp");
                }
            } catch (Exception ex) {
                response.setStatus(500);
                out.println("Rezept konnte nicht hinzugefügt werden");
            }
        } else {
            /*
             * RequestDispatcher dispatcher =
             * request.getRequestDispatcher("errorurl");
             * dispatcher.forward(request, response);
             */
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(500);
            out.println("Tut net");
        }

    }
}
