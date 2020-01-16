package maintenance_system.model;

public class MarSysErrorMsgs {
	

	private String m_errorMsg;
	private String facility_typeError;
	private String facility_nameError;
	private String urgencyError;
	private String descriptionError;
//	private String reported_byError;
	private String mar_numberError;

	
	public MarSysErrorMsgs() {
		//super();
		this.m_errorMsg = "";
		this.facility_typeError = "";
		this.facility_nameError = "";
		this.urgencyError = "";
		this.descriptionError = "";
//		this.reported_byError = "";
		this.mar_numberError = "";
	
	}

	
	public String getMErrorMsg() {
		return m_errorMsg;
	}

	public void setMErrorMsg(String action) {
		if(action.equalsIgnoreCase("createMar"))
		{
			
			if(!facility_typeError.equals("") || !facility_nameError.equals("") || !urgencyError.equals("")|| !descriptionError.equals("") 
			|| !mar_numberError.equals("")) //!reported_byError.equals("")
			{
				this.m_errorMsg = "Please correct the following errors";
			}
		}
		
	}

	public String getFacility_typeError() {
		return facility_typeError;
	}

	public void setFacility_typeError(String facility_typeError) {
		this.facility_typeError = facility_typeError;
	}

	public String getFacility_nameError() {
		return facility_nameError;
	}

	public void setFacility_nameError(String facility_nameError) {
		this.facility_nameError = facility_nameError;
	}

	public String getUrgencyError() {
		return urgencyError;
	}

	public void setUrgencyError(String urgencyError) {
		this.urgencyError = urgencyError;
	}

	public String getDescriptionError() {
		return descriptionError;
	}

	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}

//	public String getReported_byError() {
//		return reported_byError;
//	}

//	public void setReported_byError(String reported_byError) {
//		this.reported_byError = reported_byError;
//	}


	public String getMar_numberError() {
		return mar_numberError;
	}

	public void setMar_numberError(String mar_numberError) {
		this.mar_numberError = mar_numberError;
	}

}
