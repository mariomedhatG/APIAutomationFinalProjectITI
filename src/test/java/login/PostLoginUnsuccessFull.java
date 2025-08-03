package login;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostLoginUnsuccessFull extends BaseTest {

    @Test
    public void LoginUnsuccessFull (){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);
        SoftAssert softAssert = new SoftAssert();

        Response response = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "\"email\": \"peter@klaven\",\n" +
                        "}"
                )
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
