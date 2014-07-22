package helpClasses;

public class Resource {

	
	

	//resourceDesc
	String resourceDesc;
	//mimeType
	String mimeType;
	
	//OPTIONAL
	String size;
	String uri;
	String derefUri;
	String digest;
	
	public Resource(String resourceDesc, String mimeType, String size,
					String uri, String derefUri, String digest) {
		
		this.resourceDesc = resourceDesc;
		this.mimeType = mimeType;
		this.size = size;
		this.uri = uri;
		this.derefUri = derefUri;
		this.digest = digest;
	}
	
	}
