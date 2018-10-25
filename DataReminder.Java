package backEndSchedule;



public class DataLogin {
	private String user ;
	private String timeStamp;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	DataLogin(String user){
		// new login 
		this.setUser(user);
		String temp = DataTime.returnTime();		
		this.setTimeStamp(temp);
		
		System.out.println(getTimeStamp());
		
		
	};
	
	DataLogin(String user, String date){
		//load login
		this.setUser(user);
		this.setTimeStamp(date);
		
		
	};
	
	
	
}
