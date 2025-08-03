package Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Delete_User {
    int SingleUser=2;
    @Test
    public void Delete_USer() {
        Response res =  RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .when().delete("https://reqres.in/api/users/"+SingleUser ).
                then().extract().response();
        Assert.assertEquals(res.statusCode(),204);
    }
}
