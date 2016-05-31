package receive;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.UnsupportedEncodingException;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Date;  
import java.util.List;
import java.util.Properties;  
  
import javax.mail.Address;  
import javax.mail.BodyPart;  
import javax.mail.Flags;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Multipart;  
import javax.mail.Part;  
import javax.mail.Session;  
import javax.mail.Store;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  
import javax.mail.internet.MimeUtility;  

public class receiveEmaillist {
	public receiveEmaillist(){
		
	}
	public static void main(String[] args) throws Exception{
		
		receiveEmaillist rel = new receiveEmaillist();
		List<EmailObject> alllist = new ArrayList<EmailObject>(); 
		alllist = rel.Emaillist("rlovezt@sina.com","982812162");
		System.out.println(alllist.get(1).getText());
	}
	public List Emaillist(String username,String password)
			throws Exception {
		 Properties props = new Properties();  
	        props.setProperty("mail.store.protocol", "pop3");       // 协议  
	        props.setProperty("mail.pop3.port", "110");             // 端口  
	        props.setProperty("mail.pop3.host", "pop3.sina.com");    // pop3服务器  
	          
	        // 创建Session实例对象  
	        Session session = Session.getInstance(props);  
	        Store store = session.getStore("pop3");  
	        store.connect(username, password);  
	          
	        // 获得收件箱  
	        Folder folder = store.getFolder("INBOX");  
	        /* Folder.READ_ONLY：只读权限 
	         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
	         */  
	        folder.open(Folder.READ_WRITE); //打开收件箱  
	        Message[] messages = folder.getMessages();  
	        List<EmailObject>alllist = new ArrayList<EmailObject>();
	       alllist = parseMessage(messages);  
	          
	        //释放资源  
	        folder.close(true);  
	        store.close(); 
	        return alllist;
	}
	 public List parseMessage(Message ...messages) throws MessagingException, IOException {  
	        if (messages == null || messages.length < 1)   
	            throw new MessagingException("未找到要解析的邮件!"); 
	        //StringBuffer content = new StringBuffer(30);
	        List<EmailObject>alllist = new ArrayList<EmailObject>();
	       // EmailObject emo = new EmailObject();
	        // 解析所有邮件  
	        for (int i = 0, count = messages.length; i < count; i++) {  
	        	EmailObject emo = new EmailObject();
	        	StringBuffer content = new StringBuffer(30);
	            MimeMessage msg = (MimeMessage) messages[i]; 
	            emo.setFrom(getFrom(msg));
	            emo.setSubject(getSubject(msg));
	            getMailTextContent(msg,content);
	            emo.setText(content);
	            emo.setTime(getSentDate(msg, null));
	            emo.setNumber(i+1);
	            alllist.add(emo);
	            //System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");  
	            //System.out.println("主题: " + getSubject(msg)); 
	            //System.out.println("发件人: " + getFrom(msg));  
//	            System.out.println("收件人：" + getReceiveAddress(msg, null));  
//	            System.out.println("发送时间：" + getSentDate(msg, null));  
//	            System.out.println("是否已读：" + isSeen(msg));  
//	            System.out.println("邮件优先级：" + getPriority(msg));  
//	            System.out.println("是否需要回执：" + isReplySign(msg));  
//	            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");  
//	            boolean isContainerAttachment = isContainAttachment(msg);  
//	            System.out.println("是否包含附件：" + isContainerAttachment);  
//	            if (isContainerAttachment) {  
//	                saveAttachment(msg, "e:\\"+msg.getSubject() + "_"); //保存附件  
//	            }   
	           // StringBuffer content = new StringBuffer(30);  
//	            getMailTextContent(msg, content);  
//	            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));  
//	            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");  
//	            System.out.println();  
	        }  
	        return alllist;
	    }
	 /** 
	     * 获得邮件主题 
	     * @param msg 邮件内容 
	     * @return 解码后的邮件主题 
	     */  
	    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {  
	        return MimeUtility.decodeText(msg.getSubject());  
	    }  
	      
	    /** 
	     * 获得邮件发件人 
	     * @param msg 邮件内容 
	     * @return 姓名 <Email地址> 
	     * @throws MessagingException 
	     * @throws UnsupportedEncodingException  
	     */  
	    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {  
	        String from = "";  
	        Address[] froms = msg.getFrom();  
	        if (froms.length < 1)  
	            throw new MessagingException("没有发件人!");  
	          
	        InternetAddress address = (InternetAddress) froms[0];  
	        String person = address.getPersonal();  
	        if (person != null) {  
	            person = MimeUtility.decodeText(person) + " ";  
	        } else {  
	            person = "";  
	        }  
	        from = person + "<" + address.getAddress() + ">";  
	          
	        return from;  
	    } 
	    /** 
	     * 获得邮件文本内容 
	     * @param part 邮件体 
	     * @param content 存储邮件文本内容的字符串 
	     * @throws MessagingException 
	     * @throws IOException 
	     */  
	  public void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
	        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
	        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
	        if (part.isMimeType("text/*") && !isContainTextAttach) {  
	            content.append(part.getContent().toString());  
	        } else if (part.isMimeType("message/rfc822")) {   
	            getMailTextContent((Part)part.getContent(),content);  
	        } else if (part.isMimeType("multipart/*")) {  
	            Multipart multipart = (Multipart) part.getContent();  
	            int partCount = multipart.getCount();  
	            for (int i = 0; i < partCount; i++) {  
	                BodyPart bodyPart = multipart.getBodyPart(i);  
	                getMailTextContent(bodyPart,content);  
	            }  
	        }  
	    } 
	    //时间
	    public String getSentDate(MimeMessage msg, String pattern) throws MessagingException {  
	        Date receivedDate = msg.getSentDate();  
	        if (receivedDate == null)  
	            return "";  
	          
	        if (pattern == null || "".equals(pattern))  
	            pattern = "yyyy年MM月dd日 E HH:mm ";  
	          
	        return new SimpleDateFormat(pattern).format(receivedDate);  
	    }  


}
