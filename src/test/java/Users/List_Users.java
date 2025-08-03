package Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class List_Users {
    @Test
    public void Get_List_Users() {
        Response res = RestAssured.given().headers("x-api-key", "reqres-free-v1").queryParam("page", "2")
                .when().get("https://reqres.in/api/users?page=2").
                then().extract().response();
        boolean status = res.statusCode() == 200;
        Assert.assertTrue(status, "Status code isn't 200");
    }

    @Test
    public void getSingleUser_body() {
        Response res = RestAssured.given()
                .headers("x-api-key", "reqres-free-v1").queryParam("page", "2")
                .when().get("https://reqres.in/api/users?page=2")
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
