package login;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostLoginSuccessFull extends BaseTest {

    @Test
    public void LoginSuccessFull(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);
        SoftAssert softAssert = new SoftAssert();

        Response response = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "\"email\": \"eve.holt@reqres.in\",\n" +
                        "\"password\": \"cityslicka\"\n" + "}"
                )
                .when().post(baseUrl + "api/login")
                .then().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        Assert.assertEquals(jsonPath.get("token") , "QpwL5tke4Pnpja7X4");
        softAssert.assertEquals(response.getStatusCode() , 200);
        softAssert.assertAll();

        System.out.println(response);
    }
}
