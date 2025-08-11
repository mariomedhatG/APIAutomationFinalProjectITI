package register;
import models.RegisterPayLoad;
import org.testng.annotations.BeforeMethod;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static utility.Constant.*;
import java.util.HashMap;
import java.util.Map;

public class Valid_Register extends BaseTest {
    RegisterPayLoad register;
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        register = new RegisterPayLoad();
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        register.setEmail("eve.holt@reqres.in");
        register.setPassword("pistol");
    }
    @Test
    public void Valid_Register_PrintID() {

        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(register)
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
                .body(register)
                .when().post(baseUrl + RegisterUserEndPoint)
                .then().extract().response();
      Assert.assertTrue(res.statusCode() == 200);
        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token,"Token is null");
        System.out.println("Token : " + token);
    }
}
