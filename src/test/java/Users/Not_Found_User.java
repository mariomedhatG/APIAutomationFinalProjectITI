package Users;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Not_Found_User extends BaseTest {
    int NotFoundUser=23;
    @Test
    public void Get_NotFound_USer(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res =  RestAssured.given().headers(headers)
                .when().get(baseUrl + "api/users/"+ NotFoundUser).
                then().extract().response();
        boolean status = res.statusCode() == 404;
        Assert.assertTrue(status,"User Is Found");
    }
}
