package users;

import base.BaseTest;
import base.RequestSpecBuilderUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static utility.Constant.*;
import java.util.HashMap;
import java.util.Map;

public class Get_Single_User extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void getSingleUser_body() {
        Response res =  RestAssured.given().spec(getRequestSpecWithHeaders())
                .when().get(baseUrl + UserEndPoint+ SingleUser).
                then().extract().response();

        String body = res.getBody().asPrettyString();
        System.out.println(body);
        int id = res.jsonPath().getInt("data.id");
        String firstName = res.jsonPath().getString("data.first_name");
        String lastName = res.jsonPath().getString("data.last_name");
        String email = res.jsonPath().getString("data.email");

        Assert.assertEquals(id, SingleUser, "User ID mismatch");
        Assert.assertNotNull(firstName, "First name is null");
        Assert.assertNotNull(lastName, "Last name is null");
        Assert.assertNotNull(email, "Email is null");
    }
    @Test
    public void Get_Single_User() {
        Response res =  RestAssured.given().spec(getRequestSpecWithHeaders())
                .when().get(baseUrl + UserEndPoint + SingleUser).
                then().extract().response();
        Assert.assertEquals(res.statusCode(),200);
    }
}
