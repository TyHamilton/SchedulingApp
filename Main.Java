package backEndSchedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;


import java.time.LocalDate;


import java.util.ResourceBundle;
import java.util.TimeZone;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.VBox;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

 public class Main extends Application implements Initializable,Runnable {
	private static String[] args1;
	 /*
	  * UI data
	  * 
	  * 
	  */
	    
	
		
		
		static ScheduledThreadPoolExecutor dPool = new ScheduledThreadPoolExecutor(4);
	    
		public static ObservableList<DataUser> userOList = FXCollections.observableArrayList();
		public static ObservableList<DataLogin> logOList = FXCollections.observableArrayList();
		
		public static ObservableList<DataAppointment> appointFull = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> appointMonth = FXCollections.observableArrayList();
		
		public static ObservableList<DataAppointment> mon1OList = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> tue1OList = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> wen1OList = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> thu1OList = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> fri1OList = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> sat1OList = FXCollections.observableArrayList();
		public static ObservableList<DataAppointment> sun1OList = FXCollections.observableArrayList();
		
		public static ObservableList<DataDay> monOList = FXCollections.observableArrayList();
		public static ObservableList<DataDay> tueOList = FXCollections.observableArrayList();
		public static ObservableList<DataDay> wedOList = FXCollections.observableArrayList();
		public static ObservableList<DataDay> thuOList = FXCollections.observableArrayList();
		public static ObservableList<DataDay> friOList = FXCollections.observableArrayList();
		public static ObservableList<DataDay> satOList = FXCollections.observableArrayList();
		public static ObservableList<DataDay> sunOList = FXCollections.observableArrayList();
		
		public static ObservableList<DataCustomer> custOList = FXCollections.observableArrayList();
		public static ObservableList<DataCustomer> searchCustOList = FXCollections.observableArrayList();
		
		public static ObservableList<String> cityOList = FXCollections.observableArrayList();
		public static ObservableList<String> countryOList = FXCollections.observableArrayList();
		
		public static ObservableList<String> hoursOList = FXCollections.observableArrayList();
		public static ObservableList<String> emptyOList = FXCollections.observableArrayList();
		
		
		
		
		/*
		 * time start
		 */
		// Stream and lambda to control time outside of business hours
		
		@FXML
		public void timeClick(){
			try{
			String checker= dateSelectStart.getValue().toString();
			if(checker.equals(UIAssist.getAppSelect().getStart().substring(0,10))){}else{
				dayP();
			}
			
			}catch(Exception e){}
		}
		
		

		
		
		
		@FXML
		public void  dayP(){//for selecting days
			
			String sendIT="";
			try{
			
			if(dateSelectStart.getValue()==null){System.out.println("need date");
			return;}
			
			
			if(fieldAppointIDTxT.getText().equals("")){
				sendIT=getDatePick();
			}else{
				sendIT =UIAssist.getAppSelect().getStart();
				System.out.println("Not new appointment");
			}

			
			}catch(Exception e){}
			
			try{
			System.out.println("Time Click Send IT = "+sendIT);
			hoursOList.clear();
			selectStart.getItems().clear();

			selectEnd.getItems().clear();
			
			if(selectStart.getSelectionModel().isEmpty()){
				DataTime.fillHours(sendIT);	
				SQLstub.appointmentFilter(sendIT);
				
				fillHoursL();
				selectStart.getItems().addAll(hoursOList);
				selectEnd.getItems().addAll(hoursOList);
				UIAssist.setTimeClick(true);
			}
			}catch(Exception e){
				System.out.println(e.getStackTrace());
			}
			UIAssist.setDatePick(sendIT);
		}
		
		
		@FXML
		public void  selecApD(){//cal function
			hoursOList.clear();
			selectStart.getItems().clear();

			selectEnd.getItems().clear();
			try{
			String sendIT = UIAssist.getAppSelect().getStart();
			DataTime.fillHours(sendIT);	
			SQLstub.appointmentFilter(sendIT);
			fillHoursL();
			selectStart.getItems().addAll(hoursOList);
			selectEnd.getItems().addAll(hoursOList);

			if(hoursOList.isEmpty()){
				
			}
			if(selectStart.getSelectionModel().isEmpty()){
			
			}
			}catch(Exception e){
				
				
			}
		}
		
		
		
		public static  void fillHoursL(){//<<<lambda requirement>>fills hours and limits time from being selected outside business hours of user
			
			
			 DataTime.hoursAp.stream()
			.filter(s->s.getHour()>=8)
			.filter(s->s.getHour()<=17)
			.forEach(s->{s.getTitleH();
			hoursOList.add(s.getTitleH());

			System.out.println(s.getTitleH());
			}
			
					);
			
			
		}
		
		
		
		
		/*
		 *  User code
		 */

			




		@FXML
		public void userNew(){//saves user
			boolean isActive;
			String userName;
			String password;
			
			try{
			if(fieldUSerID.getText().equals("")){
				UIAssist.setUserNew(true);
				
					
			}
			if(filedUserName.getText().equals("")){
				System.out.println("User Name needed");
			
		
				
				
				
				
				
				if(UIAssist.isSpanish()){
					labLogin.setText("Nombre de usuario necesario");
				}else{labLogin.setText("User Name needed");}
				
				return;}
			if(fieldPasswordUser.getText().equals("")&&UIAssist.isUserNew()==true){
				
					
					
					if(UIAssist.isSpanish()){
						labLogin.setText("contrase necesaria");
					}else{labLogin.setText("password needed");}
				
				
				return;
				}
			
			if(rbActive.isSelected()){
				isActive = true;
			}else{
				isActive= false;
			}
			
			if(UIAssist.isUserNew()){
			
			userName=filedUserName.getText();
			password=fieldPasswordUser.getText();
			DataUser n = new DataUser( userName, password,isActive);
			SQLconnect neCon = new SQLconnect();
			neCon.saveNewUser(n);
			SQLstub.users.add(n);
			
			}else{
				DataUser editUser =UIAssist.getSelectedUser();
			
				userName=filedUserName.getText();
				
				editUser.setActive(isActive);
				editUser.setLastUpdate(DataTime.returnTime());
				editUser.setLastUpdatedBy(UIAssist.getUserL());
				editUser.setUserName(userName);
				
				if(fieldPasswordUser.getText().equals("")){}else{
					password=fieldPasswordUser.getText();
					editUser.setPassword(password);}
				
				SQLconnect neCon = new SQLconnect();
				neCon.editUser(editUser);
				
			}
			labLogin.setText("");
			UIAssist.setUserNew(false);
			userOList.clear();
			clearUser();
			fillUserOList();
			}catch(Exception e){
				
			}
		}
		
		@FXML
		public void clearUser(){//clears fields for user
			
			labLogin.setText("");
			fieldPasswordUser.clear();
			filedUserName.clear();
			fieldUSerID.clear();
			rbActive.setSelected(true);
		}
		
		
		
		@FXML
		public void loadUser(){//gets selected user loads fields
			if(userTable1.getSelectionModel().getSelectedItem()==null){
				return;
				
			}else{
				
				UIAssist.setSelectedUser(userTable1.getSelectionModel().getSelectedItem());
				
				DataUser loader = userTable1.getSelectionModel().getSelectedItem();
				if(loader.getActive()==true){
					rbActive.selectedProperty();
				}else{
					rbDisabled.selectedProperty();	
				}
				
				fieldUSerID.setText(Integer.toString(loader.getUserID()));
				filedUserName.setText(loader.getUserName());
				
			
			}
			
			
		}
		
		
		
		
		/*
		 * end of user code
		 * 
		 */
		
		
		/*
		 * start of day appointment clear and select
		 */
		

		
		@FXML
		public void getSelectAppMon1(){
		if(monTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(monTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(0);
		SQLstub.appointmentFilter(UIAssist.getAppSelect().getStart());
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		
		}
		}
		
		@FXML
		public void getSelectApptue1(){
		if(tueTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(tueTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(1);
		SQLstub.appointmentFilter(UIAssist.getAppSelect().getStart());
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		}
		}
		
		@FXML
		public void getSelectAppWed1(){
		if(wedTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(wedTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(2);
		SQLstub.appointmentFilter(UIAssist.getAppSelect().getStart());
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		}
		}
		

		@FXML
		public void getSelectAppThu1(){
		if(thuTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(thuTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(3);
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		}
		}
		
		@FXML
		public void getSelectAppFri1(){
		if(friTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(friTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(4);
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		}
		}
		
		@FXML
		public void getSelectAppSat1(){
		if(satTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(satTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(5);
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		}
		}
		
		@FXML
		public void getSelectAppSun1(){
		if(sunTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(sunTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(6);
		clearAllBut();
		fillAppointment();
		UIAssist.setTimeClick(true);
		}
		}
			
		
		@FXML
		public void getSelectAppMon(){
		if(monTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(monTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(0);
		clearAllBut();
		
		}
		}
		
		@FXML
		public void getSelectApptue(){
		if(tueTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(tueTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(1);
		clearAllBut();
		
		}
		}
		
		@FXML
		public void getSelectAppWed(){
		if(wedTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(wedTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(2);
		clearAllBut();
		
		}
		}
		

		@FXML
		public void getSelectAppThu(){
		if(thuTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(thuTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(3);
		clearAllBut();
		
		}
		}
		
		@FXML
		public void getSelectAppFri(){
		if(friTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(friTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(4);
		clearAllBut();
		
		}
		}
		
		@FXML
		public void getSelectAppSat(){
		if(satTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(satTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(5);
		clearAllBut();
		
		}
		}
		
		@FXML
		public void getSelectAppSun(){
		if(sunTable1.getSelectionModel().getSelectedItem()==null){return;}else{
		UIAssist.setAppSelect(sunTable1.getSelectionModel().getSelectedItem());	
		UIAssist.setAppDay(6);
		clearAllBut();
		
		}
		}

		
		@FXML
		public void clearAllBut(){//clears tables so data can be refreshed
			int check = UIAssist.getAppDay();
			if(check!=0){monTable1.getSelectionModel().clearSelection();}
			if(check!=1){tueTable1.getSelectionModel().clearSelection();}
			if(check!=2){wedTable1.getSelectionModel().clearSelection();}
			if(check!=3){thuTable1.getSelectionModel().clearSelection();}
			if(check!=4){friTable1.getSelectionModel().clearSelection();}
			if(check!=5){satTable1.getSelectionModel().clearSelection();}
			if(check!=6){sunTable1.getSelectionModel().clearSelection();}

			
		}
		
		
		@FXML
		public void appNew(){//clears fields
			try{
				
			if(fieldAppointIDTxT.getText().isEmpty()==false){fieldAppointIDTxT.clear();}
			if(fieldCustIDA.getText().isEmpty()==false){fieldCustIDA.clear();}
			if(fieldTitle.getText().isEmpty()==false){fieldTitle.clear();}
			if(fieldDescription.getText().isEmpty()==false){fieldDescription.clear();}
			if(fieldLocation.getText().isEmpty()==false){fieldLocation.clear();}
			if(fieldContact.getText().isEmpty()==false){fieldContact.clear();}
			if(fieldURL.getText().isEmpty()==false){fieldURL.clear();}
			if(fieldReminder.getText().isEmpty()==false){fieldReminder.clear();}
			dateSelectStart.getEditor().clear();
			
			UIAssist.setTimeClick(false);
			UIAssist.setAppSelect(null);
			
			}catch(Exception e){}

		}
		
		@FXML
		public void fillAppointment(){//gets selected appointment and fills data
			try{
			DataAppointment theAp = UIAssist.getAppSelect();
			int year = Integer.parseInt(theAp.getStart().substring(0, 4));
			int month = Integer.parseInt(theAp.getStart().substring(5, 7));
			int day=0;

			if(theAp.getStart().length()>=10){day =Integer.parseInt(theAp.getStart().substring(8, 10));
			}else if(theAp.getStart().length()<10){day = Integer.parseInt(theAp.getStart().substring(8, 9));}
			
			selecApD();
			
			

			
			fieldAppointIDTxT.setText(Integer.toString(theAp.getAppointmentId()));
			fieldCustIDA.setText(Integer.toString(theAp.getCustomerId()));
			fieldTitle.setText(theAp.getTitle());
			fieldDescription.setText(theAp.getDescription());
			fieldLocation.setText(theAp.getLocation());
			fieldContact.setText(theAp.getContact());
			fieldURL.setText(theAp.getUrl());
			
			System.out.println("Year: "+year+"month: "+month+"day: "+day);
			LocalDate n = LocalDate.of(year, month, day);
			
			System.out.println(n);
			dateSelectStart.setValue(n);
			String startL = theAp.getStart().substring(11,16);
			String endL = theAp.getEnd().substring(11,16);
			String timeL= "Appointment time start: "+startL+" end: "+endL+" ";
			
			labLogin.setText(timeL);
			
			hoursOList.clear();
			selectStart.getItems().clear();

			selectEnd.getItems().clear();
			try{
			String sendIT =getDatePick();
			
			DataTime.fillHours(sendIT);	
			SQLstub.appointmentFilter(sendIT);
			fillHoursL();
			selectStart.getItems().addAll(hoursOList);
			selectEnd.getItems().addAll(hoursOList);
			
		
			if(selectStart.getSelectionModel().isEmpty()){

			}
			}catch(Exception e){
				
				
			}
			
			
			}catch(Exception e){
				
			}
			
		}
		
		
		
		
		/*
		 * end of appointment day clear select
		 */
		
		
		
		
		
		
		public static void fillCust(){
			for(DataCustomer a: SQLstub.customer){
				if(a.getActive()==0){}else{
					custOList.add(a);
				}
				
			}
			
		}
		
		public static void fillCustAll(){
			for(DataCustomer a: SQLstub.customer){
				custOList.add(a);
				
			}
			
		}
		
		public static void fillCity(){
			for(int i =0; i<SQLstub.city.size(); i++){
				cityOList.add(SQLstub.city.get(i).getCity());
				
				
			}
			
		}
		
		public static void fillCountry(){
			for(int i =0; i<SQLstub.country.size(); i++){
				countryOList.add(SQLstub.country.get(i).getCountry());
				
				System.out.println(SQLstub.country.get(i).getCountry());
				
				
			}
			
		}
	
		public  void reloadFromPick(){
			String appDay = getDatePickNav();
			clearApp();
			if(appDay.equals("")){System.out.println("No Date"); return;}
			System.out.println(appDay);
			DataTime.buildCal(appDay);
			System.out.println("");
			clearCal();
			System.out.println("cleared");
			fillCal();
			System.out.println(appDay);
			DataTime.getAppD(appDay);
			
		}
		@FXML
		public void fUOList(){
			userOList.clear();
			fillUserOList();
		}
		
		public static void fillUserOList(){
			
		SQLstub.users.stream()
		.forEach(s->userOList.add(s));
		
			
			
		}; 
		
		@FXML
		public  void saveAppointment()throws DataException, ClassNotFoundException{
			boolean isNew =false;
			int nAppID=0;
			int appCustomer;
			String appLocation;
			String aContact;
			String appURL;
			String appDay;
			String aTitle;
			String aDescription;
			String gStart = " " ;
			String gEnd = " " ;			
			
			try{
				// data checks to make sure appointment is filled out correctly as well if it is a new appointment
				if(fieldAppointIDTxT.getText().equals("")||fieldAppointIDTxT.getText()==null){
					isNew=true;
					System.out.println("New Appointment");
				}else 
				{isNew=false;
				System.out.println("edit appointment");}
				if(fieldCustIDA.getText().equals("" )){throw new DataException("Seleccione Cliente","Select Customer");}
				if(fieldLocation.getText().equals("" )){throw new DataException("Seleccionar ubicaci�n","Select Location");}
				if(fieldContact.getText().equals("" )){throw new DataException("Agregar contacto","Add contact");}
				if(fieldURL.getText().equals("" )){throw new DataException("Necesita URL","Need URL");}
				appDay = getDatePick();
				if(appDay.equals("")){throw new DataException("Necesita Fecha","Need Date");}
				
				if(fieldDescription.getText().equals("")){throw new DataException("Necesidad Descripci�n","Need Description");}
				 gStart =selectStart.getSelectionModel().getSelectedItem();
				 gEnd =selectEnd.getSelectionModel().getSelectedItem();
				if(gStart== null|| gEnd==null){throw new DataException("Seleccione la fecha y luego la hora","Select Date then time");}
				if(fieldTitle.getText().equals("")){throw new DataException("Necesita T�tulo","Need Title");}
				if(gStart.equals(gEnd)){throw new DataException("Seleccione las horas correctas de inicio/finalizaci�n","Select correct Start/End times");}
				
				System.out.println(fieldAppointIDTxT.getText());
			
			appCustomer=Integer.parseInt(fieldCustIDA.getText());
			appLocation=fieldLocation.getText();
			aContact = fieldContact.getText();
			appURL = fieldURL.getText();
			
			appDay = getDatePick();
			aTitle = fieldTitle.getText();
			aDescription =fieldDescription.getText();
			String userL =UIAssist.getUserL();
			System.out.println("This Far");
			String startF =DataTime.getApStamp(appDay, gStart);
			String endF= DataTime.getApStamp(appDay, gEnd);
			System.out.println("Start "+startF+ " end "+endF);
		
			if(!isNew){
				DataAppointment selectedAp = UIAssist.getAppSelect();

//				SQLstub.appointment.remove(selectedAp);
				
				DataAppointment the = new DataAppointment( nAppID,appCustomer,aTitle,aDescription,appLocation ,aContact,appURL,startF,userL,endF);
				System.out.println(the.getStart());
				
				String st = UIAssist.correctTime(the.getStart());
				
				String en = UIAssist.correctTime(the.getEnd());
				the.setStart1(st);
				the.setEnd1(en);
				the.setEnTime();
				the.setStTime();
				the.setAppointList();
				System.out.println("Start of time spool");
				the.setHourOf();
				SQLstub.dayCheck(the.getHourOf(), startF);
			
				
				if(UIAssist.getCheckTime()==-1){
					UIAssist.setCheckTime(0);
					throw new DataException("El tiempo se usa en otra cita","Time is used in another appointment");}
			
				

				
				selectedAp.setCustomerId(appCustomer);
				selectedAp.setLocation(appLocation);
				selectedAp.setContact(aContact);
				selectedAp.setUrl(appURL);
				selectedAp.setStart(startF);
				selectedAp.setEnd(endF);
				selectedAp.setTitle(aTitle);
				selectedAp.setLastUpdateBy(userL);
				selectedAp.setLastUpdate(DataTime.returnTime());
				String st1 = UIAssist.correctTime(selectedAp.getStart());
				
				String en1 = UIAssist.correctTime(selectedAp.getEnd());

				selectedAp.setEnd1(en1);
				selectedAp.setStart1(st1);
				selectedAp.setStTime();
				selectedAp.setEnTime();
				selectedAp.setAppointList();
				System.out.println(appDay);
				System.out.println("System changed start"+startF);
//				SQLstub.appoadintment.add(selectedAp);
//				
				SQLconnect temp = new SQLconnect();
				temp.editAppointment(selectedAp);
				
			
			}else{
				
				DataAppointment the = new DataAppointment( nAppID,appCustomer,aTitle,aDescription,appLocation ,aContact,appURL,startF,userL,endF);
				System.out.println( nAppID+" "+appCustomer+" "+aTitle+" "+aDescription+" "+appLocation +" "+aContact+" "+appURL+" "+startF+" "+userL+" "+endF);
				String st = UIAssist.correctTime(the.getStart());
				
//				System.out.println(the.getAppointmentId());
				String en = UIAssist.correctTime(the.getEnd());
				the.setStart1(st);
				the.setEnd1(en);
				the.setEnTime();
				the.setStTime();
				the.setAppointList();
				System.out.println("Start of time spool");
				the.setHourOf();
				SQLstub.dayCheck(the.getHourOf(), startF);
				if(UIAssist.getCheckTime()==-1){
					UIAssist.setCheckTime(0);
					throw new DataException("El tiempo se usa en otra cita","Time is used in another appointment");}
				
				System.out.println(appDay);
				SQLstub.appointment.add(the);
				try{
				SQLconnect con = new SQLconnect();
				con.saveAppointment(the);
				}catch(Exception e){}
	
			}
			
			UIAssist.setCheckTime(0);
			
//			SQLstub.primeAppointment();
			labLogin.setText(" ");
			clearCal();
			clearApp();
			DataTime.buildCal(appDay);
			fillCal();
			DataTime.printCal();
			System.out.println(appDay);
			DataTime.getAppD(appDay);
			SQLstub.appointmentFilter(appDay);
			selectStart.getItems().clear();
			selectEnd.getItems().clear();
			selectStart.getItems().addAll(hoursOList);
			selectEnd.getItems().addAll(hoursOList);	
			UIAssist.setTimeClick(false);
			UIAssist.setAppSelect(null);
			appNew();
			}catch(DataException e){
				
				if(UIAssist.isSpanish()){ 
					labLogin.setText(e.getSpan());
					UIAssist.setAlertText(e.getSpan());
					System.out.println(e.getSpan()); 
				}else{ 
					labLogin.setText(e.getEng());
					UIAssist.setAlertText(e.getEng());
					System.out.println(e.getEng()); }
				try {
					alertW();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		}
 
		
		@FXML
		public static void clearApp(){
			mon1OList.clear();
			tue1OList.clear();
			wen1OList.clear();
			thu1OList.clear();
			fri1OList.clear();
			sat1OList.clear();
			sun1OList.clear();
		}
		
		
		@FXML
		public static void clearCal(){
			monOList.clear();
			tueOList.clear();
			wedOList.clear();
			thuOList.clear();
			friOList.clear();
			satOList.clear();
			sunOList.clear();
		}
		
		@FXML
		public static void fillCal(){
			DataDay[][] f= DataTime.getCalDates();
			boolean prints = true;
			while(prints){
			for(int i=0; i<6;i++){
				if(prints == false){break;}
				for(int j=0; j<7;j++){
					String addS = f[i][j].getDayC();
					if(j==0){monOList.add( f[i][j]);
					
					}
					if(j==1){tueOList.add( f[i][j]);
					
					}
					if(j==2){wedOList.add( f[i][j]);
					
					}
					if(j==3){thuOList.add( f[i][j]);
					
					}
					if(j==4){friOList.add( f[i][j]);
					
					}
					if(j==5){satOList.add( f[i][j]);
					
					}
					if(j==6){sunOList.add( f[i][j]);
					
					}
						if(f[i][j].getDayINT()==DataTime.getFinalDay()){
							
							
							
							prints=false;
							break;}
					}
					
				}
				
			}
			
			
		}
		
		
		
		public static void fillLogOList(){//loads log from file
			String logFile= "log.txt";
			String row = null;
			
			 try {
		           String user;
		           String time;
		           
		           int userStart = 0 ;
		           int userEnd = 0;
		           int timeStart = 0;
		           int timeEnd = 0;
		            FileReader file = new FileReader(logFile);
		            BufferedReader buffered = new BufferedReader(file);

		            while((row = buffered.readLine()) != null) {
		                for(int i=0; i < row.length(); i++){
		                	char a = row.charAt(i);
		                	if(a == '!'){
		                		
		                		userStart = i;
		                	};
		                	if(a == '/'){
		                		
		                		userEnd = i;
		                	};
		                	
		                	if(a == '@'){
		                		
		                		timeStart = i;
		                	};
		                	if(a == ','){
		                		
		                		timeEnd = i;
		                	};	
		                	
		                };
		                user = row.substring(++userStart, userEnd);
		                time = row.substring(++timeStart, timeEnd);
		                DataLogin alpha= new DataLogin(user,time);
		                logOList.add(alpha);
		              
		                
		            }   

		            buffered.close();         
		        }
		        catch(FileNotFoundException a) {
		            System.out.println("Log file not found: " + logFile );                
		        }
		        catch(IOException b) {
		            System.out.println("Corrupt log file: " + logFile );                  

		        }
		    };
		    
		    public static void writeLogFile(){//writes log after login
		    	String logFile= "log.txt";
		    	try{
		    		FileWriter logWriter = new FileWriter(logFile);
		    		BufferedWriter buffWrite = new BufferedWriter(logWriter);
		    		for(DataLogin l: logOList){
		    			String a = l.getUser();
		    			String b = l.getTimeStamp();
		    			buffWrite.write("!" + a +"/" +"@"+b+",");
		    			buffWrite.newLine();
		    			
		    		};
		    		buffWrite.close();
		    		
		    	}catch(IOException a){
		    		
		    	}
		    	
		    	
		    }
	 
	 
	 
	 /*
	  * start button
	  * 
	  */
	 
	    @FXML  //reports window
		public void refUserBut(ActionEvent event) throws IOException, InterruptedException{
			try{
				
				userOList.clear();
				fillUserOList();
				
			}catch(Exception e){
			
			};
			
				
		};
	 
	 
		
		@FXML
		public  void addCustomer(ActionEvent event) throws IOException{
			try{
				String cName;
				String cAddress;
				String cAddress2;
				String cZip;
				String cCity;
				String cCountry;
				String cPrefix;
				String cPhone ;
//				
				
				
				
				
				if(fieldName.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer name.");}
					return;
				}else{cName = fieldName.getText();}
				
				if(fieldAddress.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need Customer Address.");}
					return;
				}else{ cAddress= fieldAddress.getText();}
				
				if(fieldAddress2.getText().equals("")){
					cAddress2=" ";
				}else{cAddress2= fieldAddress2.getText();}
				
				if(fieldZip.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer zip.");}
					return;
				}else{cZip = fieldZip.getText();}
				
				if(selectCity.getSelectionModel().getSelectedItem()==null){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer city.");}
					return;
				}else{
					 cCity= selectCity.getValue();
				}
				if(selectCountry.getSelectionModel().getSelectedItem()==null){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer country.");}
					return;
				}else{ cCountry= selectCountry.getValue();}
				
				
				UIAssist.countryCheck(cCity, cCountry);
				boolean check = UIAssist.iscCheck();
				
				if(check){

				}else{
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Customer country is not correct.");}
					return;
					
				}
				
				if(fieldPrefix.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer prefix.");}
					return;
				}else{ cPrefix = fieldPrefix.getText();}
				
				if(fieldPhone.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer phone.");}
					return;
				}else{cPhone = fieldPhone.getText();}
				
				long cFinalPhone =UIAssist.phoneMaker(cPrefix,cPhone);
				
				
				if(cFinalPhone==-1){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Check Customer data.");}
					return;
				}
				
				int customerID= UIAssist.getNewID(cPrefix);
				
				
				DataCustomer addedCustomer= new DataCustomer( customerID,  cName, cAddress, cAddress2,  cZip,cCity, cCountry, cFinalPhone);
				SQLconnect con = new SQLconnect();
				con.saveCust(addedCustomer);
				SQLstub.customer.add(addedCustomer);
				custOList.clear();
				fillCust();
				
				labLogin.setText(" ");
				UIAssist.setcCheck(false);
				canc(event);
			}catch(Exception e){
				
			}
			
		}
		
		
		@FXML
		public  void editCustomerB(ActionEvent event) throws IOException{
				DataCustomer a =UIAssist.getSlectCust();
			try{


				String cName;
				String cAddress;
				String cAddress2;
				String cZip;
				String cCity;
				String cCountry;
				String cPrefix;
				String cPhone ;
//				
				
				
				
				
				if(fieldName.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer name.");}
					return;
				}else{cName = fieldName.getText();}
				
				if(fieldAddress.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need Customer Address.");}
					return;
				}else{ cAddress= fieldAddress.getText();}
				
				if(fieldAddress2.getText().equals("")){
					cAddress2=" ";
				}else{cAddress2= fieldAddress2.getText();}
				
				if(fieldZip.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer zip.");}
					return;
				}else{cZip = fieldZip.getText();}
				
				if(selectCity.getSelectionModel().getSelectedItem()==null){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer city.");}
					return;
				}else{
					 cCity= selectCity.getValue();
				}
				if(selectCountry.getSelectionModel().getSelectedItem()==null){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer country.");}
					return;
				}else{ cCountry= selectCountry.getValue();}
				
				
				UIAssist.countryCheck(cCity, cCountry);
				boolean check = UIAssist.iscCheck();
				
				if(check){

				}else{
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Customer country is not correct.");}
					return;
					
				}
				
				if(fieldPrefix.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer prefix.");}
					return;
				}else{ cPrefix = fieldPrefix.getText();}
				
				if(fieldPhone.getText().equals("")){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Need customer phone.");}
					return;
				}else{cPhone = fieldPhone.getText();}
				
				long cFinalPhone =UIAssist.phoneMaker(cPrefix,cPhone);
				
				
				if(cFinalPhone==-1){
					if(UIAssist.isSpanish()){
						labLogin.setText("Verifique los datos del usuario.");
						}else{labLogin.setText("Check Customer data.");}
					return;
				}
				byte active1 =0;
				if(activeR.isSelected()){active1=1;}else{active1=0;}
				
				 int getCit = SQLstub.getCityID(cCity);
				
				a.setCustomerName(cName);
				a.setAddress(cAddress);
				a.setAddress2(cAddress2);
				a.setPostalCode(cZip);
				a.setCity(cCity);
				a.setCityId(getCit);
				a.setCountry(cCountry);
				a.setPhone(cFinalPhone);
				a.setActive(active1);
				SQLconnect con= new SQLconnect();
				con.editCust(a);
				
				
				custOList.clear();
				fillCust();
				
				labLogin.setText(" ");
				UIAssist.setcCheck(false);
				
			}catch(Exception e){
				
			}
			
		}
		
		
		@FXML
		public void getSelectedCustomer(){
			if(custTable.getSelectionModel().getSelectedItem()==null){return;}
			DataCustomer custSelect = custTable.getSelectionModel().getSelectedItem();
			
			UIAssist.setSlectCust(custSelect);
			return;
			
		}
	
		@FXML
		public void editCustload1()throws RuntimeException{
			
			if(UIAssist.isDidILoad()==true){return;}
			
			try{
			if(custTable.getSelectionModel().getSelectedItem()!=null){
				getSelectedCustomer();
			}
			custTable.getSelectionModel().select(0);
			DataCustomer a = UIAssist.getSlectCust();
			fieldCustID.setText(String.valueOf(a.getCustomerId()));
		    fieldName.setText(a.getCustomerName());
		    fieldAddress.setText(a.getAddress());
		    fieldAddress2.setText(a.getAddress2());
		    fieldZip.setText(a.getPostalCode());   
		    String phone = UIAssist.rePhone(a.getPhone());
		    System.out.println(phone);
		    String preF =phone.substring(0, 3);
		    String phon =phone.substring(3);
		    fieldPrefix.setText(preF);
		    fieldPhone.setText(phon);
		    
			for(int i =0; i<cityOList.size();i++ ){
				if(a.getCity().equals(cityOList.get(i))){
					
					selectCity.getSelectionModel().select(i);
				}
				
				
			}
			
			for(int j =0; j<countryOList.size();j++){
				
				if(a.getCountry().equals(countryOList.get(j))){
					selectCountry.getSelectionModel().select(j);
					
				}
			}
		    
		    
		    
		    
		    System.out.println("Loaded"+a.getCustomerName());
			}catch(Exception ex){
				System.out.println(ex);
			}
			UIAssist.setDidILoad(true);
			return;
			
			
			
		}
		
		@FXML
		public void editCustload(){
			
			//if(UIAssist.isDidILoad()==true){return;}
			if(custTable.getSelectionModel().getSelectedItem()!=null){
				getSelectedCustomer();
			}
			DataCustomer a = UIAssist.getSlectCust();
			fieldCustID.setText(String.valueOf(a.getCustomerId()));
		    fieldName.setText(a.getCustomerName());
		    fieldAddress.setText(a.getAddress());
		    fieldAddress2.setText(a.getAddress2());
		    fieldZip.setText(a.getPostalCode());   
		    
			for(int i =0; i<cityOList.size();i++ ){
				if(a.getCity().equals(cityOList.get(i))){
					
					selectCity.getSelectionModel().select(i);
				}
				
				
			}
			
			for(int j =0; j<countryOList.size();j++){
				
				if(a.getCountry().equals(countryOList.get(j))){
					selectCountry.getSelectionModel().select(j);
					
				}
			}
		    
		    
		    
		    
		    System.out.println("Loaded"+a.getCustomerName());
	
			return;
		//	UIAssist.setDidILoad(true);
			
			
		}
		
		@FXML
		public void editCustload2(){
			try{
			//if(UIAssist.isDidILoad()==true){return;}
			if(custTable.getSelectionModel().getSelectedItem()!=null){
				getSelectedCustomer();
			}
			DataCustomer a = UIAssist.getSlectCust();
			fieldCustIDA.setText(String.valueOf(a.getCustomerId()));
		    
		   
		    
		    
		    
		    System.out.println("Loaded"+a.getCustomerName());
			}catch(Exception e){}
	
			
		//	UIAssist.setDidILoad(true);
			
			
		}

		@FXML
		public void searchCust(){
			searchCustOList.clear();
			String search = customerNamefield.getText().toLowerCase();
			
			if(search.equals("")){
				custOList.clear();
				fillCust();
				return;}
			

			for(int i = 0 ; i<SQLstub.customer.size();i++){
				int hits=0;
				String searched =SQLstub.customer.get(i).getCustomerName().toLowerCase();
				if(search.length()<=searched.length()){
				for(int j=0; j<search.length();j++){
					if(search.charAt(j)==searched.charAt(j)){
						hits++;
					}
					
				}
				}
				if(hits>=2){
					searchCustOList.add(SQLstub.customer.get(i));
				}
			}
			
			
			if(searchCustOList.size()>=1){
				custOList.clear();
				for(DataCustomer c: searchCustOList){
					custOList.add(c);
					
				}
				
				
			}
			
		}
		
	 
	 /*
	  * end button
	  */
	 
	 @FXML
	 public void lambBut(ActionEvent event)throws IOException{
		try{
		 bClose.setOnAction(e -> {
		 

				try {
					newWindow("/interfaceSchedule/ScheduleAdminScreen.fxml");
				} catch (Exception e1) {
			
				}
				try {closed(event);
					
				} catch (Exception e1) {
				
				}
		 
				
		 
		 }
				 );
		}catch(Exception e){
			
		}
	 }
	 
	 
	 
	 /*
	  * Start of Table info
	  */
		@FXML
		Button bClose= new Button();
		
		
		
		@FXML
			public TableView<DataCustomer> custTable = new TableView<>();
			
			@FXML
			TableColumn<DataCustomer, Integer>custIDCol= new TableColumn<>();	
			
			@FXML
			TableColumn<DataCustomer, String>custNameCol= new TableColumn<>();	
			
			@FXML
			TableColumn<DataCustomer, String>custAddCol= new TableColumn<>();	
			
			@FXML
			TableColumn<DataCustomer, String>custCitCol= new TableColumn<>();	
			
			@FXML
			TableColumn<DataCustomer, String>custZipCol= new TableColumn<>();	
			
			@FXML
			TableColumn<DataCustomer, String>custCountCol= new TableColumn<>();	
		
			@FXML
			public TableView<DataDay> monTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>monName= new TableColumn<>(); 		
			
			@FXML
			public TableView<DataDay> tueTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>tueName= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> wedTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>wedName= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> thuTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>thuName= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> friTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>friName= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> satTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>satName= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> sunTable = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>sunName= new TableColumn<>(); 

			@FXML
			public TableView<DataDay> monTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>monName2= new TableColumn<>(); 		
			
			@FXML
			public TableView<DataDay> tueTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>tueName2= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> wedTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>wedName2= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> thuTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>thuName2= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> friTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>friName2= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> satTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>satName2= new TableColumn<>(); 
			
			@FXML
			public TableView<DataDay> sunTable2 = new TableView<>();
		
			@FXML
			TableColumn<DataDay, String>sunName2= new TableColumn<>(); 
			
			
			@FXML
			public TableView<DataAppointment> monthTable1 = new TableView<>();
			
			@FXML
			TableColumn<DataAppointment, String>dayName= new TableColumn<>(); 			
			
		
			@FXML
			public TableView<DataAppointment> monTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>mon1Day= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment> tueTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>tue1Day= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment> wedTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>wed1Day= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment> thuTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>thu1Day= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment> friTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>fri1Day= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment> satTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>sat1Day= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment> sunTable1 = new TableView<>();
			@FXML
			TableColumn<DataAppointment, String>sun1Day= new TableColumn<>(); 
			
		 
			@FXML
			public TableView<DataUser> userTable1 = new TableView<>();
		 
			@FXML
			TableColumn<DataUser, Integer>userid1= new TableColumn<>("userID"); 	
			
			@FXML
			TableColumn<DataUser, String>userName1= new TableColumn<>(); 	 
			
			@FXML
			TableColumn<DataUser, String>password1= new TableColumn<>(); 	 
			
			@FXML
			TableColumn<DataUser, Byte>active1= new TableColumn<>(); 	 
			
			@FXML
			TableColumn<DataUser, String>createBy1= new TableColumn<>(); 	 
			
			@FXML
			TableColumn<DataUser, String>lastUpdate1= new TableColumn<>(); 		
				
			@FXML
			TableColumn<DataUser, String>lastUpdatedBy1= new TableColumn<>(); 	 

			@FXML
			public TableView<DataLogin> loginTable = new TableView<>();
			
			@FXML
			TableColumn<DataLogin, String>user= new TableColumn<>(); 	
			
			@FXML
			TableColumn<DataLogin, String>time= new TableColumn<>(); 
	    
			@FXML
			public TableView<DataCustomer>tableCustAd= new TableView<>(); 	
			
			@FXML
			TableColumn<DataCustomer, String>customerIDTable= new TableColumn<>(); 
	
			@FXML
			TableColumn<DataCustomer, String>customerNameTable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataCustomer, String>addressTable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataCustomer, String>addressIDTable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataCustomer, String>isActiveTable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataCustomer, String>creatDateTable= new TableColumn<>(); 

			@FXML
			TableColumn<DataCustomer, String>createdByTeable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataCustomer, String>lastEditTable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataCustomer, String>editByTable= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment>tableReport= new TableView<>(); 	

			@FXML
			TableColumn<DataAppointment, String>userTable= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataAppointment, String>custTable1= new TableColumn<>(); 
			
			@FXML
			TableColumn<DataAppointment, String>dateTable1= new TableColumn<>(); 
			
			@FXML
			public TableView<DataAppointment>apMonthTable= new TableView<>();
	
			@FXML
			TableColumn<DataAppointment, String>custName= new TableColumn<>(); 

			@FXML
			TableColumn<DataAppointment, String>apTitle= new TableColumn<>(); 			
			
			@FXML
			TableColumn<DataAppointment, String>dateTable= new TableColumn<>(); 
	 
	 /*
	  * end of Table info
	  */
	 
	 @FXML
	 public  String getDatePick() {
		try{
		 LocalDate ld = dateSelectStart.getValue();
		 String a = ld.toString();
		 System.out.println(a);
		 return a;
		}catch(NullPointerException e){
			
			return "";
		}
		
	 }
	 @FXML
	 public  String getDatePickNav() {
		try{
		 LocalDate ld = dateNav.getValue();
		 String a = ld.toString();
		 //System.out.println(a);
		 return a;
		}catch(NullPointerException e){
			
			return "";
		}
		
	 }
	 
	 /*
	  * Start of Fields/Text/datepicker/radio/lable
	  * 
	  *
	  *
	  */
	 

	 		@FXML
	 		private Label labLogin;
	 		@FXML
	 		private Label alertFor = new Label();
	 
	 		@FXML
	 		private RadioButton rbActive;
	   
	 		@FXML
	 		private RadioButton rbDisabled;
	    
	    
	    	@FXML
		    private RadioButton rLang1;
		   
		    @FXML
		    private RadioButton rLang2;
		    
	    	@FXML
		    private RadioButton activeR;
		   
		    @FXML
		    private RadioButton disabledR;

		    @FXML
		    private DatePicker dateNav;	 
	 
			@FXML
			private DatePicker dateSelectStart;
			
			@FXML
			private ChoiceBox<String> selectCity =new ChoiceBox<>(); 
			
			@FXML
			private ChoiceBox<String> selectStart =new ChoiceBox<>(); 
			
			@FXML
			private ChoiceBox<String> selectEnd =new ChoiceBox<>(); 
			
			
			@FXML
			private ChoiceBox<String> selectCountry =new ChoiceBox<>(); 
	 
	 //Admin Screen text
	  
	    @FXML
	    private TextField fieldSearchUserID;
	    
	    @FXML
	    private TextField fieldSearchUserName;
	    
	    @FXML
	    private TextField fieldUSerID;
	    
	    @FXML
	    private TextField filedUserName;

	    @FXML
	    private TextField fieldPasswordUser;
	    
	    
	    //Appointment text
	    
	    @FXML
	    private TextArea fieldDescription;
	    
	    @FXML
	    private TextField fieldSearchByName;
	    
	    @FXML
	    private TextField fieldTitle;
	    
	    @FXML
	    private TextField fieldSearchID;
	    
	    @FXML
	    private  TextField fieldAppointIDTxT;
	    
	    @FXML
	    private TextField fieldCustomerID;
	    
	    @FXML
	    private TextField fieldURL;
	    
	    @FXML
	    private TextField fieldReminder;
	    
	    @FXML
	    private TextField fieldSnooze;
	    
	    @FXML
	    private TextArea fieldLocation;	    
	    
	    @FXML
	    private TextArea fieldContact; 
	 
	    //Customer Add
	    
	    @FXML
	    private TextField fieldPrefix;
	    
	    @FXML
	    private TextField fieldPhone;	
	    
	    @FXML
	    private TextField fieldCustID;
	    
	    @FXML
	    private TextField fieldName;
	    
	    @FXML
	    private TextField fieldAddress;
	    
	    @FXML
	    private TextField fieldAddress2;
	    
	    @FXML
	    private TextField fieldZip;
	    
	    //customer edit
	    
	    @FXML
	    private TextField fieldCustIDA;
	    
	    @FXML
	    private TextField fieldNameA;
	    
	    @FXML
	    private TextField fieldAddressA;
	    
	    @FXML
	    private TextField fieldAddress2A;
	    
	    @FXML
	    private TextField fieldZipA;    
	    
	    
	    
	    //Login
	    
	    @FXML
	    private TextField fieldUser; 	
	    
	    @FXML
	    private PasswordField fielddPassword; 	  
	    
	    //Main Page
	    
	    @FXML
	    private TextField customerNamefield; 	   
	    
	    
	    
	    
	    
	    
	 
	 /*
	  * End of Fields/Text
	  */
	 
	 
	 
	 /*
	  * Start of Navigation Code
	  */


		
		
		public void closeClose(ActionEvent event) throws IOException{
			
			
			UIAssist.setClose(true);
			//System.out.println(UIAssist.isClose());
			closed(event);
			dPool.shutdown();
			System.exit(0);
			
		};
		
		@FXML
		public void cancelCancel(ActionEvent event)throws IOException {
		 closed(event);
		};
				
		

		@FXML   //primary closed check opens cancel window
		public void cancled(ActionEvent event) throws IOException{	
			newWindowW("/interfaceSchedule/cancel.fxml");
			//System.out.println(UIAssist.isClose());
			if(UIAssist.isClose()==true){
		    closed(event);
		    UIAssist.setClose(false);
			}
		};
		
		@FXML   //primary closed check opens cancel window
		public void canc(ActionEvent event) throws IOException{	
	
		    
			newWindow("/interfaceSchedule/ScheduleMainPage.fxml");
			closed(event);
		    
			
		};
	 
	 
		@FXML
		public void closed(ActionEvent event) throws IOException{	
			
			
			final Node whatWin = (Node) event.getSource();
		    final Stage stage = (Stage) whatWin.getScene().getWindow();
		    stage.close();
		   
			
		};	 
		
		@FXML
		public void closed1(ActionEvent event) throws IOException{	
			
			 ((Node)(event.getSource())).getScene().getWindow().hide();
//		  final Node source = (Node) event.getSource();
//		    final Stage stage = (Stage) source.getScene().getWindow();
//		    stage.close();
			
		};
	 
		@FXML  //login code
		public void adminBut(ActionEvent event) throws IOException, InterruptedException{
			try{
				
				userOList.clear();
				fillUserOList();
				newWindow("/interfaceSchedule/ScheduleAdminScreen.fxml");
				closed(event);
			}catch(Exception e){
			
			};
			
				
		};	 
		
		@FXML  //admin window close button
		public void adminCloseBut(ActionEvent event) throws IOException, InterruptedException{
			try{
//				UIAssist.setClose(false);
//				newWindowW("/interfaceSchedule/cancel.fxml");
//				if(UIAssist.isClose()==true){
					
					newWindow("/interfaceSchedule/ScheduleMainPage.fxml");
					
					closed(event);
//					}

			}catch(Exception e){
			e.printStackTrace();
			};
			
				
		};	 
		
		
		@FXML  //admin window close button
		public void backToLogin(ActionEvent event) throws IOException, InterruptedException{
			try{
				clearApp();
//				UIAssist.setClose(false);
//				newWindowW("/interfaceSchedule/cancel.fxml");
//				if(UIAssist.isClose()==true){
				    
					newWindow("/interfaceSchedule/ScheduleLogin.fxml");
					closed(event);
//					}

			}catch(Exception e){
			
			};
			
				
		};	 
		
		@FXML
		public void editCloseBut(ActionEvent event) throws IOException, InterruptedException {
			try{
				UIAssist.setClose(false);
				
				
//				newWindowW("/interfaceSchedule/cancel.fxml");
//				if(UIAssist.isClose()==true){
					custOList.clear();
					fillCust();
					newWindow("/interfaceSchedule/ScheduleMainPage.fxml");
					closed(event);
//					}

			}catch(Exception e){
			
			};
			
				
		};	
		
		@FXML  //reports window
		public void editCustomerBut(ActionEvent event) throws IOException, InterruptedException{
			try{
				if(custTable.getSelectionModel().getSelectedItem()==null){return;}
				UIAssist.setDidILoad(false);
				getSelectedCustomer();
				custOList.clear();
				custOList.add(UIAssist.getSlectCust());
				System.out.println(UIAssist.getSlectCust().getCustomerName());
				newWindow("/interfaceSchedule/ScheduleCustomerEdit.fxml");
				closed(event);
			}catch(Exception e){
			
			};
			
				
		};
		
		
		
		@FXML  //reports window
		public void addCustomerBut(ActionEvent event) throws IOException, InterruptedException{
			try{
				newWindow("/interfaceSchedule/ScheduleCustomerAdd.fxml");
				closed(event);
			}catch(Exception e){
			
			};
			
				
		};
		
		
		
		@FXML  //reports window
		public void appointmentsBut(ActionEvent event) throws IOException, InterruptedException{
			try{
				newWindow("/interfaceSchedule/ScheduleAppointment.fxml");
				closed(event);
			}catch(Exception e){
			
			};
			
				
		};
		
		
		@FXML  //reports window
		public void reportBut(ActionEvent event) throws IOException, InterruptedException{
			try{
				newWindow("/interfaceSchedule/ScheduleReports.fxml");
				closed(event);
			}catch(Exception e){
			
			};
			
				
		};
		
		
		
		 //login code

		
		
		
		@FXML
		public void issueLogin()throws IOException{
			try{
			if(UIAssist.isSpanish()){
			    newWindow("/interfaceSchedule/passwords.fxml");
			}else{
				newWindow("/interfaceSchedule/password.fxml");	
			}
			}catch(Exception e){}
			}
		
		
		
		@FXML  //login code
		public void loginBut1(ActionEvent event) throws PasswordException,IOException{
			
			String lang = System.getProperty("user.language");//language defaults to English if other than en and es
			if(lang.equals("en")){
				UIAssist.setSpanish(false);
			}else if(lang.equals("es")){
				UIAssist.setSpanish(false);
			}
			System.out.println(lang);
			System.out.println("Is spanish " +UIAssist.isSpanish());
			
			boolean check=false;
//			if(rLang1.isSelected()){UIAssist.setSpanish(false);}else{UIAssist.setSpanish(true);}
			try{
//				if(selectCity.getSelectionModel().getSelectedItem()==null){
//					if(UIAssist.isSpanish()){
//						
//						labLogin.setText("Seleccione ubicaci�n!");
//					}else{
//						labLogin.setText("Select Location!");
//					}
//					
//					System.out.println("Need city");
//				return;	
//				}
//				String location =selectCity.getSelectionModel().getSelectedItem();
				String user =fieldUser.getText();
				String password =fielddPassword.getText();
				try{
				if(user.equals("")||password.equals("")){
					throw new PasswordException();
					
				}else{
					check =SQLstub.loginCheck( user, password);
					System.out.println(check);
				}
				if(check == false){
					throw new PasswordException();
					}
				}catch(Exception a){
					
					issueLogin();
					return;
				}
				
				String local=TimeZone.getDefault().getDisplayName();
				

				System.out.println(local);//location will default to Pheonix if you are not 
				if(local.equals("Mountain Standard Time")){UIAssist.setLocationL("Phoenix, AZ");}
				if(local.equals("Eastern Standard Time")){UIAssist.setLocationL("New York, NY");}
				if(local.equals("Greenwich Mean Time")){UIAssist.setLocationL("London, England");}
				
				System.out.println("Time Zone set to: " + UIAssist.getLocationL());
				UIAssist.setUserL(user);
//				UIAssist.setLocationL(location);
				SQLstub.timeLAdjust();
				SQLstub.primeAppointmentTime();
//				System.out.println(UIAssist.timeModFlip());
				DataTime.getAppD(DataTime.returnTime());
				logOList.add(new DataLogin(user));
				SQLstub.fillTodayapp();
//				RNchecks checked = new RNchecks();
//				checked.run();
				System.out.println("Login completed "+DataTime.rightNow());
				addThreadsToPool(new RNchecks(), 0, 60);
				writeLogFile();
				newWindow("/interfaceSchedule/ScheduleMainPage.fxml");
				closed(event);
			}catch(Exception e){
			
			};
			
				
		};
		
		
		Button alert;
		
		@FXML public void onMouseClickedCancelBtn(InputEvent e) {
		    final Node source = (Node) e.getSource();
		    final Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		}
		
		@FXML
		public void alertW() throws IOException{
			System.out.println("Window launch start");
			try{
		
			FXMLLoader fx = new FXMLLoader();
			fx.setController(this);
			fx.setClassLoader(getClass().getClassLoader());
			Label problem = new Label();
			problem.setText(UIAssist.getAlertText());
			alert = new Button();
			
			alert.setOnAction(e->{System.out.println("");
			UIAssist.setShowAlert(false);
			try{
			closed(e);
			}catch(Exception p){p.printStackTrace();}
			});
			alert.setText("Okay");
			VBox format =new VBox(10);
			
			format.getChildren().add(labLogin);
			format.getChildren().add(alert);
			Scene scene = new Scene(format,400,200);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.showAndWait();
			

			
		
			}catch(Exception e){e.printStackTrace();}
		
			
		}
		
		
		
		
		//newWindowW  waits for the opened window to close before continuing on
		public void newWindowW(String a) throws IOException{//used to open new windows based on a string for file location
			try{//opens new windows for methods
				
				FXMLLoader fxmlload = new FXMLLoader(getClass().getResource(a));
				Parent root2=(Parent) fxmlload.load();
				Stage stage2 = new Stage();
				stage2.setScene(new Scene(root2));
				stage2.showAndWait();
				}catch(Exception e){
					e.printStackTrace();
				};
		};	 
			
		
		
 
	 
	 
	 public void newWindow(String a) throws IOException{//used to open new windows based on a string for file location
			try{//opens new windows for methods
				
				FXMLLoader fxmlload = new FXMLLoader(getClass().getResource(a));
				Parent root2=(Parent) fxmlload.load();
				Stage stage2 = new Stage();
				stage2.setScene(new Scene(root2));
				stage2.show();
				}catch(Exception e){
					e.printStackTrace();
				};
		};
	
		
		
		
		
		
		
		/*
		 * End of Navigation Code
		 */ 
	 

		
		
		
		/*
		 * Start of report code
		 *
		 */
		public void reportMonth(){
			String day1 = getDatePick();
			if(day1.equals("")){
				day1=DataTime.returnTime().substring(0,11);
			}
			SQLstub.monRep(day1);
			
		}
		
		@FXML
		public void userMonth(){
			 
			
			
			if(userTable1.getSelectionModel().getSelectedItem()==null){
				return;
				
			}else{
				
				UIAssist.setSelectedUser(userTable1.getSelectionModel().getSelectedItem());
				DataUser loader = userTable1.getSelectionModel().getSelectedItem();
				UIAssist.setBuffUser(UIAssist.getUserL());
				UIAssist.setUserL(loader.getUserName());
				String datePush =getDatePick();
				String datePush2=DataTime.returnTime().substring(0,11);
				clearApp();
				if(datePush.equals("")){
				datePush= datePush2;
				}
				DataTime.getAppD(datePush);
				SQLstub.userRep(datePush,UIAssist.getUserL());
			}
			
		}
		
		
		
		/*
		 * End of report code
		 */
		
		
		
		
		

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/interfaceSchedule/ScheduleLogin.fxml"));
			Scene scene = new Scene(root,1024,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			


			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void wTrigger(){
//		System.out.println("Ping");
		if(UIAssist.isShowAlert()==true){
			
			alertFor.setText(UIAssist.getAlertText());
			System.out.println("Launch new window");
			try {
				alertW();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
	public static void primer(){
		try{
		System.out.println(DataTime.rightNow());
		System.out.println(DataTime.returnTime());
		SQLconnect connection = new SQLconnect();
		connection.getUser();
		connection.getAppointment();
		connection.getCustomer();



		
		//UI data primer
		SQLstub.country.add(new DataCountry(0, "United Kingdom"));
		SQLstub.country.add(new DataCountry(1, "United States of America"));
		SQLstub.getCity().add(new DataCity(0, "London, England" , 0,1,false));
		SQLstub.getCity().add(new DataCity(1, "Phoenix, AZ" , 1,-7,false));
		SQLstub.getCity().add(new DataCity(2, "New York, NY" , 1,-4,true));

		
		DataTime.buildCal(DataTime.returnTime());
	
		DataTime.printCal();
		fillCal();
		
		
		fillLogOList();
		fillCity();
		fillCountry();
		
		
		fillCust();
		DataTime.fillHours(DataTime.returnTime());

		
		System.out.println("Time :"+DataTime.returnTime());

		}catch(Exception e){
			
		}
		
		
		
	}

	public static void fireInt(){
		launch(args1);
	}
	
	public static void stopTh(){
		
		dPool.shutdown();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		String local = TimeZone.getDefault().getDisplayName();
		
//		String local = TimeZone.getDefault().geta;
		System.out.println(local);
		if(local.equals("Mountain Standard Time")){UIAssist.setLocationL("Pheonix, AZ");}
		if(local.equals("Eastern Standard Time")){UIAssist.setLocationL("New York, NY");}
		if(local.equals("Greenwich Mean Time")){UIAssist.setLocationL("London, England");}
		
		System.out.println("Time Zone set to: " + UIAssist.getLocationL());
		
		
		String lang = System.getProperty("user.language");//language defaults to English if other than en and es
		if(lang.equals("en")){
			UIAssist.setSpanish(false);
		}else if(lang.equals("es")){
			UIAssist.setSpanish(false);
		}
		System.out.println(lang);
		System.out.println("Is spanish " +UIAssist.isSpanish());
		
		setArgs1(args);
		primer();
//		addThreadsToPool(new DataThreadFX(), 0, 1);
		
		
		
		
		launch(args);
	}
	    public static void addThreadsToPool(Runnable a, int b, int c){
//	     ScheduledThreadPoolExecutor dPool = new ScheduledThreadPoolExecutor(4);
//	    	dPool.scheduleAtFixedRate(new DataThreadFX(),0,1,TimeUnit.SECONDS);
//	    	dPool.scheduleAtFixedRate(new RNchecks(), 10, 60, TimeUnit.SECONDS);
	    	dPool.scheduleAtFixedRate(a, b, c, TimeUnit.SECONDS);
	    	
	    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// User Table
		
		
		custTable.setItems(custOList);
		
		custIDCol.setCellValueFactory(new PropertyValueFactory<DataCustomer, Integer>("customerId"));
		custNameCol.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("customerName"));
		custAddCol.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("address"));
		custCitCol.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("city"));
		custZipCol.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("postalCode"));
		custCountCol.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("country"));
		 
		
		
		userTable1.setItems(userOList);

		userid1.setCellValueFactory(new PropertyValueFactory<DataUser, Integer>("userID"));
		userid1.setMinWidth(userTable1.getMaxWidth()/6);
		userName1.setCellValueFactory(new PropertyValueFactory<>("userName"));
		userName1.setMinWidth(userTable1.getMaxWidth()/6);
		active1.setCellValueFactory(new PropertyValueFactory<>("active"));
		active1.setMinWidth(userTable1.getMaxWidth()/6);
		createBy1.setCellValueFactory(new PropertyValueFactory<>("createBy"));
		createBy1.setMinWidth(userTable1.getMaxWidth()/6);
		lastUpdate1.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
		lastUpdate1.setMinWidth(userTable1.getMaxWidth()/6);
		lastUpdatedBy1.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
		lastUpdatedBy1.setMinWidth(userTable1.getMaxWidth()/6);
		
		loginTable.setItems(logOList);
		
		user.setCellValueFactory(new PropertyValueFactory<>("user"));
		time.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
		
		
		
		
		dayName.setCellValueFactory(new PropertyValueFactory<>("dayC"));
		

		
		
		monTable1.setItems(mon1OList);
		mon1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		tueTable1.setItems(tue1OList);
		tue1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		wedTable1.setItems(wen1OList);
		wed1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		thuTable1.setItems(thu1OList);
		thu1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		friTable1.setItems(fri1OList);
		fri1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		satTable1.setItems(sat1OList);
		sat1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		sunTable1.setItems(sun1OList);
		sun1Day.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("appointList"));
		
		
		
		
		monTable.setItems(monOList);
		monName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));

		
		tueTable.setItems(tueOList);
		tueName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		wedTable.setItems(wedOList);
		wedName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		thuTable.setItems(thuOList);
		thuName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		friTable.setItems(friOList);
		friName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		satTable.setItems(satOList);
		satName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		sunTable.setItems(sunOList);
		sunName.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		monTable2.setItems(monOList);
		monName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));

		
		tueTable2.setItems(tueOList);
		tueName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		wedTable2.setItems(wedOList);
		wedName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		thuTable2.setItems(thuOList);
		thuName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		friTable2.setItems(friOList);
		friName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		satTable2.setItems(satOList);
		satName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		sunTable2.setItems(sunOList);
		sunName2.setCellValueFactory(new PropertyValueFactory<DataDay, String>("DayC"));
		
		
		selectCity.getItems().addAll(cityOList);
		selectCountry.getItems().addAll(countryOList);
		
		tableCustAd.setItems(custOList);
		customerIDTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("customerId"));
		customerNameTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("customerName"));
		addressTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("adress"));
		addressIDTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("addressId"));
		isActiveTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("active"));
		creatDateTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("createDate"));
		createdByTeable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("createdBy"));
		lastEditTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("lastUpdate"));
		editByTable.setCellValueFactory(new PropertyValueFactory<DataCustomer, String>("lastUpdateBy"));
		
		tableReport.setItems(appointFull);
		userTable.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("createdBy"));
		custTable1.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("customerName"));
		dateTable1.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("start"));
		
		apMonthTable.setItems(appointMonth);
		custName.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("customerName"));
		apTitle.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("title"));
		dateTable.setCellValueFactory(new PropertyValueFactory<DataAppointment, String>("start"));
		
		
		
		
	}





	public static String[] getArgs1() {
		return args1;
	}



	public static void setArgs1(String[] args1) {
		Main.args1 = args1;
	}



	public void run() {
		// TODO Auto-generated method stub
		
	}



	public  Label getAlertFor() {
		return alertFor;
	}



	public  void setAlertFor(String alertFor) {
		this.alertFor.setText(alertFor);
	}




	

	
}
