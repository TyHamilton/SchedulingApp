package backEndSchedule;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DataTime {
	private static Calendar cal = Calendar.getInstance();
	private static Calendar cal2 = Calendar.getInstance();
	public final String[] months = {"", "January","February", "April", "May", "June", "June", "July", "August", "September", "October", "November", "December"};
	public final static int[] lastDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	static private DataDay[][] calDates = new DataDay[6][7];
	static private int finalDay = 0;
	private static DataDay theDayis;
	private final static String[] hourName= {"12:00 AM","01:00 AM","02:00 AM","03:00 AM","04:00 AM","05:00 AM","06:00 AM","07:00 AM","08:00 AM","09:00 AM","10:00 AM","11:00 AM","12:00 PM","01:00 PM","02:00 PM","03:00 PM","04:00 PM","05:00 PM","06:00 PM","07:00 PM","08:00 PM","09:00 PM","10:00 PM","11:00 PM"};
	
	
	public static ArrayList<DataHours> hoursAp = new ArrayList<>();

	public static ArrayList<DataDay> calBuff = new ArrayList<>();
	
	public static ArrayList<Integer> buff = new ArrayList<>();

	public static ArrayList<String> buff2 = new ArrayList<>();
	
	public static void cal2Bump(){
		cal2.add(Calendar.HOUR_OF_DAY,UIAssist.timeModFlip());
		
	}
	
	public static String rightNow(){
		
		
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		String timeIsh = time.format(cal2.getTime()).toString();
		
		return timeIsh;
	}
	
	
	
	public static void timeCheck(ArrayList<String> a, ArrayList<String> b){
		
		for(int i =0; i < a.size();i++){
			System.out.println("__________________________________");
			for(int j=0; j<b.size();j++){
				System.out.println("Check new: "+a.get(i)+" VS "+b.get(j));
				
				if(a.get(i).equals(b.get(j))){
					System.out.println("Found: "+a.get(i)+" VS "+b.get(j));
					UIAssist.setCheckTime(-1);
				}
			}
			
		}
		
		
		
	}
	
	public static ArrayList<String> fillHourOf(ArrayList<String> a, String b, String c){
		buff2.clear();
		
		System.out.println(c);
		int timeStar=0;
		int timeFin=0;
		System.out.println("Hours of: ");
		for(int i = 0; i<24;i++){
			buff2.add(hourName[i]);
			if(hourName[i].equals(b)){
				timeStar=i;
				System.out.println("Start time: "+i);
			}else if(hourName[i].equals(c)){
				timeFin=i;
				System.out.println("End time: "+i);
				
			}
			
		}
		
		
	
		
		for(int j=0; j<24;j++){
//			System.out.println("Start"+timeStar +" VS j "+j+" VS "+"End"+timeFin);
			if(j>= timeStar ){
				System.out.println(j);
				a.add(hourName[j]);
				System.out.println("Time added to appointment " + hourName[j]);
				if(j>timeFin-2){
					System.out.println("break");
					break;}
			}
			

			
		}
		return a;
	}
	
	
	
	public static String strTotime(String a){
	
		int check = Integer.parseInt(a.substring(11,13));
		System.out.println("strToime"+check);
		
		String theHour = hourName[check];
		return theHour;
		
		
	}
	
	
	public static void groomHours(int a, int b){//is fed hours as ints then searches for them in hours and renames the title  turned off
//		System.out.println("Start :"+a+"End :"+b);
//		int checked=a;
//		buff.clear();
//		int fix=b-1;
//		
//			buff.add(a);
//		for(int i=0; i<=fix-a;i++){
//			checked= checked+1;
//			buff.add(checked);
//
//		}
//		try{
//		for(int j=0; j<buff.size();j++){
//
//			if(hoursAp.get(buff.get(j)).getHour()==buff.get(j)){
//				if(hoursAp.get(buff.get(j)).getHour()==b){return;}
//
//				String tmp = hoursAp.get(buff.get(j)).getTitleH();
//				String tmp2 =tmp+"-"+UIAssist.getDaySelect().getAppointmentId();
//				System.out.println(tmp2);
//				hoursAp.get(buff.get(j)).setTitleH(tmp2);
//
//				
//				
//			}
//			
//		}
//		}catch(Exception e){
//			System.out.println(e);
//		}
//			
	}
	
	
	public static void fillHours(String a ){
		hoursAp.clear();
		String dayZ="";
		System.out.println(a);
		if(a.length()>10){dayZ= a.substring(0,11);}else{dayZ= a.substring(0);}
		
		String blanks = ":00:00.000";
		String tenZ ="";
		for(int i=0; i<24;i++ ){
			if(i<10){tenZ="0";}else{tenZ="";}
			String hourN=hourName[i];
			String hourF= tenZ+Integer.toString(i);
			String fHour = hourF+blanks;
			String fDay = dayZ+fHour;
			
			DataHours added = new DataHours(fDay, fHour,i, hourN);
			
			hoursAp.add(added);
		}
		
		
	}
	
	public static String getApStamp(String a, String b){	
		try{
		System.out.println("Collected "+a);
		System.out.println("Colected "+b);
		
		int hourN= 0;
		for(int i = 0; i<hourName.length;i++){
			if(b==hourName[i]){
				hourN=i;
			}
		}
		System.out.println("Time gues= "+ hourN);
		int mod = UIAssist.timeModFlip();
		System.out.println("Saving with: "+mod);
		String blanks = ":00:00.000";
		String tenZ ="";
		
		int time= mod+hourN;
		if(time<10){tenZ="0";}else{tenZ="";}
		if(time==24){time=0;
		tenZ="00";}
		
		String theR= a+" " + tenZ+time+blanks;
		System.out.println("time sent: "+theR);
		return theR;
		}catch(Exception ex){
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			sw.toString();
			System.out.println(sw);
		}
		return "err";
	}	
	
	
	
	public static void setTheDayis(String f){

		calBuff.stream()
		.filter(s-> s.getDateP().substring(0,10).equals(f))
		.forEach(s->theDaySet(s));
		
	}
	
	public static void theDaySet(DataDay a){
		theDayis=a;
		
	}
	
	public static void getAppD(String day){//finds today to use its week to fill the appointments for the week
		  ArrayList<DataAppointment> buffed = new ArrayList<>();
		SQLstub.appointment.stream()
		.filter(s->s.getCreatedBy().equals(UIAssist.getUserL()))
		.forEach(s->{
			
			if(s.getCreatedBy().equals(UIAssist.getUserL())){buffed.add(s);}
			System.out.println(s.getCreatedBy());
		
		});
		
		
		String fed = day.substring(0,10);
		System.out.println("Fed: "+fed);
		int theWeek=theDayis.getWeek();

		
		String mon;
		String tues;
		String wed;
		String thurs;
		String fri;
		String sat;
		String sun;
	
		
		try{
//		SQLstub.primeAppointment();
		DataAppointment none = new DataAppointment();
		System.out.println(theWeek);
		if(calDates[theWeek][0]==null){
		 mon =" "; 
		 Main.mon1OList.add(none);
		}else{ mon=calDates[theWeek][0].getDateP();}
		
		if(calDates[theWeek][1]==null){
			tues =" ";
			 Main.tue1OList.add(none);
			}else{ tues=calDates[theWeek][1].getDateP();}
		
		if(calDates[theWeek][2]==null){
			wed =" ";
			 Main.wen1OList.add(none);
			}else{ wed=calDates[theWeek][2].getDateP();}
		
		if(calDates[theWeek][3]==null){
			thurs =" ";
			 Main.thu1OList.add(none);
			}else{ thurs=calDates[theWeek][3].getDateP();}
		
		if(calDates[theWeek][4]==null){
			fri =" ";
			 Main.fri1OList.add(none);
			}else{ fri=calDates[theWeek][4].getDateP();}
		
		if(calDates[theWeek][5]==null){
			sat =" ";
			 Main.sat1OList.add(none);
			}else{ sat=calDates[theWeek][5].getDateP();}
		
		if(calDates[theWeek][6]==null){
			sun =" ";
			 Main.sun1OList.add(none);
			}else{ sun=calDates[theWeek][6].getDateP();}
		
			
			try{

		System.out.println("String is long: "+ fed.length());
			
			for(int i=0; i<buffed.size();i++){
				DataAppointment ca= buffed.get(i);
				String c =ca.getStart().substring(0,10);;
//				System.out.println("buffed size "+buffed.size());
//				System.out.println(c);
					if(c.equals(mon)){
						Main.mon1OList.add(ca);
//						System.out.println(ca.getStart()+"Mon");
					}
					if(c.equals(tues)){
						Main.tue1OList.add(ca);
//						System.out.println(ca.getStart()+"Tues");	
					}
					if(c.equals(wed)){
						Main.wen1OList.add(ca);
//						System.out.println(ca.getStart()+"Wed");	
					}
					if(c.equals(thurs)){
						Main.thu1OList.add(ca);
//						System.out.println(ca.getStart()+"Thur");	
					}
					if(c.equals(fri)){
						Main.fri1OList.add(ca);
//						System.out.println(ca.getStart()+"Fri");
						}
					if(c.equals(sat)){
						Main.sat1OList.add(ca);
//						System.out.println(ca.getStart()+"Sat");
						}
					if(c.equals(sun)){
						Main.sun1OList.add(ca);
//						System.out.println(ca.getStart()+"Sun");
						}
					

				
				
		}
		
		//end of 
			if(Main.mon1OList.size()<1){Main.mon1OList.add(none);}
			if(Main.tue1OList.size()<1){Main.tue1OList.add(none);}
			if(Main.wen1OList.size()<1){Main.wen1OList.add(none);}
			if(Main.thu1OList.size()<1){Main.thu1OList.add(none);}
			if(Main.fri1OList.size()<1){Main.fri1OList.add(none);}
			if(Main.sat1OList.size()<1){Main.sat1OList.add(none);}
			if(Main.sun1OList.size()<1){Main.sun1OList.add(none);}
		
			}catch(Exception e){
				System.out.println(e);
			}
		}catch(Exception d){
//			System.out.println("No appointments");
			
		}
	}
	
	
	
	public static DataDay[][] getCalDates() {
		return calDates;
	}

	public static void setCalDates(DataDay[][] calDates) {
		DataTime.calDates = calDates;
	}

	public static void printCal(){
		boolean prints = true;
		while(prints){
		for(int i=0; i<6;i++){
			if(prints == false){break;}
			for(int j=0; j<7;j++){
				if(calDates[i][j].getDayC().equals("")){
					
					System.out.print("  | ");
				}else if(calDates[i][j] != null){
					calBuff.add(calDates[i][j]);
					System.out.print(calDates[i][j].getDayC()+ " | ");
					//System.out.println(finalDay);
					if(calDates[i][j].getDayINT()==finalDay){
						
						prints=false;
						break;}
				}
				
			}
			System.out.println("");
		}
		}
		
	}
	
	public static void buildCalToday(){
		String time = returnTime().toString();
//		System.out.println("Cal build requested today:" + time);
		buildCal(time);
		
	}
	
	public static int leapCheck(int year, int month){
		int dDay=0;
		boolean leapYear =((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
		if(leapYear){
		if(month == 2){
			return 29;
			}else {
				dDay=lastDay[month];
				return dDay;
			}
		}else{
			dDay=lastDay[month];
			return dDay;
		}
		
	}
	
	public static void buildCal(String imp){
		cal.clear();
		String inputDate = imp;
		String actualDate = "";

		if(inputDate.length()<=10){actualDate =inputDate.substring(0).toString();
		}else{actualDate =inputDate.substring(0, 11).toString();}
		

		int aYear = Integer.parseInt(actualDate.substring(0, 4));

		int aMonth =Integer.parseInt(actualDate.substring(5,7)) ;

		int aDay= Integer.parseInt(actualDate.substring(8,10));

		cal.set(aYear, aMonth-1, aDay);
		
	
		 
	 
		
		String da =firstDay();
		
		String today="";
		int lastD = leapCheck(aYear,aMonth) ;
	
		setFinalDay(lastD);
		int dayC = 1;
		int first = 0;
		int count =0;
		int dayName =0;
		boolean done = false;
		String tens = "";
		String dateSent;
		if(da.toLowerCase().equals("mon")){first= 0;}
		if(da.toLowerCase().equals("tue")){first= 1;}
		if(da.toLowerCase().equals("wed")){first= 2;}
		if(da.toLowerCase().equals("thu")){first= 3;}
		if(da.toLowerCase().equals("fri")){first= 4;}
		if(da.toLowerCase().equals("sat")){first= 5;}
		if(da.toLowerCase().equals("sun")){first= 6;}
		
		for(int i=0; i<6;i++){
			int week = i;
//			System.out.println("Last Day:" +lastD);
			
			if(dayC>lastD){break;}
			count =0;
			for(int j=0; j<7;j++){
				if(first == count){
					done = true;
//					System.out.println("Triggered first day done = true");
				}
				count++;
			

				if(done==false){

					if(dayC<9){tens ="0";}else{tens ="";}
					dateSent = ""+aYear+"-"+aMonth+"-"+dayC;
					DataDay dayN = new DataDay(dateSent);
					
					calDates[i][j]=dayN;
				}else{
					if(dayC==aDay){today="*";}
					if(dayC<9){tens ="0";}else{tens ="";}
				    dateSent = ""+aYear+"-"+aMonth+"-"+tens+dayC;

					String sentName = today+dayC;

					dayName = j;
					DataDay dayR = new DataDay(dateSent,sentName,dayC,week, dayName);
					if(sentName.charAt(0)=='*'){theDayis=dayR;}
					calDates[i][j]=dayR;
					today="";
					dayC++;
					if(dayC>lastD){

						break;
						}
				
					
				}
				
			}
			
			
		}
		
	};
	
	public static Calendar getCal() {
		return cal;
	}

	public static void setCal(Calendar cal) {
		DataTime.cal = cal;
	}

	public static String returnTime(){//time stamp generic method
	
		Timestamp time = new java.sql.Timestamp(cal2.getTime().getTime());
		String temp = time.toString();
		
		return temp;
	};

	public static String dayOfWeek(){//returns day of week
		
		Date a = new Date();		
		SimpleDateFormat day = new SimpleDateFormat("E");				
		String b = day.format(a).toString();		
		return b ;
		
	};
	
	
	public static String dayOfWeek(int c, int d, int e){//returns day of week with modifiers c is for days d month e years
		
		cal.add(Calendar.DATE, c);
		cal.add(Calendar.MONTH, d);
		cal.add(Calendar.YEAR, e);
		
		String ret = cal.getTime().toString();
		String day = ret.substring(0, 3);
		return day;
		
	};
	
	public static String firstDay(){
		 cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		 
		 String ret = cal.getTime().toString();
		 String day = ret.substring(0, 3);
		 return day;
	}

	public static int getFinalDay() {
		return finalDay;
	}

	public static void setFinalDay(int finalDay) {
		DataTime.finalDay = finalDay;
	}





	public String[] getHourName() {
		return hourName;
	}
	



	
	
	
	
}
