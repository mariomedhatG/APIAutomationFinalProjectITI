package register;
import org.testng.annotations.BeforeMethod;
import utility.Payload;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static utility.Constant.*;
import java.util.HashMap;
import java.util.Map;

public class Valid_Register extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void Valid_Register_Body() {

        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.ValidRegisterBody())
                .when().post(baseUrl + "api/register")
                .then().extract().response();
        boolean status = res.statusCode() == 200;
        System.out.println(status);
        res.body().prettyPrint();
    }
    @Test
    public void Valid_Register_PrintID() {

        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.ValidRegisterBody())
                .when().post(baseUrl + RegisterUserEndPoint).then().extract().response();
        Assert.assertTrue(res.statusCode() == 200);
        int id = res.jsonPath().getInt("id");
        Assert.assertNotNull(id,"ID is null");
        System.out.println("ID : " + id);
    }
    @Test
    public void Valid_Register_PrintToken() {

        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.ValidRegisterBody())
                .when().post(baseUrl + RegisterUserEndPoint)
                .then().extract().response();
      Assert.assertTrue(res.statusCode() == 200);
        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token,"Token is null");
        System.out.println("Token : " + token);
    }
}
