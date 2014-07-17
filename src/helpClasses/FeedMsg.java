package helpClasses;

public class FeedMsg {

	
	String id;
	String title;
	String updateTime;
	Content mContent;
	
	public FeedMsg (String id, String title, String UpdateTime, Content mContent){
		
		this.id=id;
		this.title=id;
		this.updateTime =UpdateTime;
		this.mContent = mContent;
		
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Content getmContent() {
		return mContent;
	}

	public void setmContent(Content mContent) {
		this.mContent = mContent;
	}
	
	
	
}

