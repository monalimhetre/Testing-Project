package maintenance_system.model;

public class MarSys {

	private static final long serialVersionUID = 3L;
	private String facility_type = "";
	private String facility_name = "";
	private String urgency = "";
	private String description = "";
	private String reported_by = "";
	private String date_value = "";
	private String mar_number = "";
	private String assignedTo = "";
	private String assignedDate = "";
	private String estimate = "";

	
	public void setMarSys(String facility_type, String facility_name, String urgency, String description, String reported_by, String date_value, String mar_number, String assignedTo, String assignedDate, String estimate) {
		setFacility_type(facility_type);
		setFacility_name(facility_name);
		setUrgency(urgency);
		setDescription(description);
		setReported_by(reported_by);
		setDate_value(date_value);
		setMar_number(mar_number);
		setAssignedTo(assignedTo);
		setAssignedDate(assignedDate);
		setEstimate(estimate);

	}

	public String getFacility_type() {
		return facility_type;
	}
	public void setFacility_type(String facility_type) {
		this.facility_type = facility_type;
	}
	

	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	

	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public String getReported_by() {
		return reported_by;
	}
	public void setReported_by(String reported_by) {
		this.reported_by = reported_by;
	}
	
	
	public String getDate_value() {
		return date_value;
	}
	public void setDate_value(String date_value) {
		this.date_value = date_value;
	}
	
	public String getMar_number() {
		return mar_number;
	}
	public void setMar_number(String mar_number) {
		this.mar_number = mar_number;
	}
	
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	
	
	
	
	
	public void  validateMarSys(String action, MarSys mar_sys, MarSysErrorMsgs m_errorMsg){
		if(action.equalsIgnoreCase("createMar")){
			m_errorMsg.setFacility_typeError(validateFacility_type(action, mar_sys.getFacility_type()));
			m_errorMsg.setFacility_nameError(validateFacility_name(action, mar_sys.getFacility_name())); 
			//m_errorMsg.setUrgencyError(validateUrgency(action, mar_sys.getUrgency()));
			m_errorMsg.setDescriptionError(validateDescription(action, mar_sys.getDescription()));
			//m_errorMsg.setReported_byError(validateReported_by(action, mar_sys.getReported_by()));
			m_errorMsg.setMar_numberError(validateMar_number(action, mar_sys.getMar_number()));
					
			m_errorMsg.setMErrorMsg(action);
			
			
		}
			
	}
	
	private String validateFacility_type(String action, String type){
		String msg="";
		
		if(action.equalsIgnoreCase("createMar")){
			if(type.equals("")){
				msg="Please provide Facility Type";
			}
			else if(type.length()<3){
				msg="Facility type should be at least 3 letter long";
			}
//			else if (!isTextOnlyAlphabet(type)){
//				msg = "Facility type should only be Alphabet";
//			}
		}
		
		return msg;
	}
	
	private String validateFacility_name(String action, String name){
		String msg="";
		
		if(action.equalsIgnoreCase("createMar")){
			if(name.equals("")){
				msg="Please provide Facility name";
			}
			else if(name.length()<3){
				msg="Facility name should be at least 3 letter long";
			}

		}
		
		return msg;
	}
	
//	private String validateUrgency(String action, String urcy){
//		String msg="";
//		
//		if(action.equalsIgnoreCase("createMar")){
//			if(urcy.equals("")){
//				msg="Please provide Urgency";
//			}
////			else if(urcy.length()<3){
////				msg="Urgency should be at least 3 letter long";
////			}
////			else if (!isTextOnlyAlphabet(urcy)){
////				msg = "Urgency should only be Alphabet";
////			}
//		}
//		
//		return msg;
//	}
	
		
	private String validateDescription(String action, String desp){
		String msg="";
		
		if(action.equalsIgnoreCase("createMar")){
			if(desp == null || desp.equals("")){
				msg="Please provide Description";
			}
			else if(desp.length()<3){
				msg="Description should be at least 3 letter long";
			}
			else if (!isTextOnlyAlphabet(desp)){
				msg = "Description should only be Alphabet";
			}
		}
		
		return msg;
	}
	
private String validateReported_by(String action, String repo){
		String msg="";
		
		if(action.equalsIgnoreCase("createMar")){
			if(repo == null || repo.equals("")){
				msg="Please provide Report";
			}
			else if(repo.length()<3){
				msg="Report should be at least 3 letter long";
			}

		}
		
		return msg;
	}
	
private String validateDate_value(String action, String date1){
		String msg="";
		
		if(action.equalsIgnoreCase("createMar")){
			if(date1 == null || date1.equals("")){
				msg="Please provide Date";
			}
		}
		
		return msg;
	}
	
private String validateMar_number(String action, String marNo){
		String msg="";
		
		if(action.equalsIgnoreCase("createMar")){
			if(marNo == null || marNo.equals("")){
				msg="Please provide MarNumber";
			}
		
		}
		
		return msg;
	}
	
//private boolean isTextAnInteger (String string) {
//        boolean result;
//		try
//        {
//            Long.parseLong(string);
//            result=true;
//        } 
//        catch (NumberFormatException e) 
//        {
//            result=false;
//        }
//		return result;
//	}
	
private boolean isTextOnlyAlphabet (String str) 
    { 
        return ((str != null) 
                && (!str.equals("")) 
                && (str.matches("^[a-zA-Z]*$"))); 
    }

@Override
public String toString() {
	return "MarSys [facility_type=" + facility_type + ", facility_name=" + facility_name + ", urgency=" + urgency
			+ ", description=" + description + ", reported_by=" + reported_by + ", date_value=" + date_value
			+ ", mar_number=" + mar_number + ", assigned_to=" + assignedTo + ", assigned_date=" + assignedDate + ", estimate=" + estimate + "]";
}
	
//	private String validateSearch(String action, String search, String filter){
//		String msg = "";
//		if(action.equalsIgnoreCase("searchUser")){
//			if(search.equals("") || search.length()==0){
//				msg = "Please provide your search parameter";
//			}
//			else{
//				if (filter.equals("utaid")){
//					if (!isTextAnInteger(search))
//						msg="UTA ID must be a number";
//				}
//				else {
//					if (!isTextOnlyAlphabet(search))
//						msg = "First name & Last name should only be alphabets";
//				}
//			}
//		}
//		return msg;
//	}


		
}

	
	
