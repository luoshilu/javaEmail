package servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class sendfileServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private String filepath;
    private String temppath;
    private String buf;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        ServletContext context=getServletContext();
        filepath=context.getRealPath("/"+config.getInitParameter("filepath"));
        temppath=context.getRealPath("/"+config.getInitParameter("temppath"));
        
    }
    
	public sendfileServlet() {
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

//		doPost(request,response);
		response.setContentType("text/html");
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
		response.setContentType("text/plain;charset=utf-8");
        PrintWriter writer=response.getWriter();
        int count=0;
        try{
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();
            diskFactory.setSizeThreshold(4 *1024 );
            diskFactory.setRepository(new File(temppath));
            ServletFileUpload upload = new ServletFileUpload(diskFactory);
            upload.setSizeMax(4 * 1024 * 1024);
            List fileItems = upload.parseRequest(request);
            Iterator iter = fileItems.iterator();
            while (iter.hasNext()){
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()){
                    writer.println(item.getFieldName()+" : "+item.getString());
                }
                else{
                    String filename = item.getName();
                    filename = filename.substring(
                    filename.lastIndexOf("\\")+1,filename.length());
                    File uploadFile = new File(filepath+"\\"+filename);
                    item.write(uploadFile);
                    writer.println("文件名:"+ filename);
                    writer.println(" 文件类型: "+item.getContentType());
                    count++;
                    System.out.print(filepath+"\\"+filename);
                    String path=(filepath+"\\"+filename);
                    HttpSession session=request.getSession();
                    session.setAttribute("path", path);
                }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        
	      /*  System.out.print(filepath);
	        System.out.print(temppath);*/
            writer.println("上传文件成功!");
            writer.close();
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
