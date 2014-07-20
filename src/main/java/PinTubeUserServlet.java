/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author chad
 */
@WebServlet(urlPatterns = {"/PinTubeUserServlet"})
public class PinTubeUserServlet extends HttpServlet {

    private UserController userController;
    private static Database database;
    
    public PinTubeUserServlet() {
        userController = new UserController();
        if (database == null) {
            database = new Database();
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
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
        
        String user = (String)request.getSession().getAttribute("user");
        System.out.println("USER==="+user);
        if (user == null) {
            user = "brady";
            System.out.println("Resetting user to default\n\tUSER==="+user);
        }
        
        database.connect();
        List<Video> results = database.getUserVideos(user);
        database.disconnect();
        
        Writer out = response.getWriter();
        
        if(results.size() > 0) {
        out.write("<div class='row'>");
        for(Video video : results) {
        	out.write("<div class='col-xs-6 col-md-4'>");
        	out.write("<div class='thumbnail' style='text-align:center'>");
        	out.write("<h3>" + video.getTitle() + "</h3>");
        	out.write("<div class='embed-responsive embed-responsive-16by9'>");
            out.write("<iframe class='embed-responsive-item' ");
            out.write("src='//www.youtube.com/embed/");
            out.write(video.getLink());
            out.write("' allowfullscreen></iframe>");
            out.write("</div>");
            out.write("</div>");
            out.write("</div>");
        }
        out.write("</div>");
        }
        else
        	out.write("<h3>No Videos Pinned</h3>");
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
    	System.out.println("Servlet: " + getServletContext().getRealPath("x"));
        PrintWriter out = response.getWriter();
        out.println("<h1>"+request.getMethod()+"</h1>");
        // log in
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(userController.validLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("home.jsp");
        }     
        else {
            String message = "Username or password is incorrect";
            request.setAttribute("incorrect", message);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        };
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
