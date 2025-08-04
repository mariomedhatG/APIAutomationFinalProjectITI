package login;

import org.testng.annotations.BeforeMethod;
import utility.Payload;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static utility.Constant.*;
import static utility.Constant.contentTypeValue;

public class PostLoginUnsuccessFull extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void LoginUnsuccessFull (){
        SoftAssert softAssert = new SoftAssert();

        Response response = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.InvalidLoginBody())
                .when().post(baseUrl + "api/login")
                .then().statusCode(400).extract().response();

        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        Assert.assertEquals(jsonPath.get("error") , "Missing password");
        softAssert.assertEquals(response.getStatusCode() , 400);
        softAssert.assertAll();

        System.out.println(response);
    }
}
