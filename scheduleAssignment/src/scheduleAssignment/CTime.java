package scheduleAssignment;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class CTime implements Serializable{
	private static final long serialVersionUID = 1L;
	Calendar calendar;
	// format : Year Month Day Hour Minute Second  
	public CTime() {
	
}
	public CTime(int yy, int M, int dd, int hh, int mm, int ss) {
		
			this.calendar = new GregorianCalendar(yy,M,dd,hh,mm,ss);
		
	}
	public boolean setCTime(int yy, int M, int dd, int hh, int mm, int ss){
		if(isValidTime( yy,  M,  dd,  hh,  mm,  ss)) {
			this.calendar = new GregorianCalendar(yy,M,dd,hh,mm,ss);
			return true;
		}
			
		else return false;
	}
	
	public boolean isValidTime(int yy, int M, int dd, int hh, int mm, int ss){
			if((M<0||M>11)||(dd<0||dd>31)||(hh<0||hh>23)||(ss<0||ss>59)){
				return false;
			}else return true;
	}
	
	
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		return sdf.format(calendar.getTime());
		
	}
	public void setTime(int yy, int M, int dd, int hh, int mm, int ss) {
		Calendar temp = new GregorianCalendar(yy,M,dd,hh,mm,ss);
		this.calendar = temp;
	}
	public void  getTimeVerbose() {		
		int year       = calendar.get(Calendar.YEAR);
		int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); 
		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

		int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
		int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		int minute     = calendar.get(Calendar.MINUTE);
		int second     = calendar.get(Calendar.SECOND);
		int millisecond= calendar.get(Calendar.MILLISECOND);

		System.out.println("year \t\t: " + year);
		System.out.println("month \t\t: " + month);
		System.out.println("dayOfMonth \t: " + dayOfMonth);
		System.out.println("dayOfWeek \t: " + dayOfWeek);
		System.out.println("weekOfYear \t: " + weekOfYear);
		System.out.println("weekOfMonth \t: " + weekOfMonth);

		System.out.println("hour \t\t: " + hour);
		System.out.println("hourOfDay \t: " + hourOfDay);
		System.out.println("minute \t\t: " + minute);
		System.out.println("second \t\t: " + second);
		System.out.println("millisecond \t: " + millisecond);
	}
	public int compare(CTime anotherCalendar) {
		int output = this.calendar.compareTo(anotherCalendar.calendar);
		if (output == 0) {
			System.out.println("Comparing start and end time :SAME");
		}
		else { 
			if(output < 0) {
				System.out.println("Comparing start and end time :Another Calendar is after");
			}
			else { 
				if(output > 0) {
					System.out.println("Comparing start and end time :Another Calendar is earlier");
				}
			}
		}	
		return output;
	}

	public boolean checkStartEnd(int sthh, int stmm, int endhh, int endmm)
	{
		CTime strt = new CTime(14,10,26, sthh, stmm,00);
		CTime endt = new CTime(14,10,26, endhh, endmm,00);
		CSchedule sch = new CSchedule();
		if(sch.IsTimeValid(strt, endt))
		{
			if(sch.IsTimeDifferenceValid(strt, endt))
			{
				return true;
			}else return false;
			
		}else return false;
	
	}
	
	
//	// the work that I will implement in this will work every where
	public static void main(String[] args) {
		CTime a = new CTime(2013,15,28,13,24,56);
		CTime b = new CTime(2013,0,28,13,24,55);
		a.getTimeVerbose();
		a.getTime();
		a.compare(b);
		a.setTime(2014,8,16,13,24,55);
		a.getTime();
	}

}



