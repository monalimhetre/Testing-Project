package maintenance_system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import maintenance_system.data.MarSysDAO;
import maintenance_system.data.UserDAO;
import maintenance_system.model.MarSys;
import maintenance_system.model.User;
import maintenance_system.model.UserErrorMsgs;

@WebServlet(name="UserController", urlPatterns="/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String editUserUsername=null;
	private String userRole = null;
	private String username123 = null;
	private void getUserParam (HttpServletRequest request, User user) {
//		user.setUser(request.getParameter("username"),request.getParameter("firstName"),request.getParameter("lastName"),request.getParameter("email"), request.getParameter("password"), request.getParameter("role"),request.getParameter("utaid"),request.getParameter("phone"),request.getParameter("streetaddress"),request.getParameter("city"),request.getParameter("state"),request.getParameter("zipcode"),request.getParameter("vehiclenumber"),"1",request.getParameter("permit"));
		user.setUser(
				request.getParameter("username"),
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("role"),
				request.getParameter("utaid"),
				request.getParameter("phone"),
				request.getParameter("streetaddress"),
				request.getParameter("city"),
				request.getParameter("state"),
				request.getParameter("zipcode")
				);
		System.out.println("Set user request: "+user);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user = new User();
		UserErrorMsgs userErrorMsgs = new UserErrorMsgs();

		if (action.equalsIgnoreCase("saveUser") ) {  
			getUserParam(request, user);
			user.validateUser(action, user, userErrorMsgs);
			session.setAttribute("user", user);
			if(!userErrorMsgs.getErrorMsg().equals("")){
				getUserParam(request, user);
				session.setAttribute("errorMsgs", userErrorMsgs);
				url="/formUser.jsp";
			}
			else{
				UserDAO.insertUser(user);
				if (UserDAO.isDuplicate){
					userErrorMsgs.setUserNameError("Already Registered");
					getUserParam(request, user);
					session.setAttribute("errorMsgs", userErrorMsgs);
					url="/formUser.jsp";
				}
				else{
					session.invalidate();
					url="/index.jsp";
				}
			}
			
		}

		else if (action.equalsIgnoreCase("loginUser")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			getUserParam(request, user);
			user.validateUser(action, user, userErrorMsgs);
			session.setAttribute("user", user);
			if(!userErrorMsgs.getErrorMsg().equals("")){
				session.setAttribute("errorMsgs", userErrorMsgs);
				url="/login.jsp";
				  //url="listManager.jsp";
			}
			else {
				user = UserDAO.loginUser(username, password);
				if (user!=null){
					session.setAttribute("greetingField1", "Welcome Back, " + user.getUsername());
					
					editUserUsername=user.getUsername();
					userRole=user.getRole();
					username123 = user.getUsername();
					session.setAttribute("role", userRole);
					session.setAttribute("username", user.getUsername());
					System.out.println("user role: " + userRole);
					System.out.println("editUserUsername: " + editUserUsername);
					System.out.println("Set Role(param): "+ userRole);
					if(user.getRole().equals("USER"))
						url="/listUser.jsp";
					else if(user.getRole().equals("MANAGER"))
						url="/listManager.jsp";
					else if(user.getRole().equals("REPAIRER"))
						url="/listRepairer.jsp";
					else
						url="/listAdmin.jsp";
				}
				else{
					System.out.println("userController: "+ user);
					session.setAttribute("greetingField", "User Not Found!");
					url="/login.jsp";
				}
			}
		}
	
		else if (action.equalsIgnoreCase("logoutUser")){
			session.invalidate();
			url="/login.jsp";
		}
		
		else if (action.equalsIgnoreCase("searchUser") ) {
				String searchString = request.getParameter("search");
				String searchFilter = request.getParameter("searchFilter");
				user.setSearch(searchString);
				user.setSearchfilter(searchFilter);
				System.out.println("Search: " + user.getSearch()+ " Filter: "+ user.getSearchfilter());
				user.validateUser(action, user, userErrorMsgs);
				ArrayList<User> UserInDB = new ArrayList<User>();
				if(!userErrorMsgs.getErrorMsg().equals("")){
					session.setAttribute("errorMsgs", userErrorMsgs);
					url="/searchUser.jsp";
				}
				else {
					if (searchFilter.equals("firstName")){
						UserInDB=UserDAO.searchUserwithFname(searchString);
					}
					else if(searchFilter.equals("lastName")){
						UserInDB=UserDAO.searchUserwithLname(searchString);
					}
					else{
						UserInDB=UserDAO.searchUserwithUid(searchString);
					}
					session.setAttribute("USERS", UserInDB);
					session.setAttribute("searchMsg", UserInDB.size() + " Search Results Found!");
					session.setAttribute("role", userRole);
					System.out.println("Search with Role: "+ userRole);
					session.removeAttribute("errorMsgs");
					url="/searchUserList.jsp";
				}
			}
		else if (action.equalsIgnoreCase("searchUserToEditForAdmin") ) {
			String searchString = request.getParameter("search");
			String searchFilter = request.getParameter("searchFilter");
			user.setSearch(searchString);
			user.setSearchfilter(searchFilter);
			System.out.println("Search: " + user.getSearch()+ " Filter: "+ user.getSearchfilter());
			user.validateUser(action, user, userErrorMsgs);
			ArrayList<User> UserInDB = new ArrayList<User>();
			if(!userErrorMsgs.getErrorMsg().equals("")){
				session.setAttribute("errorMsgs", userErrorMsgs);
				url="/searchUserToEditForAdmin.jsp";
			}
			else {
				if (searchFilter.equals("firstName")){
					UserInDB=UserDAO.searchUserwithFnameOnlyUser(searchString);
				}
				else if(searchFilter.equals("lastName")){
					UserInDB=UserDAO.searchUserwithLnameOnlyUser(searchString);
				}
				else{
					UserInDB=UserDAO.searchUserwithUidOnlyUser(searchString);
				}
				session.setAttribute("USERS", UserInDB);
				session.setAttribute("searchMsg", UserInDB.size() + " Search Results Found!");
				session.setAttribute("role", userRole);
				System.out.println("Search with Role: "+ userRole);
				session.removeAttribute("errorMsgs");
				url="/searchUserListToEditUserForAdmin.jsp";
			}
		}
		else if(action.equalsIgnoreCase("userEditProfile")){
			System.out.println("User edit");
			request.getSession(true);
			//getUserParam(request, user);
			
			if(userRole.equalsIgnoreCase("user") || userRole.equalsIgnoreCase("manager") || userRole.equalsIgnoreCase("admin")){
				getUserParam(request, user);
			user.validateUser(action, user, userErrorMsgs);
				session.setAttribute("user", user);
				System.out.println("session param: "+editUserUsername);
				System.out.println("My error message new:"+userErrorMsgs.getErrorMsg().equals(""));
				if(!userErrorMsgs.getErrorMsg().equals("")){
					getUserParam(request, user);
					session.setAttribute("errorMsgs", userErrorMsgs);
					if (userRole.equalsIgnoreCase("user")) {
						user.setRole("user");
						session.setAttribute("role", "user");
						url = "/listUser.jsp";
					}
					else if (userRole.equalsIgnoreCase("manager"))
						url = "/listManager.jsp";
					else
						url = "/listAdmin.jsp";
				}
				else{
					System.out.println("update: "+user.getUsername());
					User updateUser = new User();
					
					updateUser = UserDAO.getUserByUsername(editUserUsername); 
					getUserParam(request, user);
					System.out.println("Param role******:"+request.getParameter("role"));
					System.out.println("User got role****:"+user.getRole());
					user.setRole("user");
					//updateUser.setRole("user");
					session.setAttribute("UPDATEUSER", updateUser);
					if (userRole.equalsIgnoreCase("user"))
						url = "/updateUserProfile.jsp";
					else if (userRole.equalsIgnoreCase("manager"))
						url = "/updateManagerProfile.jsp";
					else
						url = "/updateAdminProfile.jsp";
					//System.out.println(updateUser.getEmail());
				}
			}			
			
		}
		
else if(action.equalsIgnoreCase("managerEditUser")){
			
			if (request.getParameter("userSelected")!=null) {
				String userSelected = request.getParameter("userSelected");
				editUserUsername = userSelected;
				user.validateUser(action, user, userErrorMsgs);
				session.setAttribute("user", user);
				System.out.println("Selected User: "+userSelected);
				if(!userErrorMsgs.getErrorMsg().equals("")){
					session.setAttribute("errorMsgs", userErrorMsgs);
					url = "/searchUserList.jsp";
				}
				else {
					System.out.println("update: "+ user.getUsername());
					User updateUser = new User();
					updateUser = UserDAO.getUserByUsername(userSelected);
					session.setAttribute("UPDATEUSER", updateUser);
					url = "/updateUserRole.jsp";
				}
			}
			
			else{
				if (request.getParameter("ListUserButton")!=null) {
					String errorMsgs =  "Please select a User";
					session.setAttribute("searchMsg",errorMsgs);
					url="/searchUserList.jsp";
			}
		}
	}
		
//		else if(action.equalsIgnoreCase("managerEditUser")){
//			session.setAttribute("manageredit", "true");
//			String userSelected = request.getParameter("userSelected");
//			editUserUsername = userSelected;
//			user.validateUser(action, user, userErrorMsgs);
//			session.setAttribute("user", user);
//			System.out.println("Selected User: "+userSelected);
//			if(!userErrorMsgs.getErrorMsg().equals("")){
//				session.setAttribute("errorMsgs", userErrorMsgs);
//				url = "/searchUserList.jsp";
//			}
//			else{					
//				System.out.println("update: "+ user.getUsername());
//				User updateUser = new User();
//				System.out.println("Manager edit user function call");
//				System.out.println("User selected: "+userSelected);
//				updateUser = UserDAO.getUserByUsername(userSelected);
//				System.out.println("Update user "+ updateUser);
//				//System.out.println("Updated role***:"+request.getParameter("role"));
//				updateUser.setRole("manager");
//				session.setAttribute("role", "manager");
//				session.setAttribute("UPDATEUSER", updateUser);
//				//Test here for user role. Check role
//				//user.setRole("user");
//				//user.setRole(userRole);
//				if (userRole.equalsIgnoreCase("manager"))
//					url = "/updateManagerProfile.jsp";
//				else
//					url = "/updateAdminProfile.jsp";
//			}
//		}
		
		else if(action.equalsIgnoreCase("editUser")){
			String userSelected = request.getParameter("userSelected");
			editUserUsername = userSelected;
			user.validateUser(action, user, userErrorMsgs);
			session.setAttribute("user", user);
			System.out.println("Selected User: "+userSelected);
			if(!userErrorMsgs.getErrorMsg().equals("")){
				session.setAttribute("errorMsgs", userErrorMsgs);
				url = "/searchUserListToEditUserForAdmin.jsp";
			}
			else{					
				System.out.println("update: "+ user.getUsername());
				User updateUser = new User();
				updateUser = UserDAO.getUserByUsername(userSelected);
				session.setAttribute("UPDATEUSER", updateUser);
				url = "/updateWholeUserProfile.jsp";
			}
		}
		
		else if (action.equalsIgnoreCase("updateUserInDB")){
//			getUserParam(request, user);
//			System.out.println("My update user "+ user.getUsername());
//			user.validateUser(action, user, userErrorMsgs);
			User updateUser = (User) session.getAttribute("UPDATEUSER");
			//userErrorMsgs.setErrorMsg("");
			getUserParam(request, updateUser);
			updateUser.setRole(session.getAttribute("role").toString());
			if(session.getAttribute("manageredit")==null || !session.getAttribute("manageredit").equals("true")) {
				updateUser = UserDAO.getUserByUsername(updateUser.getUsername());
				updateUser.validateUser(action, updateUser, userErrorMsgs);
			}
			session.setAttribute("user", updateUser);
			System.out.println("Got Error: "+userErrorMsgs.getErrorMsg());
			if(!userErrorMsgs.getErrorMsg().equals("")){
				System.out.println("user error");
				session.setAttribute("errorMsgs", userErrorMsgs);
				if (userRole.equalsIgnoreCase("user"))
					url="/updateUserProfile.jsp";
				else if (userRole.equalsIgnoreCase("manager"))
					url="/updateManagerProfile.jsp";
				else
					url="/updateAdminProfile.jsp";
			}
			else{
				System.out.println("My user here: "+user);
				System.out.println("Update user here: "+updateUser);
				System.out.println("Updated role***:"+request.getParameter("role"));
				if(request.getParameter("role") != null) {
					updateUser.setRole(request.getParameter("role"));
				} else {
					getUserParam(request, updateUser);
					updateUser.setRole(session.getAttribute("role").toString());
				}
				UserDAO.updateUserDetails(updateUser, editUserUsername);
				if (userRole.equalsIgnoreCase("user"))
					url = "/listUser.jsp";
				else if (userRole.equalsIgnoreCase("manager"))
					url = "/listManager.jsp";
				else
					url = "/listAdmin.jsp";
				//System.out.println("update query");
			}
			
		}
		
		else if (action.equalsIgnoreCase("updateUserInDBToEditUserProfileForAdmin")){
			getUserParam(request, user);
			user.validateUser(action, user, userErrorMsgs);
			System.out.println("New to be updated error: "+userErrorMsgs.getErrorMsg());
			session.setAttribute("user", user);
			System.out.println("New to be"+user);
			System.out.println("New to be updated error: "+userErrorMsgs.getErrorMsg());
			if(!userErrorMsgs.getErrorMsg().equals("")){
				getUserParam(request, user);
				session.setAttribute("errorMsgs", userErrorMsgs);
				url="/updateWholeUserProfile.jsp";
			}
			else{
				UserDAO.updateUserDetailsForAdmin(user, editUserUsername);
				url = "/listAdmin.jsp";
				System.out.println("update query");
			}
		}
		
	else if (action.equalsIgnoreCase("searchRepairerToBeAssigned") ) {
			
			ArrayList<User> RepInDB = new ArrayList<User>();
			
//				Mar mar = new Mar();
					RepInDB=UserDAO.searchRepToBeAssigned();
				
				session.setAttribute("REPAIRER", RepInDB);
				System.out.println(RepInDB);
				session.setAttribute("searchMsg", RepInDB.size() + " Search Results Found!");
				url="/repairerList.jsp";
//				String marSelected = request.getParameter("marSelected");
		}
		
		
		else if(action.equalsIgnoreCase("assignMarToTheSelectedRepairer")){
			String repSelected = request.getParameter("repSelected");
			String marSelected = request.getParameter("marSelected");
			String date =request.getParameter("date");
			String estimatetime =request.getParameter("estimatetime");
			String urgency =request.getParameter("newurgency");
			System.out.println(repSelected);
			System.out.println(marSelected);
	
			MarSys updateMar = new MarSys();
			MarSysDAO.getMarByMarNumber(marSelected, repSelected, date, estimatetime, urgency); 
			session.setAttribute("UPDATEMAR", updateMar);
			System.out.println(updateMar);
				url = "/listManager.jsp";
		}
		
	

		else if(action.equalsIgnoreCase("searchMarAndRepairer")){
			ArrayList<MarSys> MarSysInDB = new ArrayList<MarSys>();
			
			MarSysInDB=MarSysDAO.searchMAR();
		
		session.setAttribute("MARS", MarSysInDB);
		session.setAttribute("searchMsg", MarSysInDB.size() + " Search Results Found!");
//		url="/searchMARList.jsp";
		
		ArrayList<User> RepInDB = new ArrayList<User>();
		
			RepInDB=UserDAO.searchRepToBeAssigned();
		
		session.setAttribute("REPAIRER", RepInDB);
		session.setAttribute("searchMsg", RepInDB.size() + " Search Results Found!");
		url="/searchMARList.jsp";
		}
/////////////////////assigned/////////////////////////////////////////////////////////////	
		
		
		
		else if(action.equalsIgnoreCase("searchMarAndAssignedRepairer")){
			ArrayList<MarSys> MarSysInDB = new ArrayList<MarSys>();
			
			MarSysInDB=MarSysDAO.searchAssignedMAR();
			System.out.println("marsys****\n"+MarSysInDB);
		
		session.setAttribute("MARSELECTED", MarSysInDB);
		session.setAttribute("searchNewMsg", MarSysInDB.size() + " Search Results Found!");
//		url="/searchMARList.jsp";
		
		ArrayList<User> RepInDB = new ArrayList<User>();
		
			RepInDB=UserDAO.searchRepToBeAssigned();
		
		session.setAttribute("REPAIRER", RepInDB);
		session.setAttribute("searchMsg", RepInDB.size() + " Search Results Found!");
		url="/searchAssignedMarList.jsp";
		}
		
		
		
		
		
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}

}
