package send;

import java.util.Properties;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendEmail {
	 public boolean send (String from, String password, String setname, String subject, String messages) {
		
        try{
            Properties props=new Properties();
            //����һ���ʼ���������smtp.163.com
            //mail.smtp.host�����Ƿ��������ڵ������������
            props.put("mail.smtp.host", "smtp.sina.com");
            props.put("mail.smtp.auth", true);
            //���ڷ����ʼ���ֻ��Ҫ��֤���������ڵ��ʼ���������ȷ�򿪾Ϳ�����
            //�����˵���������������ַ����@163.com,@qq.com,@126.com
                                
            //����һ���������ʼ���������ͨ��
            Session mailConnection=Session.getInstance(props,null);
            Message msg=new MimeMessage(mailConnection);
                                
            //����һ��Ҫ�����û�����ָ���
            //Session mailConnection=Session.getInstance(props,new MailAuthenticator());
                                
                                
            //���÷����˺ͽ�����
            Address sender=new InternetAddress(from);
            Address receiver=new InternetAddress(setname);
                                
            /*
             * Ⱥ���ʼ��ķ���
             * StringBuffer buffer=new StringBuffer();
             * buffer.append("11@163.com,")
             * buffer.append("22@163.com")
             * String all=buffer.toString();
             * Address[] allre=InternetAddress.parse(all);
             * msg.setRecipient(Message.RecipientType.TO, allre);
             */
            msg.setFrom(sender);
            msg.setRecipient(Message.RecipientType.TO, receiver);
            msg.setSubject(subject);
                                
            //msg.setContent("<h1>Hello<h1>", "text/plain");
                                
                                
            //������ģ�ⷢ�ʹ��������ʼ�
            //�½�һ��MimeMultipart����������Ŷ��BodyPart����
            Multipart mtp=new MimeMultipart();
            //------�����ż��ı�����------
            //�½�һ������ż����ݵ�BodyPart����
            BodyPart mdp=new MimeBodyPart();
            //��BodyPart�����������ݺ͸�ʽ/���뷽ʽ
           // mdp.setContent("<h1>hello</h1><b>�Ӵ�</b>","text/html;charset=utf8");
            mdp.setContent(messages,"text/html;charset=utf8");
            
            //�������ż����ݵ�BodyPart���뵽MimeMultipart������
            mtp.addBodyPart(mdp);
                                
            //�����ż��ĸ���(�ñ��ػ��ϵ��ļ���Ϊ����)
//            mdp=new MimeBodyPart();
//            FileDataSource fds=new FileDataSource("/home/tenlee/ͼƬ/head.jpg");
//            DataHandler dh=new DataHandler(fds);
//            mdp.setFileName("head.jpg");//���Ժ�ԭ�ļ�����һ��
//            mdp.setDataHandler(dh);
//            mtp.addBodyPart(mdp);
//            //��mtp��Ϊ��Ϣ���������
            msg.setContent(mtp);
                                
            //����Ϊ���ʹ������ķ�ʽ
            //�Ƚ��д洢�ʼ�
            msg.saveChanges();
                                
            Transport trans=mailConnection.getTransport("smtp");
           // String username = from;
            //String pw = password;
            //�ʼ���������,�û���������
            trans.connect("smtp.sina.com", from,  password);
            trans.sendMessage(msg, msg.getAllRecipients());
            trans.close();
            return true;
        }catch(Exception e)
        {
        	return false;
        } 
	}
}