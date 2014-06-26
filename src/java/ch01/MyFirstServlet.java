/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonasrla
 */
@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})
public class MyFirstServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        Enumeration<String> list = request.getHeaderNames();
        String message = request.getHeader("referer");
//        String message = request.getContextPath();
//        String message = "";
//        while (list.hasMoreElements()){
//            message += list.nextElement()+"\n";
//        }
        if ("http://localhost:8080/DemoJSP/".equals(request.getHeader("referer"))){
            try {
                AccountManager am = new AccountManager();
                am.RegisterAccount(request.getParameter("user"), request.getParameter("pass"));
                ServletContext app = this.getServletContext();
                request.setAttribute("message",  message);
                RequestDispatcher rd = app.getRequestDispatcher("/sucesso.jsp"); 
                rd.forward(request, response); 
            }
            catch(IOException e){
                request.setAttribute("error", "Erro na Criacao do arquivo");
                request.setAttribute("message", message);
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/falha.jsp"); 
                rd.forward(request, response); 
            }
            catch(AccountLockedException e){
                request.setAttribute("error", "Conta já existe");
                request.setAttribute("message", message);
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/falha.jsp"); 
                rd.forward(request, response); 
            }
        }
        else{
            try {
                AccountManager am = new AccountManager();
                am.checkAccount(request.getParameter("user"), request.getParameter("pass"));
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/sucesso.jsp"); 
                rd.forward(request, response); 
            }
            catch(IOException e){
                request.setAttribute("error", "Erro na Criacao do arquivo"); 
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/falha.jsp"); 
                rd.forward(request, response); 
            }
            catch(AccountNotFoundException e){
                request.setAttribute("error", "Conta nao existe");
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/falha.jsp"); 
                rd.forward(request, response); 
            }
            catch(CredentialNotFoundException e){
                request.setAttribute("error", "Senha errada");
                ServletContext app = this.getServletContext(); 
                RequestDispatcher rd = app.getRequestDispatcher("/falha.jsp"); 
                rd.forward(request, response); 
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Got Getted");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Got Posted");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
