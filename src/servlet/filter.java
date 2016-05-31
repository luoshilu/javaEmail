package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init LoginFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        //��ServletRequest��ServletResponseת��������������
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();

        //����web.xml������Filter����ȫ�����󣬿����ų�����Ҫ���˵�url
        String requestURI = req.getRequestURI();
        if(requestURI.endsWith("login.html")){
            chain.doFilter(request, response);
            return;
        }

        //�ж��û��Ƿ��¼������ҳ��Ĵ���
        if(null == session.getAttribute("username")){
            //δ��¼�û����ض��򵽵�¼ҳ��
        	System.out.print("1");
            /*((HttpServletResponse)response).sendRedirect("login.html");*/
        	request.getRequestDispatcher("login.html").forward(request,response);
            return;
        } else {
            //�ѵ�¼�û����������
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy!!!");
    }
}