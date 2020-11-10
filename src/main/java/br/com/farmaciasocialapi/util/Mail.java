package br.com.farmaciasocialapi.util;
import java.util.HashMap;
import java.util.Map;
public class Mail {
	private String fromEmail;
    private String to;
    private String subject;
    private String fromName;
    private String template;
    private Map<String, Object> model = new HashMap<>();
    public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Map<String, Object> getModel() {
		return model;
	}
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
}











