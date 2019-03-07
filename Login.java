package smartCrawler.LoginRegister;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session=request.getSession(true);
		System.out.println("Login Servlet");
		try {
			System.out.println("Email " + request.getParameter("logemail"));
			
			System.out.println("Password "+ request.getParameter("logpassword"));
			
			if(request.getParameter("logemail").equals("admin@gmail.com")&&request.getParameter("logpassword").equals("admin@123"))
			{
				session.setAttribute("UserLoginName", request.getParameter("logemail"));
				session.setAttribute("usage", "0");
				RequestDispatcher rd = request.getRequestDispatcher("/AdminHomePage.jsp");
				rd.include(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				
				
				
				
				
				ResultSet rs=Dbconn.getResultFromSqlQuery("select * from user_details where user_email='"+request.getParameter("logemail")+"' and password='"+request.getParameter("logpassword")+"'");
				if(rs.next())
				{
					
					session.setAttribute("UserLoginName", request.getParameter("logemail"));
					RequestDispatcher rd = request.getRequestDispatcher("/Search.jsp");
					rd.include(request, response);	
				}else
				{
					PrintWriter out1 = null;
					response.setContentType("text/html;charset=UTF-8");
					out1 = response.getWriter();
					out1.println("<html><script>alert('Wrong Username and Password');</script><body>");
					out1.println("");
					out1.println("</body></html>");
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.include(request, response);
					
				}
				
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
