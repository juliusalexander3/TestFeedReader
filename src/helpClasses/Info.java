package helpClasses;

import java.util.List;

public class Info {

	
	//Language
	String language="en-US";
	//Event Category
	List<String> category;
	//Event Type
	String event;
	//Response Type
	List<String> responseType;
	//Urgency
	String urgency;
	//Severity
	String severity;
	//Certainty
	String certainty;
	//Audience
	//Event Code
	List<String> eventCode;
	//Efective Date/Time
	String effective	;
	//OnSet Date/Time
	//Expiration Date/Time
	String expires;
	//Sender Name
	String senderName;
	//Headline
	String headline;
	//Event Description
	String description;
	//Instructions
	//Information URL
	String web;
	//Contact Info
	String contact;
	//Parameter
	List<Parameters> parameters;
	
	
	public Info(){}
	
	public Info(List<String> category, String event, List<String> responseType) {
		this.category = category;
		this.event = event;
		this.responseType = responseType;
	}

	
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public List<String> getResponseType() {
		return responseType;
	}

	public void setResponseType(List<String> responseType) {
		this.responseType = responseType;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getCertainty() {
		return certainty;
	}

	public void setCertainty(String certainty) {
		this.certainty = certainty;
	}

	public List<String> getEventCode() {
		return eventCode;
	}

	public void setEventCode(List<String> eventCode) {
		this.eventCode = eventCode;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Parameters> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameters> parameters) {
		this.parameters = parameters;
	}
	
	
	

}
