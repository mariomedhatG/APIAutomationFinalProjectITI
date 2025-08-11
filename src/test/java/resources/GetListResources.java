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
public class GetListResources extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void ListResources(){
        Response response = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .when().get( baseUrl + ListEndPoint)
                .then().extract().response();

        String body = response.getBody().asPrettyString();
        System.out.println(body);
        int id = response.jsonPath().getInt("data[0].id");
        String name = response.jsonPath().getString("data[0].name");
        String year = response.jsonPath().getString("data[0].year");
        String color = response.jsonPath().getString("data[0].color");
        String pantoneValue = response.jsonPath().getString("data[0].pantone_value");

        Assert.assertTrue(id > 0, "Resource ID should be greater than 0");
        Assert.assertNotNull(name, "Name is null");
        Assert.assertNotNull(year, "Year is null");
        Assert.assertNotNull(color, "Color is null");
        Assert.assertNotNull(pantoneValue, "Pantone Value is null");
    }
}
