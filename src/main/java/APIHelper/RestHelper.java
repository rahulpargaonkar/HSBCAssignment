package APIHelper;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestHelper {
	private static RequestSpecification requestSpec;

	public static void setRequestSpecification(String baseURI) {

		requestSpec = (RequestSpecification) new RequestSpecBuilder().setBaseUri(baseURI).build();
	}

	public static Response callAPI(String date) {
		// TODO Auto-generated method stub
		Response response = given().spec(requestSpec).when().log().all().get(date);
		System.out.println(response.getBody().asString());
		return response;
	}

}
