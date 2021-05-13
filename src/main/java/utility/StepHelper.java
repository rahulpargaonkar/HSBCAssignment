package utility;

import java.time.LocalDate;

public class StepHelper {
	public static String getDate(CharSequence date) {
		String finalDate = null;
		if (date.toString().equalsIgnoreCase("todaysDate") ) {
			finalDate=LocalDate.now().toString();
			return finalDate;
		}
		else if(date.toString().equalsIgnoreCase("futureDate")) {
			finalDate=LocalDate.now().plusDays(1).toString();
			return finalDate;
		}
		else if(date.toString().equalsIgnoreCase("yesterdaysDate")){
			finalDate=LocalDate.now().minusDays(1).toString();
			return finalDate;
		}
		
		return date.toString();
	}

}
