package scheduleAssignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


public class CSchedule implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<CMeeting> meetings = new ArrayList<CMeeting>();
	public ArrayList<CMeeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(ArrayList<CMeeting> meetings) {
		this.meetings = meetings;
	}
	private static final String schedule_filename ="SCHEDULES.ser";
	
	public CSchedule(){
		if (!ScheduleExists()){ 
			CreateTempSchedule();
			
		}
		else { 
			ReadSchedules();
		}
		SortSchedules();
		SaveSchedules();
	}

	public void SortSchedules() {
		Collections.sort(meetings);
	}


	private void CreateTempSchedule() {
		
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
	}

	
	public void printSchedules() {
		System.out.println("       Start Time :    |" + "       End Time :      |" + "     Description :       |" + "        Location : ");
		for(CMeeting x : meetings) {
			System.out.println(" " + x.getStartTime() + "  | " + x.getEndTime() + "  |    " 
					+ String.format("%-20s",x.getDescription()) + " |    " 
					+ x.getLocation().getAddress()+" "+x.getLocation().getCity()+" "+x.getLocation().getCountry());
		}
	}
	

	public void addSchedule(CMeeting newMeeting) {
		if(IsTimeValid(newMeeting.getstartCTime(), newMeeting.getendCTime())){
			
			// add only when time difference b/w schedule is =<1
			if(IsTimeDifferenceValid(newMeeting.getstartCTime(), newMeeting.getendCTime())) {
				if(IsNoOverlap(newMeeting.getstartCTime(), newMeeting.getendCTime()) == true) {
					meetings.add(newMeeting);	
				}
				else {
					System.out.println(" Meeting is overlapped: " + newMeeting.getDescription());
				}
			}else {
				
				System.out.println(" Start time should be eariler than end time : " + newMeeting.getDescription());
			}
		}
		else{
			System.out.println(" Length of meeting excceeds 1 hour : " + newMeeting.getDescription());
		}
		System.out.println("-----------------------------------------------");
		SortSchedules();
		SaveSchedules();
	}
	
	
	public boolean IsTimeValid(CTime start, CTime end) {
		
		if(start.compare(end) >= 0) {
			return false;
		}else return true;
	}
	
	public boolean IsTimeDifferenceValid(CTime start, CTime end) {
		if(start.compare(end) < 0) {
			long difference = end.calendar.getTimeInMillis() - start.calendar.getTimeInMillis();
			double x = difference / 60000.0;
			System.out.println("IsTimeDifferenceValid :time difference : " + x + " mins");
			if( x <=60  ) {
				System.out.println("Time difference is valid. Checked");
				return true;
			}
		}
		return false;
	}

	public boolean IsNoOverlap(CTime newStart, CTime newEnd) {
		boolean flag= true;

		for(CMeeting pre : meetings) {
		//System.out.println("IAMHERE : " + pre.getDescription());
		int x = newEnd.calendar.compareTo(pre.getstartCTime().calendar);
		int y = newStart.calendar.compareTo(pre.getendCTime().calendar);
		if(x<0 || x == 0 || y > 0 || y == 0) {
			flag = true;
			continue;
		}
		else{
			flag = false;
			break;
		}
	}
	if(flag == true) {
		return true;
	}	
		System.out.println("After clash check : " + flag);
		return false;
	}
	
	
	public void removeSchedule(CMeeting toRemove) {
		meetings.remove(toRemove);
	}
	
	public static void main(String[] arg) {
		CSchedule c = new CSchedule();
		c.printSchedules();
		
	}
	// checks if Schedules file exist or not
    private boolean ScheduleExists(){
        File fn = new File( schedule_filename );
        System.out.println("File Exist : " + fn.exists());   
        return fn.exists();
    }
    //  saves schedule to file
    public void SaveSchedules(){
    	
        // create file
        ObjectOutputStream output_schedule=null;
        try{
            output_schedule = new ObjectOutputStream(new FileOutputStream( schedule_filename ));
        }
        catch(IOException ioException){
            System.out.println("Error opening file: while creating file ");
        }
        
        // write schedules
        try{
        	
        		output_schedule.writeObject(meetings);
        		output_schedule.flush();
//            for (CMeeting r : meetings){
//                output_schedule.writeObject(r);
//                output_schedule.flush();
//            }
            
        }
        catch(IOException ioException){
            System.out.println("Error writing to file : while write operation into file");
            return;
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        // close file
        finally {
	        try{
	            if (output_schedule != null)
	                output_schedule.close();
	        }
	        catch(IOException ex){
	            System. out .println("Error closing file");
	            System. exit (1);
	        }
        }
    }
    public void ReadSchedules(){
        ObjectInputStream input_schedules=null;
        try{
            input_schedules = new ObjectInputStream(new FileInputStream( schedule_filename ));
        }
        catch(IOException ex){
            System. out .println("Error opening file");
        };
        try{
        	meetings = (ArrayList<CMeeting>)  input_schedules.readObject();
//           for(CMeeting cm = (CMeeting)input_schedules.readObject();cm!=null; cm = (CMeeting)input_schedules.readObject()){
//        	   meetings.add(cm);
//           }
        }
        catch(Exception ex){
        	ex.printStackTrace();
            System. out .println("Error reading from file");
        }
        //close file
        finally {
	        try{
	            if (input_schedules != null)
	                input_schedules.close();
	        }
	        catch(IOException ex){
	            System. out .println("Error closing file");
	            System. exit (1);
	        }
        }
    }
    public String[] getAllSchedules(){
        String[] schedulename = new String[meetings.size()];
        int idx=0;
        for(CMeeting r : meetings){
            schedulename[idx]= r.getStartTime() + r.getEndTime() + r.getDescription() + r.getLocation();
            idx++;
        }
        return schedulename;
    }
}
