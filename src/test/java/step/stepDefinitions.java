package step;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class stepDefinitions {

    protected RequestSpecification request;
    protected Response response;

    @Before
    public void before() {
        RestAssured.baseURI = "https://rickandmortyapi.com/api";
    }
    @Given("a user logs into the web")
    public void aUserLogsIntoTheWeb() {
        request = given();
    }
    @When("the user search by name {string}")
    public void theUserSearchByName(String name) {
        response = request
                .queryParam("name", name)
                .when()
                .get("/character/");
    }
    @When("the user search by a wrong name {string}")
    public void theUserSearchByAWrongName(String name) {
        response = request
                .queryParam("name", name)
                .when()
                .get("/character/");
    }
    @When("the user search by id {int}")
    public void theUserSearchById(Integer id) {
        response = request.get("/character/" + id);
    }
    @When("the user search by a wrong id {int}")
    public void theUserSearchByAWrongId(Integer id) {
        response = request.get("/character/" + id);
    }
    @When("the user search by text {string} on the id field")
    public void theUserSearchByTextOnTheIdField(String id) {
        response = request.get("/character/" + id);
    }
    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        response.then().statusCode(statusCode);
    }
    @Then("the error message equals to {string}")
    public void errorMessage(String message) {
        response.then().assertThat().body("error", equalTo(message));
    }
    @Then("the character with name {string} is present")
    public void theCharacterWithNameIsPresent(String name) {
        response.then().assertThat().body("results.name", hasItems(containsStringIgnoringCase(name)));
    }
    @Then("the character with id {int} is present")
    public void theCharacterWithIdIsPresent(Integer id) {
        response.then().assertThat().body("id", equalTo(id));
    }
    @Then("the character response structure is as expected")
    public void theCharacterResponseStructureIsAsExpected() {
        response.then().assertThat()
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("status"))
                .body("$", hasKey("species"))
                .body("$", hasKey("type"))
                .body("$", hasKey("gender"))
                .body("origin", hasKey("name"))
                .body("origin", hasKey("url"))
                .body("location", hasKey("name"))
                .body("location", hasKey("url"))
                .body("$", hasKey("image"))
                .body("$", hasKey("episode"))
                .body("$", hasKey("url"))
                .body("$", hasKey("created"));

    }

}
