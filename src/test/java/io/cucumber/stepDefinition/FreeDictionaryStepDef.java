package io.cucumber.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FreeDictionaryStepDef {

    private Response response;

    @Given("I have the dictionary API endpoint")
    public void iHaveTheDictionaryAPIEndpoint() {
        RestAssured.baseURI = "https://api.dictionaryapi.dev/api/v2/entries/en";
    }

    @When("I send a GET request for {string}")
    public void sendsGETRequestToFreeDictionaryAPI(String word) {
        response = given().log().all().when().get("/" + word);
    }

    @Then("API call got success with status code {int}")
    public void apiCallGotSuccessWithStatusCode(int statusCode) {
        response.then().log().all().assertThat().statusCode(statusCode);
    }

    @And("the response should contain the word {string}")
    public void verifyResponseBody(String word) {
        response.then().assertThat().body("[0].word", equalTo(word));
    }
}
