package Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Get_Single_User {
    int singleUserId= 2;
    @Test
    public void getSingleUser_body() {
        Response res =  RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .when().get("https://reqres.in/api/users/"+ singleUserId).
                then().extract().response();
        String body = res.getBody().asPrettyString();
        System.out.println(body);
        int id = res.jsonPath().getInt("data.id");
        String firstName = res.jsonPath().getString("data.first_name");
        String lastName = res.jsonPath().getString("data.last_name");
        String email = res.jsonPath().getString("data.email");

        Assert.assertEquals(id, singleUserId, "User ID mismatch");
        Assert.assertNotNull(firstName, "First name is null");
        Assert.assertNotNull(lastName, "Last name is null");
        Assert.assertNotNull(email, "Email is null");
    }
    @Test
    public void Get_Single_User() {
        Response res =  RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .when().get("https://reqres.in/api/users/"+ singleUserId).
                then().extract().response();
        Assert.assertEquals(res.statusCode(),200);
    }
}
