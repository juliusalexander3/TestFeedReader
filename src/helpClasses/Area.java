package helpClasses;

public class Area {



	//Area Drescription
	String areaDesc;
	//OPTIONAL
	//Area Polygon*
	//Area Circle*
	//Area Geocode*
	//Altitude
	//Ceiling
	
	public Area(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	
}
