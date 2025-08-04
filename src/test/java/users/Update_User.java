package users;

import base.RequestSpecBuilderUtil;
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

public class Update_User extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void Update_User(){

        Response res = RestAssured.
                given().spec(getRequestSpecWithHeaders()).
                body(Payload.UpdateBody())
                .when().put(baseUrl + UserEndPoint+ SingleUser)
                .then().extract().response();
        Assert.assertEquals(res.statusCode(),200);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }

    @Test
    public void getSingleUser_body() {

        Response res =  RestAssured.given()
                .spec(getRequestSpecWithHeaders())
                .when().put(baseUrl + UserEndPoint+ SingleUser).
                then().extract().response();
        String body = res.getBody().asPrettyString();
        System.out.println(body);
        String name = res.jsonPath().getString("data.first_name");
        String job = res.jsonPath().getString("data.last_name");
        String updatedAt = res.jsonPath().getString("data.email");

        Assert.assertNotNull(name, "name is null");
        Assert.assertNotNull(job, "Job is null");
        Assert.assertNotNull(updatedAt, "Date is null");
    }
}
