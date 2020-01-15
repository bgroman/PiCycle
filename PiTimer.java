import java.util.Calendar;

public class PiTimer {

	//day of year when the first week of the semester starts (specifically, that Sunday)
	public static int START_DAY = 26;
	
	private final int targetWeek;
	private final int targetDay;
	private final int targetHour;
	private final int targetMinute;
	
	private int weeksRemaining;
	private int daysRemaining;
	private int hoursRemaining;
	private int minutesRemaining;
	
	/**
	 * Parameters represent the target time. Each will be modulated to the range specified below.
	 * @param week [0, 15] (of semester - 0 is first week, 15 is finals)
	 * @param day [0, 6] (of week - 0 is Sunday)
	 * @param hour [0, 23] (military time - 0 is midnight)
	 * @param minute [0, 59] (0 is on the hour)
	 */
	public PiTimer(int week, int day, int hour, int minute) {
		targetWeek = week % 16;
		targetDay = day % 7;
		targetHour = hour % 24;
		targetMinute = minute % 60;
	}
	
	/**
	 * Sets the "current" fields to match the current time. 
	 */
	public void update() {
		Calendar cal = Calendar.getInstance();
		int yearDay = cal.get(Calendar.DAY_OF_YEAR); // 1 based
		int semesterDay = yearDay - START_DAY; // now 0 based
		//days times hours/day times minutes/hour
		int currentMinutes = semesterDay * 24 * 60;
		//add hours times minutes/hour
		currentMinutes += cal.get(Calendar.HOUR_OF_DAY) * 60;
		//add the minutes themselves
		currentMinutes += cal.get(Calendar.MINUTE);
		
		//days - 7 for each week, plus the specified days
		int targetSemesterDay = (7 * targetWeek) + targetDay;
		//hours - 24 for each day, plus the specified hours
		int targetSemesterHours = (targetSemesterDay * 24) + targetHour;
		//minutes - 60 for each hour, plus the specified minutes
		int targetSemesterMinutes = (targetSemesterHours * 60) + targetMinute;
		
		int remainingSemesterMinutes = targetSemesterMinutes - currentMinutes;
		//handles going past the target time
		if (remainingSemesterMinutes < 0) {
			weeksRemaining = -1;
			daysRemaining = -1;
			hoursRemaining = -1;
			minutesRemaining = -1;
			return;
		}
		//divide by minutes per week
		weeksRemaining = remainingSemesterMinutes / (60 * 24 * 7);
		//modulo out the weeks, divide by minutes per day
		daysRemaining = (remainingSemesterMinutes % (60 * 24 * 7)) / (60 * 24);
		//modulo out the days, divide by minutes per hour
		hoursRemaining = (remainingSemesterMinutes % (60 * 24)) / (60);
		//modulo out the hours
		minutesRemaining = remainingSemesterMinutes % 60;
	}
	
	public String getRemaining() {
		String out = "";
		//weeks
		if (weeksRemaining > 1) {
			out += weeksRemaining + " Weeks;";  
		}
		else if (weeksRemaining == 1) {
			out += weeksRemaining + " Week;";
		}
		//days
		if (daysRemaining > 1) {
			out += daysRemaining + " Days;";
		}
		else if (daysRemaining == 1) {
			out += daysRemaining + " Day;";
		}
		//hours
		if (hoursRemaining > 1) {
			out += hoursRemaining + " Hours;";
		}
		else if (hoursRemaining == 1) {
			out += hoursRemaining + " Hour;";
		}
		//minutes
		if (minutesRemaining > 1) {
			out += minutesRemaining + " Minutes;";
		}
		else if (minutesRemaining == 1) {
			out += minutesRemaining + " Minute;";
		}
		if (out.isEmpty()) {
			//handle reaching the target
			out = "ALREADY STARTED";
		}
		else {
			//remove last semicolon
			out = out.substring(0, out.length() - 1);
			//fix punctuation
			out = out.replace(";", ", ");
		}
		return out;
	}

}
