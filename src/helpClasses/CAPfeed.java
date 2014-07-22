package helpClasses;

import java.util.ArrayList;
import java.util.List;

public class CAPfeed {

	String title;
	String id;
	String rights;
	String updated;
	final List<FeedMsg> entries = new ArrayList<FeedMsg>();
	
	public CAPfeed(String title, String id, String rights, String updated) {
		this.title = title;
		this.id = id;
		this.rights = rights;
		this.updated = updated;
	}
	
	public CAPfeed(){}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public List<FeedMsg> getEntries() {
		return entries;
	}

}
