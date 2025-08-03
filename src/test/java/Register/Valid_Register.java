package Register;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Valid_Register extends BaseTest {
    @Test
    public void Valid_Register_Body() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .when().post(baseUrl + "api/register")
                .then().extract().response();
        boolean status = res.statusCode() == 200;
        System.out.println(status);
        res.body().prettyPrint();
    }
    @Test
    public void Valid_Register_PrintID() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").when().post(baseUrl + "api/register").then().extract().response();
        Assert.assertTrue(res.statusCode() == 200);
        int id = res.jsonPath().getInt("id");
        Assert.assertNotNull(id,"Token is null");
        System.out.println("ID : " + id);
    }
    @Test
    public void Valid_Register_PrintToken() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").when().post(baseUrl + "api/register")
                .then().extract().response();
      Assert.assertTrue(res.statusCode() == 200);
        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token,"Token is null");
        System.out.println("Token : " + token);
    }
}
