package Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Update_User {
    int SingleUser =2;
    @Test
    public void Update_User(){
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").when().put("https://reqres.in/api/users/"+ SingleUser).then().extract().response();
        Assert.assertEquals(res.statusCode(),200);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }
    @Test
    public void getSingleUser_body() {
        Response res =  RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .when().put("https://reqres.in/api/users/"+ SingleUser).
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
