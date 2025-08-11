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

public class PostLoginSuccessFull extends BaseTest {
    LoginPayLoad login;
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        login = new LoginPayLoad();
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        login.setEmail("eve.holt@reqres.in");
        login.setPassword("cityslicka");
    }
    @Test
    public void LoginSuccessFull(){
        SoftAssert softAssert = new SoftAssert();
        Response response = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(login)
                .when().post(baseUrl + LoginEndPoint)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        String loginToken = jsonPath.getString("token");
        Assert.assertNotNull(loginToken, "Login Token is null");
        softAssert.assertEquals(response.getStatusCode() , 200);
        softAssert.assertAll();
        System.out.println(response);
    }
}
