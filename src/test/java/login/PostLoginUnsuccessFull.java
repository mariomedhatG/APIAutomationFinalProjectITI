package login;

import models.LoginPayLoad;
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
    LoginPayLoad login;
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        login = new LoginPayLoad();
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        login.setEmail("peter@klaven");
    }
    @Test
    public void LoginUnsuccessFull (){
        SoftAssert softAssert = new SoftAssert();

        Response response = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(login)
                .when().post(baseUrl + LoginEndPoint)
                .then().statusCode(400).extract().response();

        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        Assert.assertEquals(jsonPath.get("error") , "Missing password");
        softAssert.assertEquals(response.getStatusCode() , 400);
        softAssert.assertAll();

        System.out.println(response);
    }
}
