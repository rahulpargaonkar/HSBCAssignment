package APIHelper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import entity.CurrencyConversion;
//import entity.CurrentcyConversion;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class APIHelper {

	
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
