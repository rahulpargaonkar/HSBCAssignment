package APIHelper;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import entity.CurrencyConversion;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	private static RequestSpecification requestSpec;

	public  void setRequestSpecification(String baseURI) {

		requestSpec = (RequestSpecification) new RequestSpecBuilder().setBaseUri(baseURI).build();
	}

	public  Response getCurrencyRatesResponse(String uri) {
		// TODO Auto-generated method stub
		Response response = given().spec(requestSpec).when().log().all().get(uri);
		return response;
	}
	
}
