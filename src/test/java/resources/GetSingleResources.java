package resources;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static utility.Constant.*;
public class GetSingleResources extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void SingleResources(){
        Response response = RestAssured.
                given().spec(getRequestSpecWithHeaders()).
                when().get(baseUrl + SingleListEndPoint).
                then().extract().response();

        String body = response.getBody().asPrettyString();
        System.out.println(body);
        int id = response.jsonPath().getInt("data.id");
        String name = response.jsonPath().getString("data.name");
        String year = response.jsonPath().getString("data.year");
        String color = response.jsonPath().getString("data.color");
        String pantoneValue = response.jsonPath().getString("data.pantone_value");

        Assert.assertEquals(id, SingleResource, "Resource ID mismatch");
        Assert.assertNotNull(name, "Name is null");
        Assert.assertNotNull(year, "Year is null");
        Assert.assertNotNull(color, "Color is null");
        Assert.assertNotNull(pantoneValue, "Pantone Value is null");
    }

    @Test
    public void getSingleResource(){
        Response response =  RestAssured.given().spec(getRequestSpecWithHeaders())
                .when().get(baseUrl + SingleListEndPoint)
                .then().extract().response();
        Assert.assertEquals(response.statusCode(),200);
    }
}
