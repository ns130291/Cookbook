/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ns130291
 */
public class controller extends HttpServlet {
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
