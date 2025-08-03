package Register;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Valid_Register {
    @Test
    public void Valid_Register_Body() {
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").when().post("https://reqres.in/api/register").then().extract().response();
        boolean status = res.statusCode() == 200;
        System.out.println(status);
        res.body().prettyPrint();
    }
    @Test
    public void Valid_Register_PrintID() {
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").when().post("https://reqres.in/api/register").then().extract().response();
        Assert.assertTrue(res.statusCode() == 200);
        int id = res.jsonPath().getInt("id");
        Assert.assertNotNull(id,"Token is null");
        System.out.println("ID : " + id);
    }
    @Test
    public void Valid_Register_PrintToken() {
        Response res = RestAssured.given().headers("x-api-key","reqres-free-v1").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}").when().post("https://reqres.in/api/register").then().extract().response();
      Assert.assertTrue(res.statusCode() == 200);
        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token,"Token is null");
        System.out.println("Token : " + token);
    }
}
