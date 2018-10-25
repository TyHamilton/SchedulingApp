package backEndSchedule;

public class DataDay {
	private String dateP ="";
	private String dayC="";
	private int appointments=0;
	private int dayINT=0;
	private int week=0;
	private int dayName=0;
	
	DataDay(){
		this.dateP="";
		this.dayC="";
		this.appointments=0;
		this.dayINT=0;
		System.out.println(this.dayINT);
	}
	
	DataDay(String a){
		this.dateP=a;
		this.dayC="";
		this.appointments=0;
		this.dayINT=0;
		System.out.println(this.dayINT);
	}
	
	DataDay(String a, String b, int c, int d, int e){
		this.dateP = a;
		
		this.appointments= SQLstub.getAppoints(a);
		this.dayC = b +"("+this.appointments+")";
		this.dayINT = c;
		this.week=d;
		this.dayName= e;
		//System.out.println(b);
	}
	
	public String getDateP() {
		return dateP;
	}
	public void setDateP(String dateP) {
		this.dateP = dateP;
	}
	public String getDayC() {
		return dayC;
	}
	public void setDayC(String dayC) {
		this.dayC = dayC;
	}

	public int getAppointments() {
		return appointments;
	}
	public void setAppointments(int appointments) {
		this.appointments = appointments;
	}

	public int getDayINT() {
		return dayINT;
	}

	public void setDayINT(int dayINT) {
		this.dayINT = dayINT;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getDayName() {
		return dayName;
	}

	public void setDayName(int dayName) {
		this.dayName = dayName;
	}
	
	
	
}
