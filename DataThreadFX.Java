package backEndSchedule;

import java.io.IOException;

public class DataThreadFX extends Main implements Runnable {
	 Main test = new Main();
	
	 public void window(){
		 try {
			test.alertW();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @Override
	public void run() {
		// TODO Auto-generated method stub
		Main.fireInt();
		System.out.println("Thread Triggered for DatThread");
		
		if(UIAssist.isShowAlert()==true){
			
			
			
		}
//		test.fireInt();
	}

}
