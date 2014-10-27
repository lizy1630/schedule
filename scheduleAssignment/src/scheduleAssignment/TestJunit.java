package scheduleAssignment;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestJunit {
	
	
	
	@Test
	public void testTimeDigit()
	{
		
		CTime ct = new CTime();
		assertFalse(ct.setCTime(12, 12, 0, 0, 0, 0));
		
	}
	@Test
	public void testCMeeting()
	{
		CSchedule cs = new CSchedule();
		CTime sta = new CTime();
		CTime end = new CTime();
		sta.setCTime(2013,2,22,14,10,56);
		end.setCTime(2013,2,22,13,24,56);
		assertFalse(cs.IsTimeValid(sta, end));
//		String description = " Project Analysis";
//		CLocation location = new CLocation("Canada","Windsor","2920 Peter Street");
//		CMeeting meeting = new CMeeting(sta, end, description, location);
//		cs.addSchedule(meeting);
		
		sta.setCTime(2013,2,22,13,10,56);
		end.setCTime(2013,2,22,14,24,56);
		assertFalse(cs.IsTimeDifferenceValid(sta, end));
		
	}
	
	
	@Test
	public void testCSchedule()
	{
		CSchedule cs = new CSchedule();
		CTime sta = new CTime();
		CTime end = new CTime();
		sta.setCTime(2010,2,12,13,10,56);
		end.setCTime(2010,2,12,13,34,56);
		String description = " Software Analysis";
		CLocation location = new CLocation("Canada","Sudbury","XX Street");
		CMeeting meeting = new CMeeting(sta, end, description, location);
		cs.addSchedule(meeting);
		
		sta.setCTime(2014,11,12,13,10,56);
		end.setCTime(2014,11,12,13,34,56);
		description = " Software Engeering";
		location = new CLocation("France","Paris","ZZ Road");
		meeting = new CMeeting(sta, end, description, location);
		cs.addSchedule(meeting);
		cs.SaveSchedules();
		cs.printSchedules();
		assertTrue(cs.readScheduleSize()>2);
		
	}
	
	
	@Test
	public void testOverlap()
	{
		CSchedule cs = new CSchedule();
		CTime sta = new CTime();
		CTime end = new CTime();
		sta.setCTime(2014,2,12,13,10,56);
		end.setCTime(2014,2,12,13,34,56);
		String description = " Software Testing";
		CLocation location = new CLocation("Canada","London","XX Street");
		CMeeting meeting = new CMeeting(sta, end, description, location);
		cs.addSchedule(meeting);
		
		sta = new CTime();
		end = new CTime();
		sta.setCTime(2014,2,12,13,24,56);
		end.setCTime(2014,2,12,13,44,56);
//		description = " Software Engeering";
//		location = new CLocation("France","Paris","ZZ Road");
//		meeting = new CMeeting(sta, end, description, location);
//		cs.addSchedule(meeting);
		assertFalse(cs.IsNoOverlap(sta, end));
		
	}
}
