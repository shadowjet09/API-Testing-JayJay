package apiauto;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.apache.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class APITest {

    private static String URI = "https://reqres.in/api/users";

    @BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = URI;
    }

    // Tes GET
    @Test
    public void testGet() {
        Response response = RestAssured.given().when().get(URI + "?page=2");
        int statusCode = response.getStatusCode();
        System.out.println("GET Status Code: " + statusCode);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(response.getBody().asString().contains("michael.lawson@reqres.in"));
        Assert.assertTrue(response.getBody().asString().contains("lindsay.ferguson@reqres.in"));
    }

    // Tes POST
    @Test
    public void testPost() {
        String json = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(json).when().post(URI);
        int statusCode = response.getStatusCode();
        System.out.println("POST Status Code: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);
    }

    // Tes PUT
    @Test
    public void testPut() {
        String json = "{\"name\":\"morpheus\",\"job\":\"manager\"}";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(json).when().put(URI + "/2");
        int statusCode = response.getStatusCode();
        System.out.println("PUT Status Code: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    // Tes PATCH
    @Test
    public void testPatch() {
        String json = "{\"name\":\"morpheus\",\"job\":\"manager\"}";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(json).when().patch(URI + "/2");
        int statusCode = response.getStatusCode();
        System.out.println("PATCH Status Code: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    // Tes DELETE
    @Test
    public void testDelete() {
        Response response = RestAssured.given().when().delete(URI + "/2");
        int statusCode = response.getStatusCode();
        System.out.println("DELETE Status Code: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_NO_CONTENT);
    }
}

