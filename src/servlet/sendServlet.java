package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import send.sendEmail;

public class sendServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public sendServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String from = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		String setname = request.getParameter("setname");
		String subject = request.getParameter("subject");
		String messages = request.getParameter("message");
		System.out.print(setname+subject+messages);
		try{
			sendEmail sde = new sendEmail();
			if(sde.send(from, password, setname, subject, messages))
			{
			  out.write("true");
			}else{
			  out.write("false");
			}
		}
		catch(Exception ex)
		{
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
