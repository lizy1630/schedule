package scheduleAssignment;

import java.io.Serializable;

public class CMeeting  implements  Comparable <CMeeting> , Serializable {
	private static final long serialVersionUID = 1L;
	private CTime startTime;            // start time
	private CTime endTime;              // end time
	private String description;         // description about meeting
	private CLocation location;            // location about meeting
	
	
	public CMeeting(CTime x, CTime y, String info, CLocation loca) {
		setStartTime(x);
		setEndTime(y);
		setDescription(info);
		setLocation(loca);
	}
	
	public CTime getstartCTime() {
		return startTime;
	}
	public CTime getendCTime() {
		return endTime;
	}
	
	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	public CLocation getLocation() {
		return location;
	}

	public void setLocation(CLocation location) {
		this.location = location;
	}

	public String getStartTime() {
		return startTime.getTime();
	}


	public void setStartTime(CTime startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime.getTime();
	}


	public void setEndTime(CTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(CMeeting o) {
		// TODO Auto-generated method stub
		return startTime.calendar.compareTo(o.getstartCTime().calendar);
	}

}
