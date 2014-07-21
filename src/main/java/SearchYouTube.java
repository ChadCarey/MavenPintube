/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.api.services.samples.youtube.cmdline.youtube_cmdline_search_sample.Search;
import com.google.api.services.samples.youtube.cmdline.youtube_cmdline_search_sample.YouTubeVideo;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Developer key = AI39si7CHLZ_J7wkK6y1icBny-y8KDJ1EV3-LLxUffQQdrI-g99Tqk5svbNtHv_8PUmQjtVdaHKwLouyBW0_on3DmHbTuronXQ
 * AIzaSyCk7CHMUUuIjFqJltj8nel29dse8oYhzoA
 * gData Set Up: http://mrhaki.blogspot.com/2009/02/add-google-data-java-client-library-to.html 
 * @author chad
 */
@WebServlet(urlPatterns = {"/SearchYouTube"})
public class SearchYouTube extends HttpServlet {

   
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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchYouTube</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchYouTube at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        
        Search searchYouTube = new Search();
        String search = request.getParameter("search");
        List<YouTubeVideo> results = searchYouTube.search(search);
        
        Writer out = response.getWriter();
        out.write("<h3>Search results for: " + search + "</h3>");
        for(YouTubeVideo video : results) {
        	String title = video.getTitle();
        	title = title.replace("\"", "");
        	title = title.replace("\'", "");
            out.write("<div class='row'>");
        	out.write("<div class='col-xs-6 col-md-4'>");
        	out.write("<div class='thumbnail' style='text-align:center'>");
        	out.write("<h3>" + video.getTitle() + "</h3>");
        	out.write("<div class='embed-responsive embed-responsive-16by9'>");
            out.write("<iframe class='embed-responsive-item' ");
            out.write("src='//www.youtube.com/embed/");
            out.write(video.getId());
            out.write("' allowfullscreen></iframe>");
            out.write("</div>");
            out.write("<form action=\'\' method=\'POST\'>");
            out.write("<input type=\'button\' value=\'Pin Video\' onclick=\"addVideo(\'" +
                    title + "\', \'"+ video.getId() + "\')\"/>");
            out.write("</form>");
            out.write("</div>");
            out.write("</div>");
            out.write("</div><br/>");
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
