package backEndSchedule;

public class DataHours {
	private String fullDay;
	private String hours;
	private int hour;
	private String titleH;
	
	DataHours(String a, String b,int c, String d){
			this.fullDay=a;
			this.hours=b;
			this.hour=c;
			this.setTitleH(d);
		}
	
	
	public String getFullDay() {
		return fullDay;
	}
	public void setFullDay(String fullDay) {
		this.fullDay = fullDay;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}


	public String getTitleH() {
		return titleH;
	}


	public void setTitleH(String titleH) {
		this.titleH = titleH;
	}
	
	
	
}
