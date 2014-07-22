package helpClasses;

public class Parameters {
	String valueName;
	String value;
	
public Parameters(String valueName, String value) {
		super();
		this.valueName = valueName;
		this.value = value;
	}

public Parameters() {
	// TODO Auto-generated constructor stub
}

public String getValueName() {
	return valueName;
}

public void setValueName(String valueName) {
	this.valueName = valueName;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}


}
