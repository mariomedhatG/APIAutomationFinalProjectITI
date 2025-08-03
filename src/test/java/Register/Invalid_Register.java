package Register;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Invalid_Register {
    @Test
    public void INValid_Register_PrintErrorMessage() {
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}").when().post("https://reqres.in/api/register").then().extract().response();
        Assert.assertTrue(res.statusCode() == 400);
        String error = res.jsonPath().getString("error");
        Assert.assertNotNull(error,"Error is not null");
        System.out.println("Error : " + error);
    }
    @Test
    public void INValid_Register_Status() {
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body("{{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}").when().post("https://reqres.in/api/register").then().extract().response();
        boolean status = res.statusCode() == 200;
      Assert.assertTrue(status);
    }
}
