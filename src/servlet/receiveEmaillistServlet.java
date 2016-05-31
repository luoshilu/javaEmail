package servlet;
import index.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import json.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import receive.EmailObject;
import receive.receiveEmaillist;

public class receiveEmaillistServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public receiveEmaillistServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		String password =(String) request.getSession().getAttribute("password");
		receiveEmaillist rel = new receiveEmaillist();
		PrintWriter out = response.getWriter();
		List<EmailObject>alllist = new ArrayList<EmailObject>();
		response.setContentType("application/x-json;charset=UTF-8");
		HttpSession session=request.getSession();
	
		try {
			alllist = rel.Emaillist(username, password);/*
			session.setAttribute("list",alllist);*/
			
			/*
			JSONArray jsonarray = JSONArray.fromObject(list);*/
			for(int loop=(alllist.size());loop>0;loop--) { 
				EmailObject mail=(EmailObject)alllist.get(loop-1);
				System.out.print(mail.getFrom());
			}
			
			/*StringBuilder jso=json.list2json(alllist);
			System.out.print(jso.toString());
			out.print(jso.toString());
			System.out.print("***·µ»Ø");
			out.print("123");*/
			/*net.sf.json.JSONObject JSONObject = net.sf.json.JSONObject.fromObject(alllist);
			System.out.print(JSONObject.toString());
			out.print(JSONObject);*/
			/* JSONArray jsonarray = JSONArray.fromObject(alllist);
			List list = new ArrayList();
			String from=alllist.get(0).getFrom();
			String sub=alllist.get(0).getSubject();
		    String str = null;
			for(int i=0;i<5;i++){
			String text=alllist.get(i).getText().toString();
			String from=alllist.get(i).getFrom();
			String subject=alllist.get(i).getSubject();
			String time=alllist.get(i).getTime();
			if(i<4){
			str+="{"+(i+1)+"[{from:"+from+"subject:"+subject+"text:"+text+"time:"+time+"}";
			}else{
				str+="from:"+from+"subject:"+subject+"text:"+text+"time:"+time+"]";
			}
			}*/
			/*Map map = new HashMap();
			map.put("alllist",alllist);
			JSONObject jso = JSONObject.fromObject(map);
			System.out.println(jso);*/
			/*out.write(alllist.get(0).getFrom());*/
			//alllist.get(0).getFrom() = request.se
		} catch (Exception e) {
		}


	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}