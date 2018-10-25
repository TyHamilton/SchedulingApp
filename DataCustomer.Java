package backEndSchedule;



public class DataCustomer extends DataAddress
{
	
	private int customerId;
	private String customerName;
	private int addressId;
	private byte active;
	private String createDate;
	private String createdBy;
	private String lastUpdate;
	private String lastUpdateBy;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	
	
	
	public DataCustomer(int customerId, String customerName, String address1, String address2, String zip,String city, String country,long phone) {// customer add button
		super( address1,address2, zip,city,country,phone,customerId );
		this.customerId = customerId;
		this.customerName = customerName;
		this.active = 1;
		this.createDate = DataTime.returnTime();
		this.createdBy = "System";
		this.lastUpdate = DataTime.returnTime();
		this.lastUpdateBy = "System";
		this.addressId=customerId;
		
	}
	
	DataCustomer(String customerId,String customerName,String addressId,String active,String createDate,String createdBy,String lastUpdate,String lastUpdateBy,String address,String address2,String cityID,String postalCode,String phone,String createDate1,String createdBy1,String lastUpdate1,String lastUpdateBy1){
		super();		
		this.customerId = Integer.parseInt(customerId);
		this.customerName=customerName;
		this.addressId=Integer.parseInt(addressId);
		this.active= Byte.valueOf(active);
		this.createDate=createDate;
		this.createdBy=createdBy;
		this.lastUpdate=lastUpdate;
		this.lastUpdateBy=lastUpdateBy;
		super.setAddressId(Integer.parseInt(addressId));
		super.setAddress(address);
		super.setAddress2(address2);
		super.setCreateDateAddress(createDate1);
		super.setCreatedByAddress(createdBy1);
		super.setLastUpdateAddress(lastUpdate1);
		super.setLastUpdateByAddress(lastUpdateBy1);
		super.setCityId(Integer.parseInt(cityID));
		super.setPostalCode(postalCode);
		super.setPhone(Long.parseLong(phone));
		
		String buff1= SQLstub.getCityName(Integer.parseInt(cityID));
		System.out.println(buff1);
		
		super.setCity(buff1);
		
		String buff2= SQLstub.getCountry(this.getCountryId());
		super.setCountry(buff2);
		
		
	}
	
	
	
	


}
