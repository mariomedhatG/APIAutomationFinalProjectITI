package Register;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Invalid_Register extends BaseTest {
    @Test
    public void INValid_Register_PrintErrorMessage() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .when().post(baseUrl + "api/register").then().extract().response();
        Assert.assertTrue(res.statusCode() == 400);
        String error = res.jsonPath().getString("error");
        Assert.assertNotNull(error,"Error is not null");
        System.out.println("Error : " + error);
    }

    @Test
    public void INValid_Register_Status() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}").when().post(baseUrl + "api/register").then().extract().response();
        boolean status = res.statusCode() == 200;
      Assert.assertTrue(status);
    }
}
