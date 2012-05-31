/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.CookbookHelper;
import java.io.DataInputStream;
import java.io.FileOutputStream;
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
import model.Equipment;
import model.IngredientTbl;
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
            //neues Rezept anlegen
            session.removeAttribute("pic");
            forward(request, response, "/WEB-INF/pages/addrecipe.jsp");
        } else if (request.getParameter("iframe") != null) {
            //dateiupload-form
            if (request.getParameter("iframe").equals("upload")) {
                forward(request, response, "/WEB-INF/pages/iupload.jsp");
            }
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
        //Session wird auf vorhandene Rezept-Liste geprüft, falls nicht vorhanden wird sie geladen und in der Session gespeichert
        if (session.getAttribute("rezepte") == null) {
            session.setAttribute("rezepte", helper.getAllReceipts());
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
        } else if (request.getParameter("removeFromCookbook") != null) {
            //Rezept zu Kochbuch hinzufügen
            System.out.println("löschen");
            try {
                int id = Integer.parseInt(request.getParameter("removeFromCookbook"));
                ArrayList<Receipt> kochbuch = (ArrayList<Receipt>) session.getAttribute("kochbuch");
                Iterator<Receipt> iter = kochbuch.listIterator();
                Receipt r = null;
                while (iter.hasNext()) {
                    r = iter.next();
                    if (r.getId() != id) {
                        r = null;
                    }
                }
                kochbuch.remove(r);
            } catch (Exception ex) {
                System.out.println("Exception");
            }
        } else if (request.getParameter("removeAllFromCookbook") != null) {
            //Rezept zu Kochbuch hinzufügen
            System.out.println("löschenAlle");
            session.setAttribute("kochbuch", new ArrayList<Receipt>(0));
        } else if (request.getParameter("uploadpicture") != null) {
            //Datei-Upload
            //1. Überprüfung des Datenstroms auf seine Verschlüsselungs-methode
            String contentType = request.getContentType();
            if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
                //2. Einlesen des gesendeten Datenstroms 
                DataInputStream in = new DataInputStream(request.getInputStream());
                int formDataLength = request.getContentLength();
                byte dataBytes[] = new byte[formDataLength];
                int byteRead = 0;
                int totalBytesRead = 0;
                while (totalBytesRead < formDataLength) {
                    byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                    totalBytesRead += byteRead;
                }
                String file = new String(dataBytes);

                int index = file.indexOf("filename=");
                int indexFileNameStart = file.indexOf("\"", index) + 1;
                int indexFileNameEnd = file.indexOf("\"", indexFileNameStart);
                String saveFileName = file.substring(indexFileNameStart, indexFileNameEnd);
                String[] extension = saveFileName.split("\\.");
                //zufallsname generieren
                int r = (int) (Math.random() * (99999 - 10000) + 10000);
                saveFileName = r + "." + extension[1];

                index = file.indexOf("boundary");
                index = contentType.indexOf("=", index + 1);

                //boundary ist die Zeichenkette, mit der die einzelnen Einträge (Parts) 
                //in der "Multipart-Form" voneinander getrennt werden

                String boundary = contentType.substring(index + 1, contentType.length());


                //Sicherstellung, dass an dieser Stelle die Inhalte der Bilddatei stehen
                int indexStart = file.indexOf("Content-Type", indexFileNameEnd);
                indexStart = file.indexOf("image", indexStart + 1);
                //vor den Binärdaten eines Bildes kommt erst eine Leerzeile 
                indexStart = file.indexOf("\n", indexStart + 1);
                indexStart = file.indexOf("\n", indexStart + 1);
                indexStart++;

                //3. Separierung der Eingabeelemente über die Begrenzungszeile 
                int boundaryLocation = file.indexOf(boundary, indexStart);

                int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length - 4;

                session.setAttribute("pic", saveFileName);
                saveFileName = "C:\\Users\\ns130291\\Documents\\NetBeansProjects\\Cookbook\\web\\img\\" + saveFileName;
                //4. Ausgabe auf den Bestimmungsort auf dem Server
                FileOutputStream fileOut = new FileOutputStream(saveFileName);
                fileOut.write(dataBytes, indexStart, (endPos - indexStart));
                fileOut.flush();
                fileOut.close();

                forward(request, response, "/WEB-INF/pages/iuploaddone.jsp");
            }
        } else if (request.getParameter("addrecipe") != null) {
            Receipt r = new Receipt(request.getParameter("description"), request.getParameter("title"));
            if (request.getParameter("author") != null && !request.getParameter("author").equals("")) {
                r.setAuthor(request.getParameter("author"));
            }
            if (request.getParameter("degree") != null && !request.getParameter("degree").equals("")) {
                r.setDegree(Integer.parseInt(request.getParameter("degree")));
            }
            if (request.getParameter("duration") != null && !request.getParameter("duration").equals("")) {
                r.setDuration(Integer.parseInt(request.getParameter("duration")));
            }
            if (request.getParameter("note") != null && !request.getParameter("note").equals("")) {
                r.setNote(request.getParameter("note"));
            }
            if (session.getAttribute("pic") != null) {
                r.setPicture((String) session.getAttribute("pic"));
                session.removeAttribute("pic");
            }

            helper.addReceipt(r);

            if (request.getParameter("equipment") != null) {
                System.out.println(request.getParameter("equipment"));
                String[] stemp = request.getParameter("equipment").split(";");
                for (int i = 0; i < stemp.length; i++) {
                    Equipment etemp = new Equipment(r, stemp[i]);
                    r.addEquipment(etemp);
                    helper.addEquipment(etemp);
                }
            }

            if (request.getParameter("ingredient") != null) {
                System.out.println(request.getParameter("ingredient"));
                String[] stemp = request.getParameter("ingredient").split(";");
                for (int i = 0; i < stemp.length; i++) {
                    if (!stemp[i].equals("")) {
                        String[] stemp2 = stemp[i].split(":");
                        IngredientTbl itemp = new IngredientTbl(r, stemp2[0], stemp2[1], stemp2[2]);
                        r.addIngredient(itemp);
                        helper.addIngredient(itemp);
                    }
                }
            }

            List<Receipt> l = (List<Receipt>) session.getAttribute("rezepte");
            l.add(r);
            session.setAttribute("rezepte", l);

            System.out.println(r.toString());

            forward(request, response, "/WEB-INF/pages/overview.jsp");
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
