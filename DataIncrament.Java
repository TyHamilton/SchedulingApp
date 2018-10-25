package backEndSchedule;

public class DataIncrement extends DataReminder {
	private int incrementTypeId;
	private String incrementTypeDescription;
	
    public int getIncrementTypeId() {
		return incrementTypeId;
	}
	public void setIncrementTypeId(int incrementTypeId) {
		this.incrementTypeId = incrementTypeId;
	}
	public String getIncrementTypeDescription() {
		return incrementTypeDescription;
	}
	public void setIncrementTypeDescription(String incrementTypeDescription) {
		this.incrementTypeDescription = incrementTypeDescription;
	}

}
