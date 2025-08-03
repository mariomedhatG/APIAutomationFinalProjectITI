package Users;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Create_User extends BaseTest {
    @Test
    public void Update_User(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post(baseUrl + "api/users/")
                .then().extract().response();
        boolean status = res.statusCode() == 201;
        System.out.println(status);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }
    @Test
    public void Print_Name(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured
                .given().headers(headers)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post(baseUrl +"api/users/")
                .then().extract().response();
        String name = res.jsonPath().get("name");
        Assert.assertEquals(name, "morpheus");
        System.out.println(name);
    }
    @Test
    public void InValid_Print_Id(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured.given().headers(headers)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post(baseUrl + "api/users/")
                .then().extract().response();
        String ID = res.jsonPath().get("id");
        Assert.assertEquals(ID, "leader");
    }
}
