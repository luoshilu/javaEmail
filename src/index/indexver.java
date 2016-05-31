package index;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
public class indexver {
	public indexver(){
		
	}
	public int receive(String username,String password) 
			throws MessagingException {  
		try{
        // 准备连接服务器的会话信息  
        Properties props = new Properties();  
        props.setProperty("mail.store.protocol", "pop3");       // 协议  
        props.setProperty("mail.pop3.port", "110");             // 端口  
        props.setProperty("mail.pop3.host", "pop3.sina.com");    // pop3服务器  
       /* props.setProperty("mail.pop3.host", "pop.qq.com");    // pop3服务器 
*/          
        // 创建Session实例对象  
        Session session = Session.getInstance(props);  
        Store store = session.getStore("pop3");  
        store.connect(username, password);
		}
	catch(Exception e)
	{
		//ex.printStackTrace();
		return -1;
		
	}
		return 0;
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) throws Exception{
//		
//		
//		// TODO Auto-generated method stub
//
//	}

}
