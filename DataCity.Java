package backEndSchedule;



public class DataCity extends DataCountry {
	private int cityId;
	private String city;
	private int countryId;
	private String createDateCity; 
	private String createdByCity;
	private String lastUpdateCity;
	private String lastUpdateByCity;
	
	//ui data
	private int timeMod;
	private boolean dst;
	
	public DataCity( ){
		super();
		
	} 
	
	DataCity(String city,String country ){// customer add button
		super(country);
		this.city=city;
		this.countryId=SQLstub.getCountry(country);
		this.cityId=SQLstub.getCityID(city);
	}
	
	public DataCity(int cityId, String city, int countryID, int timeMod, boolean dst) {
		super();
		this.setCityId(cityId);
		this.city = city;
		countryId = countryID;
		this.createDateCity = DataTime.returnTime();
		this.createdByCity = "System";
		this.lastUpdateCity = DataTime.returnTime();
		this.lastUpdateByCity = "System";
		this.timeMod=timeMod;
		this.dst=dst;
		}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCreateDateCity() {
		return createDateCity;
	}
	public void setCreateDateCity(String createDateCity) {
		this.createDateCity = createDateCity;
	}
	public String getCreatedByCity() {
		return createdByCity;
	}
	public void setCreatedByCity(String createdByCity) {
		this.createdByCity = createdByCity;
	}
	public String getLastUpdateCity() {
		return lastUpdateCity;
	}
	public void setLastUpdateCity(String lastUpdateCity) {
		this.lastUpdateCity = lastUpdateCity;
	}
	public String getLastUpdateByCity() {
		return lastUpdateByCity;
	}
	public void setLastUpdateByCity(String lastUpdateByCity) {
		this.lastUpdateByCity = lastUpdateByCity;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getTimeMod() {
		return timeMod;
	}

	public void setTimeMod(int timeMod) {
		this.timeMod = timeMod;
	}

	public boolean isDst() {
		return dst;
	}

	public void setDst(boolean dst) {
		this.dst = dst;
	}
	
	
	
}
