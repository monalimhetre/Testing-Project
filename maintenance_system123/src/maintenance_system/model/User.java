package maintenance_system.model;

public class User {

	private static final long serialVersionUID = 3L;
	private String username = "";
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String password = "";
	private String role = "";
	private String utaid = "";
	private String phone = "";
	private String streetaddress = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	
	private String greetingText = "";
	
	private String search = "";
	private String searchfilter= "";

	
	public void setUser(String username, String firstName, String lastName, String email, String password, String role,
			String utaid, String phone, String streetaddress, String city, String state, String zipcode) {
		setUsername(username);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setRole(role);
		setUtaid(utaid);
		setPhone(phone);
		setStreetaddress(streetaddress);
		setCity(city);
		setState(state);
		setZipcode(zipcode);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUtaid() {
		return utaid;
	}

	public void setUtaid(String utaid) {
		this.utaid = utaid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getGreetingText() {
		return greetingText;
	}

	public void setGreetingText(String greetingText) {
		this.greetingText = greetingText;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchfilter() {
		return searchfilter;
	}

	public void setSearchfilter(String searchfilter) {
		this.searchfilter = searchfilter;
	}

	public void  validateUser(String action, User user, UserErrorMsgs errorMsg){
		if(action.equalsIgnoreCase("saveUser")||action.equalsIgnoreCase("updateUserInDBToEditUserProfileForAdmin")){
			System.out.println("Email error "+ user.getEmail() + " error: "+validateEmail(action, user.getEmail()));
			errorMsg.setUserNameError(validateUsername(action, user.getUsername()));
			errorMsg.setUserFirstNameError(validateFirstName(action, user.getFirstName())); 
			errorMsg.setUserLastNameError(validateLastName(action, user.getLastName()));
			errorMsg.setUserEmailError(validateEmail(action, user.getEmail()));
			errorMsg.setPhoneError(validatePhone(user.getPhone()));
			errorMsg.setUserPasswordError(validatePassword(action, user.getPassword()));
			errorMsg.setUtaIDerror(validateUTAID(user.getUtaid()));
			errorMsg.setStateError(validateState(user.getState()));
			errorMsg.setCityError(validateCity(user.getCity()));
			errorMsg.setStreetAddError(validateStreetAdd(user.getStreetaddress()));
			errorMsg.setZipCodeError(validateZipcode(user.getZipcode()));
			
			errorMsg.setErrorMsg(action);
			System.out.println(errorMsg.getErrorMsg());
			
		}
		
		if(action.equalsIgnoreCase("loginUser")){
			errorMsg.setUserNameError(validateUsername(action, user.getUsername()));
			errorMsg.setUserPasswordError(validatePassword(action, user.getPassword()));
			
			errorMsg.setErrorMsg(action);
			
		}
		
		if(action.equalsIgnoreCase("searchUser")){
			errorMsg.setSearchError(validateSearch(action, user.getSearch(), user.getSearchfilter()));
			
			errorMsg.setErrorMsg(action);
			
		}
		
		if(action.equalsIgnoreCase("updateUserInDB") || action.equalsIgnoreCase("editUser")){
			errorMsg.setUserNameError(validateUsername(action, user.getUsername()));
			errorMsg.setUserFirstNameError(validateFirstName(action, user.getFirstName())); 
			errorMsg.setUserLastNameError(validateLastName(action, user.getLastName()));
			errorMsg.setUserEmailError(validateEmail(action, user.getEmail()));
			errorMsg.setPhoneError(validatePhone(user.getPhone()));
			errorMsg.setUserPasswordError(validatePassword(action, user.getPassword()));
			errorMsg.setUtaIDerror(validateUTAID(user.getUtaid()));
			errorMsg.setStateError(validateState(user.getState()));
			errorMsg.setCityError(validateCity(user.getCity()));
			errorMsg.setStreetAddError(validateStreetAdd(user.getStreetaddress()));
			errorMsg.setZipCodeError(validateZipcode(user.getZipcode()));
			
			errorMsg.setErrorMsg(action);
			
			
		}
//		else if(action.equalsIgnoreCase("searchToEditForAdmin")) {
//			errorMsg.setSearchError(validateSearchWithLName(action, user.getSearch()));
//			errorMsg.setErrorMsg(action);
//		}
			
	}
		
	private String validateUsername(String action, String username){
		String msg="";
		
		if(action.equalsIgnoreCase("saveUser")){
			if(username.equals("")){
				msg="Please provide username";
			}
			else if(username.length()<3){
				msg="Username should be at least 3 letter long ";
			}
			else if (isTextAnInteger(username)){
				msg = "Username cannot only be Numeric";
			}
		}
		else if(action.equalsIgnoreCase("loginUser"))
		{
			if(username.equals("") || username.length()==0)
			{
				msg = "Please enter username";
			}
		}
		return msg;
	}
	
	private String validateFirstName(String action, String name){
		String msg="";
		System.out.println("vfn");
//		if(action.equalsIgnoreCase("saveUser")){
			if(name.equals("")){
				msg="Please provide First name";
			}
			else if(name.length()<3){
				msg="First name should be at least 3 letter long";
			}
			else if (!isTextOnlyAlphabet(name)){
				msg = "First name should only be Alphabet";
			}
//		}
//		System.out.println(msg);
		return msg;
	}
	
	private String validateLastName(String action, String name){
		String msg="";
		
//		if(action.equalsIgnoreCase("saveUser")){
			if(name.equals("")){
				msg="Please provide Last name";
			}
			else if(name.length()<3){
				msg="Last name should be at least 3 letter long";
			}
			else if (!isTextOnlyAlphabet(name)){
				msg = "Last name should only be Alphabet";
			}
//		}
		
		return msg;
	}
	
	private String validateEmail(String action, String email){
		String msg="",extension="";
		
//		if(action.equalsIgnoreCase("saveUser")){
			if(email.equals("")){
				msg="Please provide Email";
				}
			else{
				if (!email.contains("@")){
					msg = "Email address needs to contain @";
				 }
				else{
					extension = email.substring(email.length()-4, email.length());
					if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil")){
						msg = "Invalid domain name";				
					 }
				}
			}
//			}
		return msg;
	}
	
	private String validatePassword(String action, String password){
		String msg="";
		
		if(action.equalsIgnoreCase("saveUser") || action.equalsIgnoreCase("updateUserInDB")){
			if(password.equals("")){
				msg = "Please provide password";
			}
			else if(password.length()<6){
				msg = "Password should be at least 6 characters long.";
			}
			else if(password.length()>16){
				msg = "Password can not be more than 16 characters long.";
			}
			else if(password.equals(password.toLowerCase())){
				msg = "Password should contain at least 1 capital letter.";
			}
			else if(password.matches("[A-Za-z0-9 ]*")){
				msg = "Password should contain at least 1 special letter";
			}
		}
		
		else if(action.equalsIgnoreCase("loginUser") || action.equalsIgnoreCase("updateUserInDB")){
			if(password.equals("") || password.length()==0){
				msg = "Please enter password";
			}
		}
		return msg;
	}
	
	private String validatePhone( String phone){
		String msg = "";
		
		if (phone.length()!=10)
			msg="Phone number must be 10 digits in length";
		else
			if (!isTextAnInteger(phone))
				msg="Phone number must be a number";
		
		return msg;
	}
	
	private String validateUTAID( String phone){
		String msg = "";
		
		if (phone.length()!=10)
			msg="UTA ID must be 10 digits in length";
		else
			if (!isTextAnInteger(phone))
				msg="UTA ID must be a number";
		
		return msg;
	}
	
	private String validateStreetAdd(String address){
		String msg = ""; 
		
		if(address.equals("") || address.length()==0){
			msg = "Please provide street address";
		}
		else if(address.length()>45){
			msg = "Street address can not be more than 45 characters long.";
		}
		return msg;
	}
	
	private String validateCity(String city){
		String msg="";
		
		if (city.equals("")){
			 msg = "Please provide city name";
		}
		else if ((city.length()<3) || (city.length()>45))
		{
			msg= "Your City Name must between 3 and 45 digits";
		}
			
		else if (!isTextOnlyAlphabet(city)){
				msg="City name must be Alphabets only";
		}
		
		return msg;
	}
	
	private String validateState(String state){
		String msg = "";
		
		if(state.equals("") || state.length()==0){
			msg = "Please provide state name";
		}
		else if(state.length()!= 2 ){
			msg = "Please provide only state intials";
		}
		else if (!isTextOnlyAlphabet(state)){
			msg="City name must be Alphabets only";
			}
		return msg;
	}
	
	private String validateZipcode(String zipcode){
		String msg ="";
		
		if(zipcode.equals("") || zipcode.length()==0){
			msg = "Please provide Zip code.";
		}
		else if (zipcode.length()!=5)
			msg="Zip code must be 5 digits in length";
		else if (!isTextAnInteger(zipcode)){
				msg="Zip code must be a number";
		}
		
		return msg;
	}
	
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
	
	private boolean isTextOnlyAlphabet (String str) 
    { 
        return ((str != null) 
                && (!str.equals("")) 
                && (str.matches("^[a-zA-Z]*$"))); 
    }
	
	private String validateSearch(String action, String search, String filter){
		String msg = "";
		if(action.equalsIgnoreCase("searchUser")){
			if(search.equals("") || search.length()==0){
				msg = "Please provide your search parameter";
			}
			else{
				if (filter.equals("utaid")){
					if (!isTextAnInteger(search))
						msg="UTA ID must be a number";
				}
				else {
					if (!isTextOnlyAlphabet(search))
						msg = "First name & Last name should only be alphabets";
				}
			}
		}
		return msg;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", role=" + role + ", utaid=" + utaid + ", phone=" + phone
				+ ", streetaddress=" + streetaddress + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", greetingText=" + greetingText + ", search=" + search + ", searchfilter=" + searchfilter + "]";
	}
	
		
}
