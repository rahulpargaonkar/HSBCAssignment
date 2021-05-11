package APIHelper;

import java.time.LocalDate;

import entity.CurrencyConversion;
//import entity.CurrentcyConversion;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class APIHelper {

	public static String getDate(CharSequence date) {
		String finalDate = null;
		if (date.toString().equalsIgnoreCase("latest")) {
			finalDate=LocalDate.now().toString();
		}
		else if(date.toString().equalsIgnoreCase("futureDate")) {
			finalDate=LocalDate.now().plusDays(1).toString();
		}
		else if(date.toString().equalsIgnoreCase("todaysDate")) {
			finalDate=LocalDate.now().toString();
		}
		else {
			finalDate = (String) date;
		}
		return finalDate;
	}

	public static CurrencyConversion getObjectFromResponse(Response response) {
		return response.getBody().as(CurrencyConversion.class,  ObjectMapperType.GSON);
		
	}

	public static String getFinalURI(String finalDate, String base, String symbols) {
		// TODO Auto-generated method stub
		return String.format("%s/?base=%s&symbols=%S", finalDate,base,symbols);
	}

	public static String getFinalURIWithSymbols(String finalDate, String symbols) {
		// TODO Auto-generated method stub
		return String.format("%s/?symbols=%S", finalDate,symbols);
	}
	public static String getFinalURIWithBase(String finalDate, String base) {
		// TODO Auto-generated method stub
		return String.format("%s/?base=%S", finalDate,base);
	}
}