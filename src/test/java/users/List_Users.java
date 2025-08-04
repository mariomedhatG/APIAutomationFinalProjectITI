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

public class List_Users extends BaseTest  {
    private Map<String, String> headers;
    @BeforeMethod
    public void setup() {
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        headers.put(queryKey, queryValue);
    }
    @Test
    public void Get_List_Users() {
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeadersquery())
                .when().get(baseUrl + ListUserEndPoint)
                .then().extract().response();
        boolean status = res.statusCode() == 200;
        Assert.assertTrue(status, "Status code isn't 200");
    }

    @Test
    public void getSingleUser_body() {
        Response res = RestAssured
                .given().spec(getRequestSpecWithHeadersquery())
                .when().get(baseUrl + ListUserEndPoint)
                .then().extract().response();

        String body = res.getBody().asPrettyString();
        System.out.println(body);

        int id = res.jsonPath().getInt("data[0].id");
        String firstName = res.jsonPath().getString("data[0].first_name");
        String lastName = res.jsonPath().getString("data[0].last_name");
        String email = res.jsonPath().getString("data[0].email");

        Assert.assertTrue(id > 0, "User ID should be greater than 0");
        Assert.assertNotNull(firstName, "First name is null");
        Assert.assertNotNull(lastName, "Last name is null");
        Assert.assertNotNull(email, "Email is null");
    }
}
