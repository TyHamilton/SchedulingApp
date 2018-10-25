package backEndSchedule;

public class DataUser {
	private int userID;
	private String userName;
	private String password;
	private boolean active;
	private String createBy;
	private String createDate; 
	private String lastUpdate;
	private String lastUpdatedBy;
	
	
	
	
	public DataUser( String userName, String password, boolean a ) {//save new
		
		
		
		this.userID = SQLstub.users.size()+1;
		this.userName = userName;
		this.password = password;
		this.active = a;
		this.createBy = UIAssist.getUserL();
		this.createDate = DataTime.returnTime();
		this.lastUpdate = DataTime.returnTime();
		this.lastUpdatedBy = UIAssist.getUserL();
		
		System.out.println(this.getUserID());
	}
	
	
	
	public DataUser(int userID, String userName, String password, boolean a, String createBy, 
			 String lastUpdatedBy) {//System created for admin
		
		
		
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.active = a;
		this.createBy = createBy;
		String temp = DataTime.returnTime();
		this.createDate = temp;
		this.lastUpdate = "Never";
		this.lastUpdatedBy = lastUpdatedBy;
		
		System.out.println(this.getUserID());
	}
	
	
	DataUser(String userId,String userName,String password,String active,String createBy,String createDate,String lastUpdate,String lastUpdatedBy){//sql load
		boolean buff=false;
		if(active.charAt(0)=='1'){buff=true;}
		
		this.userID=Integer.parseInt(userId);
		this.userName=userName;
		this.password=password;
		this.active=buff;
		this.createBy=createBy;
		this.createDate=createDate; 
		this.lastUpdate=lastUpdate;
		this.lastUpdatedBy=lastUpdatedBy;
		
		
		
	}
	
	
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
