package helpClasses;

import java.util.List;

public class Alert {
	

	//Attributes
	String xmlns="urn:oasis:names:tc:emergency:cap:1.2";
	
	//Message ID
	String identifier;
	//Sender ID
	String sender;
	//Sent Date/Time
	String sent;
	//Message Status
	String status;
	//Message Type
	String msgType;
	//Source
	String source;
	//Scope
	String scope;
	//Restriction
	String restriction;
	//Handling Code
	List<String> code;
	//Inf*
	List<Info> information;
	
	public Alert(){
		
	}
	
	public Alert(String identifier) {
		this.identifier = identifier;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}



	public List<Info> getInformation() {
		return information;
	}

	public void setInformation(List<Info> information) {
		this.information = information;
	}

	public List<String> getCode() {
		return code;
	}

	public void setCode(List<String> code) {
		this.code = code;
	}
	
}
