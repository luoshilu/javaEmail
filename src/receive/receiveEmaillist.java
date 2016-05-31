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
	        props.setProperty("mail.store.protocol", "pop3");       // Э��  
	        props.setProperty("mail.pop3.port", "110");             // �˿�  
	        props.setProperty("mail.pop3.host", "pop3.sina.com");    // pop3������  
	          
	        // ����Sessionʵ������  
	        Session session = Session.getInstance(props);  
	        Store store = session.getStore("pop3");  
	        store.connect(username, password);  
	          
	        // ����ռ���  
	        Folder folder = store.getFolder("INBOX");  
	        /* Folder.READ_ONLY��ֻ��Ȩ�� 
	         * Folder.READ_WRITE���ɶ���д�������޸��ʼ���״̬�� 
	         */  
	        folder.open(Folder.READ_WRITE); //���ռ���  
	        Message[] messages = folder.getMessages();  
	        List<EmailObject>alllist = new ArrayList<EmailObject>();
	       alllist = parseMessage(messages);  
	          
	        //�ͷ���Դ  
	        folder.close(true);  
	        store.close(); 
	        return alllist;
	}
	 public List parseMessage(Message ...messages) throws MessagingException, IOException {  
	        if (messages == null || messages.length < 1)   
	            throw new MessagingException("δ�ҵ�Ҫ�������ʼ�!"); 
	        //StringBuffer content = new StringBuffer(30);
	        List<EmailObject>alllist = new ArrayList<EmailObject>();
	       // EmailObject emo = new EmailObject();
	        // ���������ʼ�  
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
	            //System.out.println("------------------������" + msg.getMessageNumber() + "���ʼ�-------------------- ");  
	            //System.out.println("����: " + getSubject(msg)); 
	            //System.out.println("������: " + getFrom(msg));  
//	            System.out.println("�ռ��ˣ�" + getReceiveAddress(msg, null));  
//	            System.out.println("����ʱ�䣺" + getSentDate(msg, null));  
//	            System.out.println("�Ƿ��Ѷ���" + isSeen(msg));  
//	            System.out.println("�ʼ����ȼ���" + getPriority(msg));  
//	            System.out.println("�Ƿ���Ҫ��ִ��" + isReplySign(msg));  
//	            System.out.println("�ʼ���С��" + msg.getSize() * 1024 + "kb");  
//	            boolean isContainerAttachment = isContainAttachment(msg);  
//	            System.out.println("�Ƿ����������" + isContainerAttachment);  
//	            if (isContainerAttachment) {  
//	                saveAttachment(msg, "e:\\"+msg.getSubject() + "_"); //���渽��  
//	            }   
	           // StringBuffer content = new StringBuffer(30);  
//	            getMailTextContent(msg, content);  
//	            System.out.println("�ʼ����ģ�" + (content.length() > 100 ? content.substring(0,100) + "..." : content));  
//	            System.out.println("------------------��" + msg.getMessageNumber() + "���ʼ���������-------------------- ");  
//	            System.out.println();  
	        }  
	        return alllist;
	    }
	 /** 
	     * ����ʼ����� 
	     * @param msg �ʼ����� 
	     * @return �������ʼ����� 
	     */  
	    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {  
	        return MimeUtility.decodeText(msg.getSubject());  
	    }  
	      
	    /** 
	     * ����ʼ������� 
	     * @param msg �ʼ����� 
	     * @return ���� <Email��ַ> 
	     * @throws MessagingException 
	     * @throws UnsupportedEncodingException  
	     */  
	    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {  
	        String from = "";  
	        Address[] froms = msg.getFrom();  
	        if (froms.length < 1)  
	            throw new MessagingException("û�з�����!");  
	          
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
	     * ����ʼ��ı����� 
	     * @param part �ʼ��� 
	     * @param content �洢�ʼ��ı����ݵ��ַ��� 
	     * @throws MessagingException 
	     * @throws IOException 
	     */  
	  public void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
	        //������ı����͵ĸ�����ͨ��getContent��������ȡ���ı����ݣ����ⲻ��������Ҫ�Ľ��������������Ҫ���ж�  
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
	    //ʱ��
	    public String getSentDate(MimeMessage msg, String pattern) throws MessagingException {  
	        Date receivedDate = msg.getSentDate();  
	        if (receivedDate == null)  
	            return "";  
	          
	        if (pattern == null || "".equals(pattern))  
	            pattern = "yyyy��MM��dd�� E HH:mm ";  
	          
	        return new SimpleDateFormat(pattern).format(receivedDate);  
	    }  


}
