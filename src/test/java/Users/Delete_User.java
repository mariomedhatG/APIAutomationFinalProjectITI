package Users;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Delete_User extends BaseTest{
    int SingleUser=2;
    @Test
    public void Delete_USer() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res =  RestAssured
                .given().headers(headers)
                .when().delete(baseUrl + "api/users/" +SingleUser )
                .then().extract().response();
        Assert.assertEquals(res.statusCode(),204);
    }
}
