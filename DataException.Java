package backEndSchedule;

@SuppressWarnings("serial")
public class DataException extends Exception{
	private int error=0;
	private String eng;
	private String span;
	public DataException(int a){
		this.setError(a);
	}
	
	public DataException(String b, String a){
		this.eng=a;
		this.span=b;
	}
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getEng() {
		return eng;
	}
	public void setEng(String eng) {
		this.eng = eng;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	
	
	
	
}
