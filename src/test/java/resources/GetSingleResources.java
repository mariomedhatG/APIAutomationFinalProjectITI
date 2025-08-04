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
                when().get(baseUrl + "api/unknown/2").
                then().extract().response();

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode() , 200);
    }
}
