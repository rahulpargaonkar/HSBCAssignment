package stepDefinitions;

import java.util.List;

import org.junit.Assert;

import APIHelper.APIHelper;
import APIHelper.RestHelper;
import entity.CurrencyConversion;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.response.Response;

public class StepDefinitions {
	private String baseURI = "https://api.ratesapi.io/api/";
	private Response response;

	@Given("user has base endpoint")
	public void user_has_base_endpoint() {
		// Write code here that turns the phrase above into concrete actions
		RestHelper.setRequestSpecification(baseURI);
	}

	@When("user wants to call API with date as {string}")
	public void user_wants_to_call_api_with_date_as(String date) {
		// Write code here that turns the phrase above into concrete actions
		String finalDate = APIHelper.getDate(date);
		response = RestHelper.callAPI(finalDate);
	}

	@When("user wants to call API with date as {string} and Base as {string} and symbol as {string}")
	public void user_wants_to_call_api_with_date_as_and_base_as_and_symbol_as(String date, String base,
			String symbols) {
		String finalDate = APIHelper.getDate(date);
		String uri = APIHelper.getFinalURI(finalDate, base, symbols);
		response = RestHelper.callAPI(uri);
	}

	@When("user wants to call API with date as {string} and Base as {string}")
	public void user_wants_to_call_api_with_date_as_and_base_as(String date, String base) {
		// Write code here that turns the phrase above into concrete actions
		String finalDate = APIHelper.getDate(date);
		String uri = APIHelper.getFinalURIWithBase(finalDate, base);
		response = RestHelper.callAPI(uri);
	}

	@When("user wants to call API with date as {string} and Symbols as {string}")
	public void user_wants_to_call_api_with_date_as_and_symbols_as(String date, String symbols) {
		// Write code here that turns the phrase above into concrete actions
		String finalDate = APIHelper.getDate(date);
		String uri = APIHelper.getFinalURIWithSymbols(finalDate, symbols);
		response = RestHelper.callAPI(uri);
	}

	@Then("response code should be (\\d+)$")
	public void response_code_should_be(int statusCode) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("API should have dates {string}")
	public void api_should_have_dates(String expectedDate) {
		// Write code here that turns the phrase above into concrete actions
		expectedDate = APIHelper.getDate(expectedDate);
		CurrencyConversion currencyConversion = APIHelper.getObjectFromResponse(response);
		String date = currencyConversion.getDate();
		Assert.assertEquals(date, expectedDate);
	}

	@Then("API should have Base {string} and rates as {string} with some value in response and date as {string}")
	public void api_should_have_base_and_rates_as_with_some_value_in_response_and_date_as(String expectedBase,
			String expectedSymbols, String expectedDate) {
		// Write code here that turns the phrase above into concrete actions
		expectedDate = APIHelper.getDate(expectedDate).toString();
		CurrencyConversion currencyConversion = APIHelper.getObjectFromResponse(response);
		String base = currencyConversion.getBase();
		String date = currencyConversion.getDate();

		List<String> actualRates = response.jsonPath().get("rates.collect{it.key}");
		Assert.assertTrue(actualRates.stream().allMatch(rateKey -> expectedSymbols.contains(rateKey)));
		Assert.assertEquals(date, expectedDate);
		Assert.assertEquals(base, expectedBase);
	}

	@Then("response should have error message {string}")
	public void response_should_have_error_message(String message) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(response.getBody().asString().contains(message));
	}

	@Then("API should have rates {string} with some value in response")
	public void api_should_have_rates_with_some_value_in_response(String expectedSymbols) {
		// Write code here that turns the phrase above into concrete actions
		List<String> actualRates = response.jsonPath().get("rates.collect{it.key}");
		System.out.println(actualRates);
		Assert.assertTrue(actualRates.stream().allMatch(rateKey -> expectedSymbols.contains(rateKey)));

	}

	@Then("API should have Base {string} with some value in response")
	public void api_should_have_base_with_some_value_in_response(String base) {
		// Write code here that turns the phrase above into concrete actions

		response.then().body("base", equalTo(base));
	}

	@Then("API should have Base {string} and rates as {string} with some value in response")
	public void api_should_have_base_and_rates_as_with_some_value_in_response(String expectedBase, String expectedSymbols) {
		// Write code here that turns the phrase above into concrete actions
		CurrencyConversion currencyConversion = APIHelper.getObjectFromResponse(response);
		String base = currencyConversion.getBase();
		String date = currencyConversion.getDate();
		List<String> actualRates = response.jsonPath().get("rates.collect{it.key}");
		Assert.assertTrue(actualRates.stream().allMatch(rateKey -> expectedSymbols.contains(rateKey)));
		Assert.assertEquals(base, expectedBase);
	}

	@Then("API response should have date as {string} and validate the response with privoius resposnse")
	public void api_response_should_have_date_as_and_validate_the_response_with_privoius_resposnse(String date) {
		// Write code here that turns the phrase above into concrete actions
		CurrencyConversion currencyConversionLatestAPI = APIHelper.getObjectFromResponse(response);
		String finalDate = APIHelper.getDate(date);
		Response todaysDateAPIResponse = RestHelper.callAPI(finalDate);
		CurrencyConversion currencyConversionDatesApi = APIHelper.getObjectFromResponse(todaysDateAPIResponse);
		Assert.assertEquals(currencyConversionDatesApi, currencyConversionLatestAPI);

	}

}
