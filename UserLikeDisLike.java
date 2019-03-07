package smartCrawler.analysis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import smartCrawler.LoginRegister.Dbconn;



public class UserLikeDisLike extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String value=request.getParameter("u");
		HttpSession session=request.getSession(true);
		System.out.println(" value of like and dislike "+value);
		try {
		if(value.equals("like"))
		{
			
			
				
				
				Dbconn.insertUpdateFromSqlQuery("insert into feedback(searchkeyword,feedback) values('" + session.getAttribute("SearchingKeyword")	+ "' , 'like' )");
				
				
			
		}
		else if(value.equals("dislike"))
		{
			Dbconn.insertUpdateFromSqlQuery("insert into feedback values('" + session.getAttribute("SearchingKeyword")	+ "' , 'Dislike' )");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		RequestDispatcher rd = request.getRequestDispatcher("/Search.jsp");
		rd.include(request, response); 
	}
}
