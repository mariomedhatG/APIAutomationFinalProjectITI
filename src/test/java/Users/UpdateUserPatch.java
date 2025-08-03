package Users;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserPatch extends BaseTest {
    @Test
    public void PatchUser(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured.given().headers(headers).
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when().put(baseUrl + "api/users/2")
                .then().extract().response();
        Assert.assertEquals(res.statusCode(),200);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }
}
