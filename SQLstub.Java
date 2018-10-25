package backEndSchedule;

import java.util.ArrayList;

public class SQLstub {
	
	
	static ArrayList<DataAddress> address = new ArrayList<>();
	
	static ArrayList<DataAppointment> appointment = new ArrayList<>();	
	private static ArrayList<DataAppointment> appointmentRe = new ArrayList<>();
	
	static ArrayList<DataCity> city = new ArrayList<>();
	
	static ArrayList<DataCountry> country = new ArrayList<>();	
	
	static ArrayList<DataCustomer> customer = new ArrayList<>();
		
	static ArrayList<DataIncrement> increment = new ArrayList<>();
	
	static ArrayList<DataReminder> reminder = new ArrayList<>();
	
	static ArrayList<DataUser> users = new ArrayList<>();

	
	public static void userRep(String a,String c){
		System.out.println(a.substring(0,7).trim());
		System.out.println(c);
		for(DataAppointment b: appointment){
			System.out.println("Appointment month"+b.getStart().substring(0,7).trim());
			System.out.println(b.getCreatedBy());
			if(b.getStart().substring(0,7).trim().equals(a.substring(0,7).trim())){
			
				if(b.getCreatedBy().equals(c)){
					System.out.println("User Match");
				b.setCustomerName();
				Main.appointFull.add(b);
				}
			}
			
			
		}
		
	}
	
	public static void monRep(String a){
		System.out.println("______Month Report_____");
		System.out.println("Fed day"+a);
		for(DataAppointment b: appointment){
			System.out.println("Appointment month"+b.getStart().substring(0,7).trim());
			System.out.println(a.substring(0,7).trim());
			if(b.getStart().substring(0,7).trim().equals(a.substring(0,7).trim())){
				System.out.println("Day Match");
				b.setCustomerName();
				Main.appointMonth.add(b);
				
			}
			
			
		}
		
	}
	
	
	
	public static String getNameAp(int a){
		String name="";
		for(DataCustomer b: customer){
			if(b.getCustomerId()==a){
				name=b.getCustomerName();
			}
			
		}
		
		return name;
	}
	
	
	public static String getCityName(int a){
		for(DataCity b: city){
			System.out.println("City finder "+b.getCityId());
			if(b.getCityId()==a){
				System.out.println("Found city");
				return b.getCity();
			}
		}
		return "";
	}	
	
	
	
	public static int getCityID(String a){
		for(DataCity b: city){
			
			if(b.getCity().equals(a)){
				return b.getCityId();
			}
		}
		return 0;
	}
	
	
	public static int getCountry(String a){
		
		for(DataCountry b: country){
			if(b.equals(a)){
				
				return b.getCountryId();
			}
			
			
		}
			
			
		
		
		return 0;
	}
	public static String getCountry(int a){
		
		for(DataCountry b: country){
			if(b.getCountryId()==a){
				
				return b.getCountry();
			}
			
			
		}
			
			
		
		
		return "";
	}
	
	
	public static void fillTodayapp(){
		System.out.println(UIAssist.getUserL());
		String today=DataTime.returnTime().substring(0, 11);
		appointment.stream()
		.filter(s->s.getCreatedBy().equals(UIAssist.getUserL()))
		.filter(s->s.getStart().substring(0, 11).equals(today))
		.forEach(s->{
			s.setReminder();
			appointmentRe.add(s);
			System.out.println("Appointment added to watch");
		});
		
		
		
	}
	
	
	
	
	 public static void timeLAdjust(){
		 city.stream()
		 .forEach(s->{
			 if(s.getCity().equals(UIAssist.getLocationL())){
				 UIAssist.setTimeAdjust(s.getTimeMod());
				 UIAssist.setdST(s.isDst());
			 }
			 
			 
		 });
		 
		 
	 }
	
	
	
	public static void dayCheck(ArrayList<String> a, String b){
		System.out.println("Day check started= "+b.substring(0,11));
		appointment.stream()
		.filter(s->s.getStart().substring(0,11).equals(b.substring(0,11)))
		.forEach(s->{
		System.out.println("Time check = "+UIAssist.getCheckTime());
			DataTime.timeCheck( a, s.getHourOf());
			
			System.out.println("Time check = "+UIAssist.getCheckTime());
		});
		
		
		
	}
	
	public static void primeAppointmentTime(){
		appointment.stream()
		.forEach(s->{s.setAppointList();
		String st = UIAssist.correctTime(s.getStart());
		String en = UIAssist.correctTime(s.getEnd());
			s.setStart1(st);
			s.setEnd1(en);
			s.setEnTime();
			s.setStTime();
			s.setHourOf();
			s.setAppointList();
			s.setReminder();
			System.out.println(s.getReminder());
		});
		
	}	
	
	
	public static void primeAppointment(){
		appointment.stream()
		.forEach(s->{s.setAppointList();
			s.setEnTime();
			s.setStTime();
			s.setHourOf();
		});
		
	}
	
	

	public static void appointmentFilter(String a){
		System.out.println("appointmentFilrter: "+a);
		appointment.stream()
		.filter(s->s.getStart().substring(0, 10).equals(a))
		.forEach(s-> { //buff.add(s);
		int start = Integer.parseInt(s.getStart().substring(11, 13));
		int end = Integer.parseInt(s.getEnd().substring(11, 13));
		System.out.println("Appointment: "+s.getStart().substring(0, 10));
		UIAssist.setDaySelect(s);
		DataTime.groomHours(start, end);
		
		});
		
		
	
		
	}
	
	
	
	
public static void printApps(){
	for(DataAppointment a: appointment){
		System.out.println(a.getStart());
		
	}
	
}	
	
	public static boolean loginCheck(String user,String pass){
		for(DataUser d: users){
			if(d.getUserName().equals(user)){
				if(d.getPassword().equals(pass)){
					return true;
				}
			}
			
			
		}
		
		
		return false;
	}
	
	
	public void addUsers(int userID,
			String userName,
			String password,
			boolean active,
			String createBy,
			String lastUpdatedBy){
		
		DataUser temp = new DataUser(userID, userName, password, active, createBy, lastUpdatedBy);
		System.out.println(temp.getUserID());
		users.add(temp);
		
		
		
	};
	
	public void remUsers(int userID){
		
	};
	
	public void addReminder(){};
	public void remReminder(){};
	
	public void addIncrement() {};
	public void remIncrement() {};
	
	public void addCustomer() {};
	public void remCustomer () {};
	
	public void addCountry() {};
	public void remCountry() {};
	
	public void addCity() {};
	public void remCity() {};

	public void addAppointment() {};
	public void remAppointment() {};
	
	public void addAddress() {};
	public void remAddress() {};
	
	
	
	
	public static int getAppoints(String a){
//		System.out.println("Fetch day given"+ a);
		int counter=0;
		for(DataAppointment b: appointment){
//			System.out.println(b.getStart().substring(0,10));
			if(b.getStart().substring(0,11).trim().equals(a.trim())){
				System.out.println("Match found!");
				counter= counter + 1;
				
			}
			
			
		}
		
		
		return counter;
	}

	public static ArrayList<DataCity> getCity() {
		return city;
	}

	public static void setCity(ArrayList<DataCity> city) {
		SQLstub.city = city;
	}



	public static ArrayList<DataAppointment> getAppointmentRe() {
		return appointmentRe;
	}



	public static void setAppointmentRe(ArrayList<DataAppointment> appointmentRe) {
		SQLstub.appointmentRe = appointmentRe;
	}
	
	
	
	
	
	
}
