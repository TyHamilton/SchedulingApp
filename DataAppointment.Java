package backEndSchedule;

import java.util.ArrayList;

public class DataAppointment extends DataIncrement {
	private int appointmentId;
	private int customerId; 
	private String title; 
	private String description;
	private String location;
	private String contact;
	private String url;
	private String start;
	private String end;
	private String createDate;
	private String createdBy; 
	private String lastUpdate;
	private String lastUpdateBy;
	
	//UI information
	private String start1;
	private String appointList;
	private String stTime;
	private String enTime;
	private ArrayList<String> hoursOf = new ArrayList<>();
	private String reminder;
	private String end1;
	private boolean alerted= false;
	private String customerName;
	
	
	public DataAppointment(){
		this.title=" ";
	}
	
	//this saves from interface for new appointment
	public DataAppointment(int appointmentId,int customerId,String title,String description,String location,String contact,String url,String start,String createdBy, String endF ) {
		super();
		String apIDn=start.substring(0,3);
		String appFin = apIDn+customerId+SQLstub.appointment.size();
		int nAppID= Integer.parseInt(appFin);
		this.appointmentId = nAppID;
		this.customerId = customerId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.url = url;
		this.start = start;
		this.end = endF;
		this.createDate = DataTime.returnTime();
		this.createdBy = UIAssist.getUserL();
		this.lastUpdate = DataTime.returnTime();
		this.lastUpdateBy = UIAssist.getUserL();
		
	}
	//system save appointment
	public DataAppointment(int appointmentId,int customerId,String title,String description,String location,String contact,String url,String start,String createdBy, int a, String end ) {
		super();
		this.appointmentId = appointmentId;
		this.customerId = customerId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.url = url;
		this.start = start;
		this.end = end;
		this.createDate = DataTime.returnTime();
		this.createdBy = createdBy;
		this.lastUpdate = DataTime.returnTime();
		this.lastUpdateBy = UIAssist.getUserL();
	}
	
	
	DataAppointment(String appointmentId,String customerId,String title,String description,String location,String contact,String url,String start,String end,String createDate,String createdBy,String lastUpdate,String lastUpdatedBy){
		
		this.appointmentId = Integer.parseInt(appointmentId);
		this.customerId = Integer.parseInt(customerId);
		this.title = title;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.url = url;
		this.start = start;
		this.end = end;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.lastUpdate = lastUpdate;
		this.lastUpdateBy = lastUpdatedBy;
		
		
	}
	
	
	
	
	
	

	public void setTimes(){
		this.setStart1(start);
		this.setEnd1(end);
	}
	
	public String retApTime(){ //creates the appointment view for the calendar 
		try{


		String buff1=stTime;

		String buff2=enTime;
		String fin = customerId+"-\n"+buff1+"\n"+buff2+"\n------";
		return fin;
		}catch(Exception e){}
		return"";
		
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getAppointList() {
		return appointList;
	}

	public void setAppointList() {
		this.appointList = retApTime();
	}

	public String getStTime() {
		return stTime;
	}

	public void setStTime() {
		this.stTime =DataTime.strTotime(start1);
		System.out.println("Start time set to: "+stTime);
		
	}

	public String getEnTime() {
		return enTime;
	}

	public void setEnTime() {
		this.enTime =DataTime.strTotime(end1);
		System.out.println("End time set to: "+enTime);
	}

	public ArrayList<String> getHourOf() {
		return hoursOf;
	}

	public void setHourOf() {
		
		
		this.hoursOf = DataTime.fillHourOf(hoursOf, stTime, enTime);
	}

	public String getStart1() {
		return start1;
	}

	public void setStart1(String start1) {
		this.start1 = start1;
	}

	public String getEnd1() {
		return end1;
	}

	public void setEnd1(String end1) {
		this.end1 = end1;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder() {
		String buff1 =start1.substring(11);
		int hour = Integer.parseInt(buff1.substring(0, 2));
		hour=hour -1;
		String tenZ="";
		if(hour<10){tenZ="0";}
		
		String reminder1=tenZ+hour+":45:00"	;
		
		
		this.reminder = reminder1;
	}

	public boolean isAlerted() {
		return alerted;
	}

	public void setAlerted(boolean alerted) {
		this.alerted = alerted;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName() {
		this.customerName = SQLstub.getNameAp(customerId);
	}
}
