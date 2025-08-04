package users;

import base.RequestSpecBuilderUtil;

import utility.Payload;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static utility.Constant.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserPatch extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void PatchUser(){
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders()).
                body(Payload.UpdateBody())
                .when().patch(baseUrl + UpdateUsersEndPoint)
                .then().extract().response();

        Assert.assertEquals(res.statusCode(),200);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }
}
