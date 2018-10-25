package backEndSchedule;

public class DataAddress extends DataCity{
	private int addressId;
	private String address;
	private String address2;
	private int cityId;
	private String postalCode;
	private long phone;
	private String createDateAddress;
	private String createdByAddress; 
	private String lastUpdateAddress;
	private String lastUpdateByAddress;
	
	public DataAddress(String address1,String address2,String zip, String city,String country,long phone, int addressId ){// customer add button
		super(city,country);
		this.addressId =  addressId;
		this.address=address1;
		this.address2= address2;
		this.postalCode = zip;
		this.phone = phone;
	}
	
	public DataAddress(int addressId,String address, String address2,int cityId2, String postalCode, long phone ) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.address2 = address2;
		cityId = cityId2;
		this.postalCode = postalCode;
		this.phone = phone;
		this.createDateAddress = DataTime.returnTime();
		this.createdByAddress = "System";
		this.lastUpdateAddress = DataTime.returnTime();
		this.lastUpdateByAddress = "System";
	}
	
	public DataAddress() {
		// TODO Auto-generated constructor stub
	}

	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getCreateDateAddress() {
		return createDateAddress;
	}
	public void setCreateDateAddress(String createDateAddress) {
		this.createDateAddress = createDateAddress;
	}
	public String getCreatedByAddress() {
		return createdByAddress;
	}
	public void setCreatedByAddress(String createdByAddress) {
		this.createdByAddress = createdByAddress;
	}
	public String getLastUpdateAddress() {
		return lastUpdateAddress;
	}
	public void setLastUpdateAddress(String lastUpdateAddress) {
		this.lastUpdateAddress = lastUpdateAddress;
	}
	public String getLastUpdateByAddress() {
		return lastUpdateByAddress;
	}
	public void setLastUpdateByAddress(String lastUpdateByAddress) {
		this.lastUpdateByAddress = lastUpdateByAddress;
	}
	
	
	
	
}
