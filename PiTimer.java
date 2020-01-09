import java.util.Calendar;

public class PiTimer {

	//day of year when the first week of the semester starts (specifically, that Sunday)
	public static int START_DAY = 26;
	
	private final int targetWeek;
	private final int targetDay;
	private final int targetHour;
	private final int targetMinute;
	
	private int currentWeek;
	private int currentDay;
	private int currentHour;
	private int currentMinute;
	
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
		currentWeek = semesterDay / 7;
		currentDay = semesterDay % 7;
		currentHour = cal.get(Calendar.HOUR_OF_DAY);
		currentMinute = cal.get(Calendar.MINUTE);
	}
	
	public String getRemaining() {
		String out = "";
		int weeksRemaining = targetWeek - currentWeek;
		int daysRemaining = targetDay - currentDay;
		int hoursRemaining = targetHour - currentHour;
		int minutesRemaining = targetMinute - currentMinute;
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
