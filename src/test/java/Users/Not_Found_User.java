package Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Not_Found_User {
    int NotFoundUser=23;
    @Test
    public void Get_NotFound_USer(){
        Response res =  RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .when().get("https://reqres.in/api/users/"+ NotFoundUser).
                then().extract().response();
        boolean status = res.statusCode() == 404;
        Assert.assertTrue(status,"User Is Found");
    }
}
