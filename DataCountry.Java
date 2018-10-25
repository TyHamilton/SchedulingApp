package backEndSchedule;



public class DataCountry {
	private int countryId; 
	private String country;
	private String createDateCountry; 
	private String createdByCountry; 
	private String lastUpdateCountry;
	private String lastUpdateByCountry;
	

	
	public DataCountry(){
		
	}
	
	public DataCountry(String country){ // customer add button
		this.country= country;
		
	}
	
	public DataCountry(int countryId, String country)  {//initial load for country
		super();
		this.countryId = countryId;
		this.country = country;
		this.createDateCountry = DataTime.returnTime();
		this.createdByCountry = "System";
		this.lastUpdateCountry = DataTime.returnTime();
		this.lastUpdateByCountry = "System";
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCreateDateCountry() {
		return createDateCountry;
	}
	public void setCreateDateCountry(String createDateCountry) {
		this.createDateCountry = createDateCountry;
	}
	public String getCreatedByCountry() {
		return createdByCountry;
	}
	public void setCreatedByCountry(String createdByCountry) {
		this.createdByCountry = createdByCountry;
	}
	public String getLastUpdateCountry() {
		return lastUpdateCountry;
	}
	public void setLastUpdateCountry(String lastUpdateCountry) {
		this.lastUpdateCountry = lastUpdateCountry;
	}
	public String getLastUpdateByCountry() {
		return lastUpdateByCountry;
	}
	public void setLastUpdateByCountry(String lastUpdateByCountry) {
		this.lastUpdateByCountry = lastUpdateByCountry;
	}






}
