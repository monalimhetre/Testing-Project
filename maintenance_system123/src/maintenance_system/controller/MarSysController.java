package maintenance_system.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import maintenance_system.data.MarSysDAO;
import maintenance_system.data.UserDAO;
import maintenance_system.model.MarSys;
import maintenance_system.model.MarSysErrorMsgs;
import maintenance_system.model.User;


@WebServlet("/MarSysController")
public class MarSysController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String editMarSysNumber=null;
	private String username=null;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date = new Date();
	private void getMarSysParam (HttpServletRequest request, MarSys mar_sys) {
		mar_sys.setMarSys(
				request.getParameter("facility_type"),
				request.getParameter("facility_name"),
				request.getParameter("urgency"),
				request.getParameter("description"),
				request.getParameter("reported_by"),
				request.getParameter("date_value"),
				request.getParameter("mar_number"),
				request.getParameter("assignedTo"),
				request.getParameter("assignedDate"),
				request.getParameter("estimate")
				);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="",username = request.getParameter("username");
		HttpSession session = request.getSession();
		
		session.setAttribute("username", username);
		
		MarSys mar_sys = new MarSys();
		
		MarSysErrorMsgs mar_sysErrorMsgs = new MarSysErrorMsgs();

		if (action.equalsIgnoreCase("createMar") ) {  
			getMarSysParam(request, mar_sys);
			if(action == null || mar_sys == null ) {
				System.out.println("Nulls");
			}
			System.out.println(action +" "+ mar_sys.toString());
			mar_sys.validateMarSys(action, mar_sys, mar_sysErrorMsgs);
			session.setAttribute("mar_sys", mar_sys);
			//System.out.println(mar_sysErrorMsgs.getDate_valueError() +" " + mar_sysErrorMsgs.getDescriptionError()+" "+ mar_sysErrorMsgs.getFacility_nameError()+" "+ mar_sysErrorMsgs.getMar_numberError()+" "+ mar_sysErrorMsgs.getUrgencyError()+" "+ mar_sysErrorMsgs.getFacility_typeError()+ " "+ mar_sysErrorMsgs.getReported_byError());
			System.out.println(mar_sysErrorMsgs.getMErrorMsg());
			if(!mar_sysErrorMsgs.getMErrorMsg().equals("")){
				getMarSysParam(request, mar_sys);
				
				session.setAttribute("m_errorMsgs", mar_sysErrorMsgs);
				url="/createMAR.jsp";
			}
			else{
				System.out.println(username);
				MarSysDAO.insertMAR_SYS(mar_sys);
				if (MarSysDAO.isDuplicate){
					mar_sysErrorMsgs.setFacility_typeError("Already Registered");
					getMarSysParam(request, mar_sys);
					session.setAttribute("m_errorMsgs", mar_sysErrorMsgs);
					url="/formUser.jsp";
				}
				else{
					session.invalidate();
					url="/listUser.jsp";
				}
			}
			
		}

	
		else if (action.equalsIgnoreCase("searchMar") ) {	
		ArrayList<MarSys> MarSysInDB = new ArrayList<MarSys>();
		
			
				MarSysInDB=MarSysDAO.searchMAR();
			
			session.setAttribute("MARS", MarSysInDB);
			session.setAttribute("searchMsg", MarSysInDB.size() + " Search Results Found!");
			url="/searchMARList.jsp";
	}
	
	else if(action.equalsIgnoreCase("assignMarToTheSelectedRepairer")){
		String repSelected = request.getParameter("repSelected");
		String marSelected = request.getParameter("marSelected");
		String mydate =request.getParameter("mydate");
		String estimatetime =request.getParameter("estimatetime");
		String urgency =request.getParameter("newurgency");
		System.out.println(repSelected);
		System.out.println(marSelected);
        
		MarSys updateMar = new MarSys();
		MarSysDAO.getMarByMarNumber(marSelected, repSelected, mydate, estimatetime, urgency); 

			url = "/listManager.jsp";
	}
		
	else if (action.equalsIgnoreCase("searchAssignedMar") ) {	
	ArrayList<MarSys> MarSysInDB = new ArrayList<MarSys>();
	
		
			MarSysInDB=MarSysDAO.searchAssignedMAR();
		
		session.setAttribute("MARSELECTED", MarSysInDB);
		session.setAttribute("searchNewMsg", MarSysInDB.size() + " Search Results Found!");
		url="/searchAssignedMarList.jsp";
}

////////////////////////new code/////////////////////////////////////////////
		
	else if(action.equalsIgnoreCase("mar_sysEditProfile")){
		System.out.println("Mar edit");
		
			getMarSysParam(request, mar_sys);
			session.setAttribute("mar_sys", mar_sys);
			
				MarSys updateMarSys = new MarSys();
				editMarSysNumber=mar_sys.getMar_number();
				updateMarSys = MarSysDAO.getMarSysByMarNumber(editMarSysNumber); 
				getMarSysParam(request, mar_sys);
				session.setAttribute("UPDATEMAR", updateMarSys);
					url = "/login.jsp";
	
		}			
		
	
	
	///////////////////////new update/////////////////////////////////
	
	
	else if (action.equalsIgnoreCase("updateMarInDB")){
		String myMar = request.getParameter("marSelected");
		System.out.println("myMar: "+ myMar);
		String repairer1 = request.getParameter("repairer"+myMar);
		String date1 = request.getParameter("mydate"+myMar);
		String time1 = request.getParameter("estimatetime"+myMar);
		String urgency1 = request.getParameter("newurgency"+myMar);
		
		MarSys my_mar_sys = MarSysDAO.getMarSysByMarNumber(myMar);
		System.out.println("my mar sys:"+my_mar_sys.getMar_number());
		my_mar_sys.setAssignedDate(sdf.format(date));
		my_mar_sys.setAssignedTo(repairer1);
		my_mar_sys.setEstimate(time1);
		my_mar_sys.setUrgency(urgency1);
		
		MarSysDAO.updateMarAssignedRepairer(my_mar_sys);
				url = "/listManager.jsp";

	}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}

}
