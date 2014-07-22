package helpClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class CAPfeedParser {



    // We don't use namespaces
    private static final String ns = null;
	static final String TITLE= "title";
	static final String ID = "id";
	static final String RIGHTS= "rights"; 
	static final String UPDATED= "updated";

	XmlPullParser parser;
	String fileLoc;
	public InputStream in;

	public CAPfeedParser(String fileLoc) throws XmlPullParserException, FileNotFoundException {
		this.fileLoc = fileLoc;
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		parser = factory.newPullParser();
		parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		
		Reader mReader = new FileReader(new File(fileLoc));
		parser.setInput(mReader);
	}
	
	
	public CAPfeedParser(InputStream in) throws XmlPullParserException{
		this.in=in;
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		parser = factory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(this.in, null);
		
	}
	
	public void CloseInputStream() throws IOException{
		this.in.close();
	}
	


	public CAPfeed readFeed() throws XmlPullParserException, IOException {
		CAPfeed feed= new CAPfeed();

		parser.nextTag();
		String nombrespacial = parser.getNamespace();
		int tipo =parser.getEventType();
		parser.require(XmlPullParser.START_TAG, ns, "feed");



		while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
			if(parser.getEventType() == XmlPullParser.START_DOCUMENT) {
				//System.out.println("Start document");

			} else if(parser.getEventType() == XmlPullParser.END_DOCUMENT) {
				//System.out.println("End document");

			} else if(parser.getEventType() == XmlPullParser.START_TAG) {
				//System.out.println("Start tag "+parser.getName());

				if(parser.getName().equals(TITLE)){
					feed.setTitle(readTitle(parser));
				}else if(parser.getName().equals(ID)  ){
					feed.setId(readID(parser));
				}else if(parser.getName().equals(RIGHTS)  ){
					feed.rights = readRights(parser);
				}else if(parser.getName().equals(UPDATED) ){
					feed.updated= readUpdated(parser);
				}else if(parser.getName().equals("entry") ){
					feed.entries.add(readEntry(parser));
				}


			} else if(parser.getEventType() == XmlPullParser.END_TAG) {
				//System.out.println("End tag "+parser.getName());

			} else if(parser.getEventType() == XmlPullParser.TEXT) {

				//System.out.println("Text "+ parser.getText());
			}
			parser.next();
		}



		return feed;



	}
	
	private String readRights(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "rights");
	    String rights = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "rights");
	    return rights;
	}


	private FeedMsg readEntry(XmlPullParser parser) throws XmlPullParserException, IOException{
		parser.require(XmlPullParser.START_TAG, ns, "entry");
		
		
		FeedMsg entryMsg=new FeedMsg();

		
		   while (parser.next() != XmlPullParser.END_TAG) {
		        if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
		        String name = parser.getName();
		        if (name.equals("id")) {
		        	entryMsg.id = readID(parser);
		        } else if (name.equals("title")) {
		        	entryMsg.title = readTitle(parser);
		        } else if (name.equals("updated")) {
		        	entryMsg.updateTime = readUpdated(parser);
		        } else if (name.equals("content")) {
		        	entryMsg.mContent = readContent(parser);
		        }  else {
		            skip(parser);
		        }
		    }
		return entryMsg;
	}
	
	// Processes title tags in the feed.
	private String readID(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "id");
	    String id = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "id");
	    return id;
	}
	
	// Processes title tags in the feed.
	private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "title");
	    String title = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "title");
	    return title;
	}
	
	// Processes title tags in the feed.
	private String readUpdated(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "updated");
	    String updated = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "updated");
	    return updated;
	}
	
	// Processes title tags in the feed.
	private Content readContent(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "content");
	    Content content = new Content();
	    
	    
	    
		   while (parser.next() != XmlPullParser.END_TAG) {
		        if (parser.getEventType() != XmlPullParser.START_TAG) {
		        	content.type = parser.getAttributeValue(ns, "type");
		            continue;
		        }
		        String name = parser.getName();
		        if (name.equals("alert")) {
		        	content.mAlert = readAlert(parser);
		        }  else {
		            skip(parser);
		        }
		    }
	    

	    return content;
	}
	
	private Alert readAlert(XmlPullParser parser) throws IOException, XmlPullParserException {
		  parser.require(XmlPullParser.START_TAG, ns, "alert");
		Alert mAlert= new Alert();
		while (parser.next() != XmlPullParser.END_TAG) {
			 if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
			 String name = parser.getName();
		        if (name.equals("identifier")) {
		        	mAlert.identifier = readIdentifier(parser);
		        }else if(name.equals("sender")){
		        	mAlert.sender = readSender(parser);
		        }else if(name.equals("sent")){
		        	mAlert.sent= readSent(parser);
		        } else if(name.equals("status")){
		        	mAlert.status= readStatus(parser);
		        } else if(name.equals("msgType")){
		        	mAlert.msgType = readMsgType(parser);
		        } else if(name.equals("source")){
		        	mAlert.source = readSource(parser);
		        }else if(name.equals("scope")){
		        	mAlert.scope= readScope(parser);
		        }else if(name.equals("code")){
		        	 skip(parser);
		       // 	mAlert.code= readCode(parser);
		        }else if(name.equals("info")){
		        	 skip(parser);
		     //   	mAlert.information = readInfo(parser);
		        	
		        }
		        
		        else {
		            skip(parser);
		        }
		}
		
		return mAlert;	
	}
	
	private List<Info> readInfo(XmlPullParser parser) {
		// TODO Auto-generated method stub
		return null;
	}


	private List<String> readCode(XmlPullParser parser) {
		// TODO Auto-generated method stub
		return null;
	}


	private String readScope(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "scope");
	    String scope = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "scope");
	    return scope;
	}


	private String readSource(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "source");
	    String source = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "source");
	    return source;
	}


	private String readMsgType(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "msgType");
	    String msgType = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "msgType");
	    return msgType;
	}


	private String readStatus(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "status");
	    String status = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "status");
	    return status;
	}


	private String readSent(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "sent");
	    String sent = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "sent");
	    return sent;
	}


	private String readSender(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "sender");
	    String sender = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "sender");
	    return sender;
	}


	private String readIdentifier(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "identifier");
	    String identifier = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "identifier");
	    return identifier;
	}


	private Info readInformation(XmlPullParser parser) throws IOException, XmlPullParserException {
		 parser.require(XmlPullParser.START_TAG, ns, "info");
		Info mInfo= new Info();
		
		while (parser.next() != XmlPullParser.END_TAG) {
			 if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
			 String name = parser.getName();
		        if (name.equals("language")) {
		        	mInfo.language = readLanguage(parser);
		        }else if(name.equals("category")){
		        	 skip(parser);
		        	//mInfo.category = readCategory(parser);
		        }else if(name.equals("event")){
		        	mInfo.event = readEvent(parser);
		        }else if(name.equals("responseType")){
		        	 skip(parser);
		        //	mInfo.responseType= readResponseType(parser);
		        }else if(name.equals("urgency")){
		        	mInfo.urgency = readUrgency(parser);
		        }else if(name.equals("severity")){
		        	mInfo.severity = readSeverity(parser);
		        }else if(name.equals("certainty")){
		        	mInfo.certainty= readCertainty(parser);
		        }else if(name.equals("eventCode")){
		        	 skip(parser);
		        	//mInfo.eventCode= readEventCode(parser);
		        }else if(name.equals("effective")){
		        	mInfo.effective= readEffective(parser);
		        }else if(name.equals("expires")){
		        	mInfo.expires= readExpires(parser);
		        } else if(name.equals("senderName")){
		        	mInfo.senderName = readSenderName(parser);
		        } else if(name.equals("headline")){
		        	mInfo.headline = readHeadline(parser);
		        }else if(name.equals("description")){
		        	mInfo.description = readDescription(parser);
		        }else if(name.equals("web")){
		        	mInfo.web = readWeb(parser);
		        }else if(name.equals("contact")){
		        	mInfo.contact = readContact(parser);
		        }else if(name.equals("parameter")){
		        	 skip(parser);
		        //	mInfo.parameters = readParameters(parser);
		        }
		        
		        else {
		            skip(parser);
		        }
		}
		
		return mInfo;	
	}
	
	private List<Parameters> readParameters(XmlPullParser parser2) {
		// TODO Auto-generated method stub
		return null;
	}


	private String readContact(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "contact");
	    String contact = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "contact");
	    return contact;
	}


	private String readWeb(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "web");
	    String web = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "web");
	    return web;
	}


	private String readDescription(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "description");
	    String description = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "description");
	    return description;
	}


	private String readHeadline(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "headline");
	    String headline = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "headline");
	    return headline;
	}


	private String readSenderName(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "senderName");
	    String senderName = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "senderName");
	    return senderName;
	}


	private String readExpires(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "expires");
	    String expires = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "expires");
	    return expires;
	}


	private String readEffective(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "effective");
	    String effective = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "effective");
	    return effective;
	}


	private List<String> readEventCode(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "eventCode");
		
		// TODO Auto-generated method stub
		parser.require(XmlPullParser.END_TAG, ns, "eventCode");
		return null;
	}


	private String readCertainty(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "certainty");
	    String certainty = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "certainty");
	    return certainty;
	}


	private String readSeverity(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "severity");
	    String severity = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "severity");
	    return severity;
	}


	private String readUrgency(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "urgency");
	    String urgency = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "urgency");
	    return urgency;
	}


	private List<String> readResponseType(XmlPullParser parser2) {
		// TODO Auto-generated method stub
		return null;
	}


	private String readEvent(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "event");
	    String event = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "event");
	    return event;
	}


	private List<String> readCategory(XmlPullParser parser) {
		// TODO Auto-generated method stub
		return null;
	}


	private String readLanguage(XmlPullParser parser) throws IOException, XmlPullParserException{
	    parser.require(XmlPullParser.START_TAG, ns, "language");
	    String language = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "language");
	    return language;
	}


	private Parameters readParameter(XmlPullParser parser) throws IOException, XmlPullParserException {
		 parser.require(XmlPullParser.START_TAG, ns, "parameter");
		Parameters mParameter=new Parameters();
		
		while (parser.next() != XmlPullParser.END_TAG) {
			 if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
			 String name = parser.getName();
		        if (name.equals("identifier")) {
		        	mParameter.valueName = readValueName(parser);
		        }else if(name.equals("sender")){
		        	mParameter.value = readValue(parser);
		        }  
		        
		        else {
		            skip(parser);
		        }
		}
		
		
		return mParameter;	
	}
	
	

	private String readValue(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "value");
	    String value = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "value");
	    return value;
	}


	private String readValueName(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "valueName");
	    String valueName = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "valueName");
	    return valueName;
	}


	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}
	
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
}
