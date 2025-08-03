package Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_User {
    @Test
    public void Update_User(){
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").when().post("https://reqres.in/api/users/").then().extract().response();
        boolean status = res.statusCode() == 201;
        System.out.println(status);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }
    @Test
    public void Print_Name(){
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").when().post("https://reqres.in/api/users/").then().extract().response();
        String name = res.jsonPath().get("name");
        Assert.assertEquals(name, "morpheus");
        System.out.println(name);
    }
    @Test
    public void InValid_Print_Id(){
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json").
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}").when().post("https://reqres.in/api/users/").then().extract().response();
        String ID = res.jsonPath().get("id");
        Assert.assertEquals(ID, "leader");
    }
}
