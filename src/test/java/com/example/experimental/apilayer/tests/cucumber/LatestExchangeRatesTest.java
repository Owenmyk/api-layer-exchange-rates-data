package com.example.experimental.apilayer.tests.cucumber;

import com.example.experimental.apilayer.api.RestBase;
import com.example.experimental.apilayer.dto.response.GetLatestResponse;
import com.example.experimental.apilayer.utils.ConfigLoader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static com.example.experimental.apilayer.api.PathConstants.LATEST;

public class LatestExchangeRatesTest {
    String apiKey;
    String incorrect_path;
    Response response;
    String datePattern = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

    @Given("Invalid api key is provided")
    public void invalidApiKeyIsProvided() {
        apiKey = "ABC";
    }

    @Given("Empty api key is provided")
    public void emptyApiKeyIsProvided() {
        apiKey = "";
    }

    @Given("Valid api key is provided")
    public void validApiKey() {
        apiKey = ConfigLoader.getInstance().getApiKey();
    }

    @Given("Incorrect api url is provided")
    public void incorrectApiUrlIsProvided() {
        apiKey = ConfigLoader.getInstance().getApiKey();
        incorrect_path = "?!";
    }

    @When("User call latest API with single parameter {string} = {string}")
    public void callApiWithSingleParameter(String parameter, String value) {
        response = RestBase.get(apiKey, LATEST, Map.of(parameter, value));
    }

    @When("User call latest API with both parameters base = {string} symbols = {string}")
    public void callApiWithMultipleParameters(String baseValue, String symbolsValue) {
        response = RestBase.get(apiKey, LATEST, Map.of("base", baseValue, "symbols", symbolsValue));
    }

    @When("User call latest API without parameters")
    public void callApiWithoutParameters() {
        response = RestBase.get(apiKey, LATEST);
    }

    @When("User call incorrect API")
    public void userCallIncorrectAPI() {
        response = RestBase.get(apiKey, incorrect_path);
    }

    @Then("Status code {int} is returned")
    public void statusCodeIsReturned(int status_code) {
        Assert.assertEquals(status_code, response.getStatusCode());
    }

    @Then("Call is successful and default values are returned")
    public void apiReturnsDefaultValues() {
        GetLatestResponse getLatestResponse = response
                .then()
                .statusCode(200)
                .extract()
                .as(GetLatestResponse.class);
        Assert.assertTrue(getLatestResponse.isSuccess());
        Assert.assertEquals(getLatestResponse.getBase(), "EUR");
        Assert.assertTrue(getLatestResponse.getRates().size() > 0);
        Assert.assertTrue(getLatestResponse.getTimestamp() > 0);
        Assert.assertTrue(getLatestResponse.getDate().matches(datePattern));
    }

    @And("Returned data for parameter {string} = {string} is correct")
    public void apiReturnsCorrectValuesForSingleParameter(String parameter, String value) {
        GetLatestResponse getLatestResponse = response.as(GetLatestResponse.class);
        if (parameter.equals("base")) {
            Assert.assertEquals(getLatestResponse.getBase(), value);
        } else {
            for (String symbol : getLatestResponse.getRates().keySet()) {
                Assert.assertTrue(value.contains(symbol));
            }
            for (Double rate : getLatestResponse.getRates().values()) {
                Assert.assertTrue(rate > 0);
            }
        }


        Assert.assertTrue(getLatestResponse.getRates().size() > 0);
    }

    @And("Returned data for parameters base = {string} symbols = {string} is correct")
    public void apiReturnsCorrectValuesForMultipleParameters(String baseValue, String symbolsValue) {
        GetLatestResponse getLatestResponse = response.as(GetLatestResponse.class);
        Assert.assertEquals(getLatestResponse.getBase(), baseValue.toUpperCase());
        for (String symbol : getLatestResponse.getRates().keySet()) {
            Assert.assertTrue(symbolsValue.contains(symbol));
        }
        for (Double rate : getLatestResponse.getRates().values()) {
            Assert.assertTrue(rate > 0);
        }

    }

}
