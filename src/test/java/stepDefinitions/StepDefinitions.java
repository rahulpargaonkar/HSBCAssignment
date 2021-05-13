package stepDefinitions;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import client.APIHelper;
import client.RestClient;
import entity.CurrencyConversion;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.response.Response;
import utility.StepHelper;

public class StepDefinitions {
	private final String baseURI = "https://api.ratesapi.io/api/";
	private Response response;
	RestClient restClient;

	@Given("User has base endpoint")
	public void user_has_base_endpoint() {
		restClient = new RestClient();
		restClient.setRequestSpecification(baseURI);
	}

	@When("User calls Rates API with date as {string}")
	public void user_wants_to_call_api_with_date_as(String date) {

		String finalDate = StepHelper.getDate(date);
		response = restClient.getCurrencyRatesResponse(finalDate);
	}

	@When("User calls Rates API with date as {string} and Base as {string} and symbol as {string}")
	public void user_wants_to_call_api_with_date_as_and_base_as_and_symbol_as(String date, String base,
			String symbols) {
		String finalDate = StepHelper.getDate(date);
		String uri = APIHelper.getFinalURI(finalDate, base, symbols);
		response = restClient.getCurrencyRatesResponse(uri);
	}

	@When("User calls Rates API with date as {string} and Base as {string}")
	public void user_wants_to_call_api_with_date_as_and_base_as(String date, String base) {

		String finalDate = StepHelper.getDate(date);
		String uri = APIHelper.getFinalURIWithBase(finalDate, base);
		response = restClient.getCurrencyRatesResponse(uri);
	}

	@When("User calls Rates API with date as {string} and Symbols as {string}")
	public void user_wants_to_call_api_with_date_as_and_symbols_as(String date, String symbols) {

		String finalDate = StepHelper.getDate(date);
		String uri = APIHelper.getFinalURIWithSymbols(finalDate, symbols);
		response = restClient.getCurrencyRatesResponse(uri);
	}

	@Then("Response code should be (\\d+)$")
	public void response_code_should_be(int statusCode) {

		Assert.assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("API should have date {string} in response")
	public void api_should_have_dates(String expectedDate) {

		expectedDate = StepHelper.getDate(expectedDate);
		CurrencyConversion currencyConversion = APIHelper.getObjectFromResponse(response);
		String date = currencyConversion.getDate();
		Assert.assertEquals(expectedDate,date );
	}

	@Then("API should have Base {string} and rates as {string} with some value in response and date as {string}")
	public void api_should_have_base_and_rates_as_with_some_value_in_response_and_date_as(String expectedBase,
			String expectedSymbols, String expectedDate) {

		expectedDate = StepHelper.getDate(expectedDate).toString();
		List<String> actualRates = response.jsonPath().get("rates.collect{it.key}");
		Assert.assertArrayEquals(expectedSymbols.split(","), actualRates.toArray());
		Assert.assertEquals(response.body().jsonPath().get("date"),expectedDate);
		Assert.assertEquals(response.body().jsonPath().get("base"),expectedBase);
	}

	@Then("Response should have error message {string}")
	public void response_should_have_error_message(String message) {

		Assert.assertTrue(response.getBody().asString().contains(message));
	}

	@Then("API should have rates {string} with some value in response")
	public void api_should_have_rates_with_some_value_in_response(String expectedSymbols) {

		List<String> actualRates = response.jsonPath().get("rates.collect{it.key}");

		Assert.assertArrayEquals(expectedSymbols.split(","), actualRates.toArray());
	}

	@Then("API should have Base {string} with some value in response")
	public void api_should_have_base_with_some_value_in_response(String base) {

		response.then().body("base", equalTo(base));
	}

	@Then("API should have Base {string} and rates as {string} with some value in response")
	public void api_should_have_base_and_rates_as_with_some_value_in_response(String expectedBase,
			String expectedSymbols) {
		List<String> actualRates = response.jsonPath().get("rates.collect{it.key}");
		Assert.assertArrayEquals(expectedSymbols.split(","), actualRates.toArray());
		Assert.assertEquals(response.body().jsonPath().get("base"),expectedBase);
	}

	@Then("API response should have date as {string} and validate the response with privoius resposnse")
	public void api_response_should_have_date_as_and_validate_the_response_with_privoius_resposnse(String date) {

		CurrencyConversion currencyConversionLatestAPI = APIHelper.getObjectFromResponse(response);
		String finalDate = StepHelper.getDate(date);
		Response todaysDateAPIResponse = restClient.getCurrencyRatesResponse(finalDate);
		CurrencyConversion currencyConversionDatesApi = APIHelper.getObjectFromResponse(todaysDateAPIResponse);
		Assert.assertEquals(currencyConversionDatesApi, currencyConversionLatestAPI);

	}
	
	@Then("Expected Base,rates values should match with the actual for date {string}")
	public void expected_base_rates_values_should_match_with_the_actual_for_date(String date) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		CurrencyConversion currencyConversionActual = APIHelper.getObjectFromResponse(response);
		CurrencyConversion currencyConversionExpected = APIHelper.getObjectFromJson(date);
		Assert.assertEquals(currencyConversionExpected, currencyConversionActual);
		
		
	}

}
