package users;

import base.RequestSpecBuilderUtil;
import org.testng.annotations.BeforeMethod;
import utility.Payload;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static utility.Constant.*;
public class Create_User extends BaseTest {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
    }
    @Test
    public void Create_User(){
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.CreateBody())
                .when().post(baseUrl + UserEndPoint)
                .then().extract().response();
        Assert.assertEquals(res.statusCode(), 201, "Status code should be 201 Created");
        Assert.assertEquals(res.jsonPath().getString("name"), "morpheus", "Name should be morpheus");
    }
    @Test
    public void Print_Name(){
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.CreateBody())
                .when().post(baseUrl + UserEndPoint)
                .then().extract().response();
        String name = res.jsonPath().get("name");
        Assert.assertEquals(name, "morpheus");
        System.out.println(name);
    }
    @Test
    public void InValid_Print_Id(){
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.CreateBody())
                .when().post(baseUrl + UserEndPoint)
                .then().extract().response();
        String ID = res.jsonPath().get("id");
        Assert.assertEquals(ID, "leader");
    }
}
