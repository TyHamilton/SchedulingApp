package backEndSchedule;



public class UIAssist {
    /*
     * Start Data cache for UI
     */
	
	static private  Main theMain; 
	static private boolean userNew=false;
	static private DataUser selectedUser;
	
     static private boolean cCheck = false;
	 static private boolean isClose = false;
	 
	 static private DataCustomer slectCust;
	 static private boolean didILoad = false;
	 static private String userL="admin";
	 
	 static private DataUser selectUser;
	 static private String buffUser;
	 
	 static private DataAppointment appSelect;
	 static private int appDay=0;
	 
	 static private boolean isSpanish=false;
	 
	 static private String locationL="Pheonix, AZ";
	 
	 static private boolean dST=false;
	 
	 static private int timeAdjust =0;
	 
	 static private boolean timeClick=false;
	 
	 static private DataAppointment daySelect;
	 
	 static private String datePick="";
	 
	 static private int checkTime =0;
	 
	 static private boolean showAlert=false;
	 
	 static private String alertText="";
	 
	 
	 
	 
	 public static String correctTime(String a){
		 System.out.println("correctTime"+a);
		 int time =Integer.parseInt( a.substring(11,13));
		 System.out.println("Time is :"+time);
		 int mod = UIAssist.timeAdjust;
		 System.out.println("Time: "+time);
		 System.out.println("Mod: "+mod);
		 String front=a.substring(0,11);
		 String back=a.substring(13);
		 if(time==0){time=24;}
		 mod+=time;
		 String tenZ="";
		 if(mod<10){tenZ="0";}
		 String finTime= front+tenZ+mod+back;
		 
		 System.out.println("Time changed from: "+a+" to: "+finTime);
		 
		 
		 return finTime;
	 }

	 public static int timeModFlip(){
		 
		 int check =timeAdjust;
		 String neg="-";
		 String temp = "";
		 if(check>0){
			 temp=Integer.toString(check);
			 check = Integer.parseInt(neg+temp);
		 }else{
			 temp=Integer.toString(check);
			 check=Integer.parseInt(temp.substring(1,temp.length()));
			 
		 }
		 
		 
		return check; 
	 }
	 

	public static String rePhone(long a){
		long lChang= a;
		
		String toNum = Long.toString(lChang);
//		System.out.println("Total number sent to rePhone"+toNum);
		String pre = toNum.substring(0, 3);
//		System.out.println("Pre :"+pre);
		String post = toNum.substring(3,6);
//		System.out.println("Post :"+post);
		String post1 = toNum.substring(6);
//		System.out.println("Post final:"+post1);
		
		String conv = pre+post+"-"+post1;
		
		
		return conv;
	}
	 
	 
	 public static boolean isClose() {
			return isClose;
		}


		public static void setClose(boolean isClose) {
			UIAssist.isClose = isClose;
		}
		//User data

	
	public static long phoneMaker(String a, String b){
//		System.out.println("String fed a:"+a);
//		System.out.println("String fed b"+b);
		String cPrefix = a;
		String cPhone="";
		String temp ="";
		Long fullPhone = -1l;
		
		if(b.charAt(3)=='-'){
			String front = b.substring(0,3);
			String back = b.substring(4);
			cPhone = front+back;
			System.out.println(cPhone);
		}else{
			cPhone = b;
		}
		temp = cPrefix+cPhone;
		for(int i2=0; i2 < temp.length();i2++){
			char at =temp.charAt(i2);
			
			
			if(Character.isDigit(at)){
				
				//System.out.println("Phone digit: "+at);
				
			}else{
				System.out.println("Digit not digit");
				return -1;
			}
			}
		
		
		
		fullPhone = Long.parseLong(temp);
		
//		System.out.println("Phone sent :"+fullPhone);
	return fullPhone;
	}
	
	
	
	
	public static int getNewID(String b){
		int a =SQLstub.customer.size()+1;
	
//		int d = a+c;
		return a;
	}
	
