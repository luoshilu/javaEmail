package receive;

public class EmailObject {
	public EmailObject(){
		
	}
	private int number;
	private String from;
	private String subject;
	private StringBuffer text = new StringBuffer(30);
	private String time;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public StringBuffer getText() {
		return text;
	}
	public void setText(StringBuffer text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	

}
