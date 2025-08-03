package Users;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Update_User extends BaseTest {
    int SingleUser =2;
    @Test
    public void Update_User(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res = RestAssured.given().headers(headers).
                body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}").when().put(baseUrl + "api/users/"+ SingleUser).then().extract().response();
        Assert.assertEquals(res.statusCode(),200);
        String body = res.getBody().asPrettyString();
        System.out.println(body);
    }

    @Test
    public void getSingleUser_body() {
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response res =  RestAssured.given().headers(headers)
                .when().put(baseUrl + "api/users/"+ SingleUser).
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
