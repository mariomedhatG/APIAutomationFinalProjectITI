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

public class Not_Found_User extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void Get_NotFound_USer(){

        Response res =  RestAssured.
                given().spec(getRequestSpecWithHeaders())
                .when().get(baseUrl + UserEndPoint+ NotFoundUser).
                then().extract().response();
        boolean status = res.statusCode() == 404;
        Assert.assertTrue(status,"User Is Found");
    }
}
