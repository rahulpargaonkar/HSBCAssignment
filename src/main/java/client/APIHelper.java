package client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import entity.CurrencyConversion;
//import entity.CurrentcyConversion;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class APIHelper {

	
	public static CurrencyConversion getObjectFromResponse(Response response) {
		return response.getBody().as(CurrencyConversion.class,  ObjectMapperType.GSON);
		
	}

	public static String getFinalURI(String finalDate, String base, String symbols) {
		
		return String.format("%s/?base=%s&symbols=%S", finalDate,base,symbols);
	}

	public static String getFinalURIWithSymbols(String finalDate, String symbols) {

		return String.format("%s/?symbols=%S", finalDate,symbols);
	}
	public static String getFinalURIWithBase(String finalDate, String base) {
		
		return String.format("%s/?base=%S", finalDate,base);
	}

	public static CurrencyConversion getObjectFromJson(String date) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		return gson.fromJson(new FileReader("./src/test/resources/testData/" + date+".json"), CurrencyConversion.class);
	}
}
