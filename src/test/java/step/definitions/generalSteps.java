package step.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import io.restassured.response.ResponseBody;

import java.io.File;
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
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given().headers("accept", "*/*","Content-Type", "application/json");
        Response res = httpRequest.body(json).when().put(path);
        System.out.println("The response code - " +res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(),200);
        System.out.println("Response=>" + res.body().prettyPrint());
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

    @Then("I send a POST request to {string}")
    public void iSendAPOSTRequestToWithQueryParameters(String path) {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/Uploads/test.jpg");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = given();
        request.config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(true)));
        request.contentType("image/jpg");
        request.header("accept", "application/json");
        Response response = request.body(new byte[]{42}).when().post(path);
        System.out.println("The status received: " + response.statusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }

    @Then("I send a POST request to {string} with query parameters {string}, {string}")
    public void iSendAPOSTRequestToWithQueryParameters(String path, String arg1, String arg2) {
        if ("/pet/10".equals(path)) {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = given();
            JSONObject requestParams = new JSONObject();
            requestParams.put("name", arg1);
            requestParams.put("status", arg2);
            request.header("Content-Type", "application/json");
            request.body(requestParams.toString());
            Response response = request.post(path + "?" + "name=" + arg1 + "&" + "status=" + arg2);
            System.out.println("The status received: " + response.statusLine());
            System.out.println("Response=>" + response.prettyPrint());
        } else {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = given();
            JSONObject requestParams = new JSONObject();
            requestParams.put("idd", "TQ123");
            requestParams.put("name", "9781449325862");
            request.header("Content-Type", "application/json");
            request.body(requestParams.toString());
            Response response = request.post(path);
            System.out.println("The status received: " + response.statusLine());
            System.out.println("Response=>" + response.prettyPrint());
        }
    }

    @Then("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String path) {
        if ("/user/user1".equals(path)) {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification httpRequest = given().header("Authorization", "Bearer ").header("Content-Type", "application/json");
            Response res = httpRequest.get(path);
            ResponseBody body = res.body();
            String rbdy = body.asString();
            System.out.println("Data from the GET API- " + rbdy);
            Assert.assertEquals(res.getStatusCode(), 200);
            System.out.println("Response=>" + res.prettyPrint());
        } else {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification httpRequest = given().header("Authorization", "Bearer ").header("Content-Type", "application/json");
            Response res = httpRequest.get(path);
            ResponseBody body = res.body();
            String rbdy = body.asString();
            System.out.println("Data from the GET API- " + rbdy);
            Assert.assertEquals(res.getStatusCode(), 404);
            System.out.println("Response=>" + res.prettyPrint());
        }

    }

    @Then("I send a DELETE request to {string} with query parameters {string}, {string}")
    public void iSendADELETERequestToWithQueryParameters(String path, String parameter0, String parameter1) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer ").header("Content-Type", "application/json");
        Response res = httpRequest.body("data").delete(path);
        System.out.println("The response code is - " +res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(),200);
        System.out.println("Response=>" + res.prettyPrint());
    }

    @Then("I send a POST request to {string} and request json:")
    public void iSendAPOSTRequestToAndRequestJson(String path, String json) {
        switch (path) {
            case "/pet" -> {
                RestAssured.baseURI = BASE_URL;
                RequestSpecification request = given();
                JSONObject jsonObj = new JSONObject(json);
                request.header("Content-Type", "application/json");
                request.body(json);
                Response response = request.post(path);
                System.out.println("The status received: " + response.statusLine());
                Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
                System.out.println("Response=>" + response.body().prettyPrint());
                System.out.println("Headers=>" + response.headers());
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
                Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
                System.out.println("Response=>" + response.body().prettyPrint());
                System.out.println("Headers=>" + response.headers());
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
                Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
                System.out.println("Response=>" + response.body().prettyPrint());
                System.out.println("Headers=>" + response.headers());
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
                Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
                System.out.println("Response=>" + response.body().prettyPrint());
                System.out.println("Headers=>" + response.headers());
            }
        }
    }
}
