package smartCrawler.LoginRegister;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registration extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Registration Servlet");
		try {
			HttpSession session=request.getSession();
			
			
			
			//String t7=req.getParameter("agree");

			
			
			int id=0;
			Dbconn.insertUpdateFromSqlQuery("insert into user_details values('"+request.getParameter("firstname")+"','"+request.getParameter("lastname")+"','"+request.getParameter("email")+"','"+request.getParameter("number")+"','"+request.getParameter("firstname")+"','"+request.getParameter("password")+"')");
			System.out.println("Done");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
