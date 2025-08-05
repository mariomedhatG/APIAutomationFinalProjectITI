package register;

import models.RegisterPayLoad;
import org.testng.annotations.BeforeMethod;
import utility.Payload;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static utility.Constant.*;

public class Invalid_Register extends BaseTest {
    RegisterPayLoad register ;
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
       register=  new RegisterPayLoad();
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        register.setEmail("eve.holt@reqres.in");
    }
    @Test
    public void InValid_Register_PrintErrorMessage() {

        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(register)
                .when().post(baseUrl + RegisterUserEndPoint).then().extract().response();

        Assert.assertTrue(res.statusCode() == 400);
        String error = res.jsonPath().getString("error");
        Assert.assertNotNull(error,"Error is not null");
        System.out.println("Error : " + error);
    }

    @Test
    public void INValid_Register_Status() {
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(register)
                .when().post(baseUrl + RegisterUserEndPoint)
                .then().extract().response();
        boolean status = res.statusCode() == 200;
        Assert.assertTrue(status);
    }
}
