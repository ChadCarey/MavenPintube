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
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/MontageUserServlet"})
public class MontageUserServlet extends HttpServlet {

    private final UserController userController;
    private static Database database;
    
    public MontageUserServlet() {
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
        
        String user;
        user = request.getSession().getAttribute("user").toString();
        System.out.println("USER==="+user);
        if (user != null) {
            System.out.println(request.getParameter("req"));
            if (request.getParameter("req").equals("getUserVideos"))
            {
                int reelID = Integer.parseInt(request.getParameter("reel"));
                List<Video> results = getUserVideos(user, reelID);
                Writer out = response.getWriter();
                // to-do: instead of iframe, gen thumnail as link to open iframe in new window
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
                   out.write("<h3>No Videos Tagged</h3>");
            }
            else if(request.getParameter("req").equals("getUserReels"))
            { 
                // get the stuff
                List<Reel> results = getUserReels(user);
                Writer out = response.getWriter();

                if(results.size() > 0) {
                   out.write("<div class='row'>");
                   for(Reel reel : results) {
                      out.write("<div class='col-xs-6 col-md-4 reel'>");
                      out.write("<a href='#' onclick='getUserVideos(");
                      out.write(Integer.toString(reel.getID()));
                      out.write(")' class='thumbnail'" +
                                "style='text-align:center; color:black; text-decoration: none;'>");
                      out.write("<h3>" + reel.getTitle() + "</h3>");
                      out.write("<div class='embed-responsive embed-responsive-16by9'>");
                     // out.write("<iframe class='embed-responsive-item' ");
                     // out.write("src='//www.youtube.com/embed/");
                     // out.write(video.getLink());
                     // out.write("' allowfullscreen></iframe>");
                      out.write("</div>");
                      out.write("</a>");
                      out.write("</div>");
                   }
                   out.write("</div>");
                }
                else
                   out.write("<h3>No Reels Available</h3>");
            }
        }
        else {
            System.out.println("Not logged in, redirecting to home page");
            response.sendRedirect("login.jsp");
        }
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
        
        if(userController.validLogin(username, password)) 
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            System.out.println(request.getSession().getAttribute("user").toString());
            response.sendRedirect("home.jsp");
        }     
        else 
        {
            String message = "Username or password is incorrect";
            request.setAttribute("incorrect", message);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
    
    public static List<Video> getUserVideos(String user, int reelID) {
        database.connect();
        List<Video> results = database.getUserVideos(user, reelID);
        database.disconnect();
        System.out.println("Get Videos");
        return results;
    }
    
    public static List<Reel> getUserReels(String user) {
        database.connect();
        List<Reel> results = database.getUserReels(user);
        database.disconnect();
        System.out.println("Get Reels");
        return results;
    }
}
