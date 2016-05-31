package servlet;

import index.indexver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class indexServlet extends HttpServlet {

	public indexServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username, password;
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
			System.out.println(username);
			System.out.println(password);
			indexver ver = new indexver();
			if(ver.receive(username,password)==0){
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				session.setAttribute("password",password);
				PrintWriter wr = response.getWriter();
			    wr.write("1");
			    /*response.encodeRedirectURL("/index.html");*/
			   // ((HttpServletResponse) request).sendRedirect("/index.jsp");
			   //request.getRequestDispatcher("/html/index.html").forward(request, response);
			   // System.out.println("true");
			}
			else {
				PrintWriter writer = response.getWriter();
			     writer.write("0");
			     System.out.println("shibai");
			}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				System.out.println("yc");
			}
			
		}
}