	public static void countryCheck(String a, String b){
		int i =0;
		int j=0;
		int check1=0;
		int check2=1;
		
		for(;i<SQLstub.city.size();i++){
			if(a.equals(SQLstub.city.get(i).getCity())){
			
			check1 = SQLstub.city.get(i).getCountryId();
			
			System.out.println("Match city: "+ SQLstub.city.get(i).getCity());
			}
		}
		
		for (;j<SQLstub.country.size();j++){
			if(b.equals(SQLstub.country.get(j).getCountry())){
				
				check2= SQLstub.country.get(j).getCountryId();
				
				System.out.println("Match Country: "+SQLstub.country.get(j).getCountry());
			}
			
		}
		if(check1==check2){
			cCheck=true;
			System.out.println("True match");
			
		}else{
			System.out.println("No match");
		}
		
	}


	public static boolean iscCheck() {
		return cCheck;
	}


	public static void setcCheck(boolean cCheck) {
		UIAssist.cCheck = cCheck;
	}


	public static DataCustomer getSlectCust() {
		return slectCust;
	}


	public static void setSlectCust(DataCustomer slectCust) {
		UIAssist.slectCust = slectCust;
	}


	public static DataUser getSelectUser() {
		return selectUser;
	}


	public static void setSelectUser(DataUser selectUser) {
		UIAssist.selectUser = selectUser;
	}


	public static boolean isDidILoad() {
		return didILoad;
	}


	public static void setDidILoad(boolean didILoad) {
		UIAssist.didILoad = didILoad;
	}


	public static String getUserL() {
		return userL;
	}


	public static void setUserL(String userL) {
		UIAssist.userL = userL;
	}


	public static DataAppointment getAppSelect() {
		return appSelect;
	}


	public static void setAppSelect(DataAppointment appSelect) {
		UIAssist.appSelect = appSelect;
	}


	public static int getAppDay() {
		return appDay;
	}


	public static void setAppDay(int appDay) {
		UIAssist.appDay = appDay;
	}


	public static boolean isUserNew() {
		return userNew;
	}


	public static void setUserNew(boolean userNew) {
		UIAssist.userNew = userNew;
	}


	public static DataUser getSelectedUser() {
		return selectedUser;
	}


	public static void setSelectedUser(DataUser selectedUser) {
		UIAssist.selectedUser = selectedUser;
	}


	public static boolean isSpanish() {
		return isSpanish;
	}


	public static void setSpanish(boolean isSpanish) {
		UIAssist.isSpanish = isSpanish;
	}


	public static String getLocationL() {
		return locationL;
	}


	public static void setLocationL(String locationL) {
		UIAssist.locationL = locationL;
	}


	public static int getTimeAdjust() {
		return timeAdjust;
	}


	public static void setTimeAdjust(int timeAdjust) {
		UIAssist.timeAdjust = timeAdjust;
	}


	public static boolean isTimeClick() {
		return timeClick;
	}


	public static void setTimeClick(boolean timeClick) {
		UIAssist.timeClick = timeClick;
	}


	public static DataAppointment getDaySelect() {
		return daySelect;
	}


	public static void setDaySelect(DataAppointment daySelect) {
		UIAssist.daySelect = daySelect;
	}


	public static String getDatePick() {
		return datePick;
	}


	public static void setDatePick(String datePick) {
		UIAssist.datePick = datePick;
	}


	public static int getCheckTime() {
		return checkTime;
	}


	public static void setCheckTime(int checkTime) {
		UIAssist.checkTime = checkTime;
	}


	public static boolean isdST() {
		return dST;
	}


	public static void setdST(boolean dST) {
		UIAssist.dST = dST;
	}

	public static boolean isShowAlert() {
		return showAlert;
	}

	public static void setShowAlert(boolean showAlert) {
		UIAssist.showAlert = showAlert;
	}

	public static String getAlertText() {
		return alertText;
	}

	public static void setAlertText(String alertText) {
		UIAssist.alertText = alertText;
	}

	public static String getBuffUser() {
		return buffUser;
	}

	public static void setBuffUser(String string) {
		UIAssist.buffUser = string;
	}

	public static Main getTheMain() {
		return theMain;
	}

	public static void setTheMain(Main theMain) {
		UIAssist.theMain = theMain;
	}
	
	
	
	
	
}
