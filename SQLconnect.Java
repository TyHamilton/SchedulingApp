package backEndSchedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RNchecks extends Application implements Runnable  {
	Button alert =new Button();
	Label labLogin = new Label();
	ReentrantLock lock = new ReentrantLock();
	

		
	@FXML
	public  void  windowO(){
	
try{
			
			FXMLLoader fx = new FXMLLoader();
			fx.setController(this);
			fx.setClassLoader(getClass().getClassLoader());
//			labLogin = new Lable();
			labLogin.setText(UIAssist.getAlertText());
			alert = new Button();
			
			alert.setOnAction(e->{System.out.println("");
			UIAssist.setShowAlert(false);
			try{
				final Node whatWin = (Node) e.getSource();
			    final Stage stage = (Stage) whatWin.getScene().getWindow();
			    stage.close();
			}catch(Exception p){}
			});
			alert.setText("Okay");
			StackPane format =new StackPane();
			format.getChildren().add(labLogin);
			format.getChildren().add(alert);
			Scene scene = new Scene(format,200,200);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			

			
		
			}catch(Exception ex){
				ex.printStackTrace();
			}

		
		
	}
	
	
	
	@Override
	
	@FXML
	public void run() {
		// TODO Auto-generated method stub
		
		
		System.out.println("Thread fired");

		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFed= formatter.format(System.currentTimeMillis());
		System.out.println(dateFed);
	
			System.out.println("Tick Tock");

			String realTime =new String();
			realTime= dateFed;
			int hourNow=Integer.parseInt(realTime.substring(0,2));
			int minNow= Integer.parseInt(realTime.substring(3,5));

			SQLstub.getAppointmentRe().stream()
			.forEach(s->{
				System.out.println("Appointment checked, start: "+s.getReminder());
				
				System.out.println("time is: "+hourNow+":"+minNow);
				System.out.println("Appointment checked, hour: "+s.getReminder().substring(0,2)+" "+hourNow);
				if(Integer.parseInt(s.getReminder().substring(0,2))==hourNow){
					System.out.println("Appointment checked, min: "+s.getReminder().substring(3,5)+" "+ minNow);
					if(Integer.parseInt(s.getReminder().substring(3,5))<=minNow){
						if(s.isAlerted()==false){
							s.setAlerted(true);
						System.out.println("Appointment Found ");
						UIAssist.setShowAlert(true);
					String cust = Integer.toString(s.getCustomerId());
					String time= s.getStTime();
					
					UIAssist.setAlertText("You have appointment with "+cust+ " at "+time);

					System.out.println("You have appointment with "+cust+ " at "+time);
		
					JFrame frame = new JFrame("Apointment Alert");
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					
					JPanel alertish = new JPanel();
					JButton okay = new JButton(UIAssist.getAlertText());
					okay.addActionListener(new ActionListener(){
					

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							frame.dispose();
							
							
						}
					});
					alertish.add(okay);
					frame.setSize(800, 300);
					frame.getContentPane().add(alertish);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					
					}
				}
				}
			});

		
	}



	@Override
	public void start(Stage primaryStage) throws Exception {
	
	}
}
