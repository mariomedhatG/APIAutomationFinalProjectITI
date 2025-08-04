package users;

import base.BaseTest;
import base.RequestSpecBuilderUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static utility.Constant.*;
public class Delete_User extends BaseTest{
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void Delete_USer() {
        Response res =  RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .when().delete(baseUrl + UserEndPoint +SingleUser )
                .then().extract().response();
        Assert.assertEquals(res.statusCode(),204);
    }
}
