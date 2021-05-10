/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserServices.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H-URBINA-M
 */
@WebServlet(name = "VerificaUsuarioServlet", urlPatterns = {"/VerificaUsuario"})
public class VerificaUsuarioServlet extends HttpServlet {

    private ServletConfig config;
    private ServletContext context;
    private HttpSession session;
    private Connection objConnection;

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
        config = this.getServletConfig();
        context = config.getServletContext();
        session = request.getSession();
        objConnection = (Connection) context.getAttribute("objConnection");
        response.setContentType("text/html;charset=ISO-8859-1");
        String accion = request.getParameter("accion");
        String result = null;
        String target = null;
        java.util.Date fechaSistema = new java.util.Date();
        DateFormat df = new SimpleDateFormat("yyyy");
        String periodo = df.format(fechaSistema);
        switch (accion) {
            case "LOGIN":
                String usuario = request.getParameter("usuario");
                String password = request.getParameter("password");
                
                result = "Login/Principal.jsp";
                break;
            default:
                request.getSession().removeAttribute("ID");
                request.getSession().removeAttribute("objUsuario" + session.getId());
                request.getSession().removeAttribute("autorizacion");
                request.getSession().invalidate();
                target = "index.jsp";
                break;
        }
        if (result != null) {
            response.setContentType("text/html;charset=ISO-8859-1");
            try (PrintWriter out = response.getWriter()) {
                out.print(result);
            }
        }
        if (target != null) {
            response.sendRedirect(target);
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
