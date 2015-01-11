

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
		// TODO Auto-generated method stub
		//request.getSession().invalidate();
		//response.sendRedirect("login.jsp");
            response.setHeader("Cache-Control","no-store"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", 0);

            HttpSession session = request.getSession();
            // ensuring you have an existing session object, and holding objects

            if (session != null && !session.isNew())
            {
                session.setAttribute("user", null) ;
                session.invalidate();//unbinds any objects bound to it
                session = null ;//enforcing the session, that doesn't hold any objects
            }
            response.sendRedirect("login.jsp");
        } 
	

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
		// TODO Auto-generated method stub
            response.setHeader("Cache-Control","no-store"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", 0);

            HttpSession session = request.getSession();
            // ensuring you have an existing session object, and holding objects

            if (session != null && !session.isNew())
            {
                session.setAttribute("user", null) ;
                session.invalidate();//unbinds any objects bound to it
                session = null ;//enforcing the session, that doesn't hold any objects
            }
            response.sendRedirect("login.jsp");
	}

}
