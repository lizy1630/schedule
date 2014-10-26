package scheduler;

import java.io.Serializable;

import org.junit.Test;
public class CScheduleTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CSchedule sch = new CSchedule();

	/**
	 * 
	 */
	@Test
	public void test() {
		
		CTime startTime = new CTime(2013,2,22,13,10,56);
		CTime endTime = new CTime(2013,2,22,13,24,56);
		String description = " Project Analysis";
		CLocation location = new CLocation("Canada","Windsor","2920 Peter Street");
		CMeeting meeting = new CMeeting(startTime, endTime, description, location);
		addSchedule(meeting);
		
		CTime startTime1 = new CTime(2014,5,22,10,02,56);
		CTime endTime1 = new CTime(2014,5,22,11,0,56);
		String description1 = "Resource Planning";
		CLocation location1 =  new CLocation("Canada","Toronto","6061 Finch/Young");
		CMeeting meeting1 = new CMeeting(startTime1, endTime1, description1, location1);
		addSchedule(meeting1);
		
		CTime startTime2 = new CTime(2014,5,22,10,32,56);
		CTime endTime2 = new CTime(2014,5,22,11,20,56);
		String description2 = "Fight with Undertaker";
		CLocation location2 = new CLocation("America","Detroit","42 W Warren Ave");
		CMeeting meeting2 = new CMeeting(startTime2, endTime2, description2, location2);
		addSchedule(meeting2);
		
		CTime startTime3 = new CTime(2015,2,16,10,32,56);
		CTime endTime3 = new CTime(2015,2,16,11,20,56);
		String description3 = "Exam Prep Planning";
		CLocation location3 = new CLocation("China","Nanjing","No.22 Gupinggang");
		CMeeting meeting3 = new CMeeting(startTime3, endTime3, description3, location3);
		addSchedule(meeting3);
		
		CTime startTime4 = new CTime(2016,9,22,10,32,56);
		CTime endTime4 = new CTime(2016,9,22,11,20,56);
		String description4 = "Teacher Lecture";
		CLocation location4 = new CLocation("India","Delhi","I.N.A");
		CMeeting meeting4 = new CMeeting(startTime4, endTime4, description4, location4);
		addSchedule(meeting4);
		
		
		sch.SortSchedules();
		sch.SaveSchedules();
		sch.printSchedules();
	}
	
	
}
