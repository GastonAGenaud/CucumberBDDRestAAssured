package step.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import io.restassured.response.ResponseBody;
import java.net.URI;

import static io.restassured.RestAssured.given;

public class generalSteps {

    private Scenario scenario;
    private Response response;
    private final String BASE_URL = "http://localhost:8080/api/v3";

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }
    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(url));
    }

    @Then("I send a PUT request to {string} and request json:")
    public void iSendAPUTRequestToAndRequestJson(String path, String json) {
        String id = "10";
        String username = "theUser";
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer").header("Content-Type", "application/json");
        Response res = httpRequest.body("{ \"id\": \"" + id + "\", \"username\": \"" + username + "\"}").put(BASE_URL + path);
        System.out.println("The response code - " +res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(),200);
    }

    @Then("I send a PUT request to {string} with query parameter {string}")
    public void iSendAPUTRequestToWithQueryParameter(String path, String parameter) {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer ")
                    .header("Content-Type", "application/json");
            Response res = httpRequest.body(path + parameter).put();
            System.out.println("The response code - " + res.getStatusCode());
            Assert.assertEquals(res.getStatusCode(), 404);
    }



    @Then("I send a GET request to {string} with query parameter {string}")
    public void iSendAGETRequestToWithQueryParameter(String path, String parameter) {
        RestAssured.baseURI = BASE_URL ;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, path + "?" + parameter);
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }

    @Then("I send a GET request to {string}")
    public void iSendAGETRequestTo(String path) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET,path);
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }

    @Then("I send a GET request to {string} with query parameters {string}, {string}")
    public void iSendAGETRequestToWithQueryParameters(String path, String parameter0, String parameter1) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, parameter0, parameter1, parameter1);
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }

    @Then("I send a POST request to {string} with query parameters {string}, {string}, {string}")
    public void iSendAPOSTRequestToWithQueryParameters(String path, String parameter0, String parameter1, String parameter2) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", parameter0);
        requestParams.put("name", parameter1);
        requestParams.put("status", parameter2);
        request.header("Content-Type", "application/xml");
        request.body(requestParams.toString());
        Response response = request.post(path);
        System.out.println("The status received: " + response.statusLine());
    }

    @Then("I send a POST request to {string} with query parameters {string}, {string}")
    public void iSendAPOSTRequestToWithQueryParameters(String path, String arg1, String arg2) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("idd", "TQ123");
        requestParams.put("name", "9781449325862");
        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post(path);
        System.out.println("The status received: " + response.statusLine());
    }

    @Then("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String path) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest =
                RestAssured.given().header("Authorization", "Bearer ").header("Content-Type", "application/json");

        Response res = httpRequest.get();
        ResponseBody body = res.body();
        String rbdy = body.asString();
        System.out.println("Data from the GET API- "+rbdy);
    }

    @Then("I send a DELETE request to {string} with query parameters {string}, {string}")
    public void iSendADELETERequestToWithQueryParameters(String path, String parameter0, String parameter1) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer ").header("Content-Type", "application/json");
        Response res = httpRequest.body("data").delete(path);
        System.out.println("The response code is - " +res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(),200);
    }

    @Then("I send a POST request to {string} and request json:")
    public void iSendAPOSTRequestToAndRequestJson(String path, String json) {
        switch (path) {
            case "/pet" -> {
                RestAssured.baseURI = BASE_URL;
                RequestSpecification request = given();
                JSONObject requestParams = new JSONObject();
                requestParams.put("id", 10);
                requestParams.put("name", "doggie");
                requestParams.put("status", "available");
                request.header("Content-Type", "application/json");
                request.body(json);
                Response response = request.post(path);
                System.out.println("The status received: " + response.statusLine());
            }
            case "/store/order" -> {
                RestAssured.baseURI = BASE_URL;
                RequestSpecification request = given();
                JSONObject requestParams = new JSONObject();
                requestParams.put("id", 10);
                requestParams.put("petId", 198772);
                requestParams.put("quantity", 7);
                requestParams.put("shipDate", "2022-08-02T19:38:29.170Z");
                requestParams.put("status", "approved");
                requestParams.put("complete", true);
                request.header("Content-Type", "application/json");
                request.body(json);
                Response response = request.post(path);
                System.out.println("The status received: " + response.statusLine());
            }
            case "/user" -> {
                RestAssured.baseURI = BASE_URL;
                RequestSpecification request = given();
                JSONObject requestParams = new JSONObject();
                requestParams.put("id", 10);
                requestParams.put("username", "theUser");
                requestParams.put("firstName", "John");
                requestParams.put("lastName", "James");
                requestParams.put("email", "john@email.com");
                requestParams.put("password", "12345");
                requestParams.put("phone", "12345");
                requestParams.put("userStatus", 1);
                request.header("Content-Type", "application/json");
                request.body(json);
                Response response = request.post(path);
                System.out.println("The status received: " + response.statusLine());
            }
            default -> {
                RestAssured.baseURI = BASE_URL;
                RequestSpecification request = given();
                JSONObject requestParams = new JSONObject();
                requestParams.put("id", "10");
                requestParams.put("username", "theUser");
                requestParams.put("firstName", "John");
                requestParams.put("lastName", "James");
                requestParams.put("email", "john@email.com");
                requestParams.put("password", "12345");
                requestParams.put("phone", "12345");
                requestParams.put("userStatus", "1");
                request.header("Content-Type", "application/json");
                request.body(json);
                Response response = request.post(path);
                System.out.println("The status received: " + response.statusLine());
            }
        }
    }
}
