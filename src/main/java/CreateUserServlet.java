

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chad
 */
@WebServlet(urlPatterns = {"/CreateUserServlet"})
public class CreateUserServlet extends HttpServlet {

    private static UserController userController; // should be a singleton
    
    public CreateUserServlet() {
        if(userController == null) {
            userController = new UserController();
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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
        // make sure the passwords are the same
        String pass = request.getParameter("pass");
        String pass2 = request.getParameter("pass2");
        String user = request.getParameter("user");
        if (!pass.equals(pass2)) {
            String message = "Your passwords do not match";
            System.out.println(message);
            request.setAttribute("incorrect", message);
            request.getRequestDispatcher("CreateUser.jsp").forward(request, response);
            return;
        }
        else if (userController.userExists(user)) {
            String message = "Username is already in use";
            System.out.println(message);
            request.setAttribute("incorrect", message);
            request.getRequestDispatcher("CreateUser.jsp").forward(request, response);
            return;
        }     
        else if(userController.addUser(user, pass)) {
        	response.sendRedirect("index.jsp");
        }
        else {
        	   String message = "Error adding user";
               System.out.println(message);
               request.setAttribute("incorrect", message);
               request.getRequestDispatcher("CreateUser.jsp").forward(request, response);
               return;
        }
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
