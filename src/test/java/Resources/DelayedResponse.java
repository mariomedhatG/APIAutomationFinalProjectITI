package Resources;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DelayedResponse extends BaseTest {

    @Test
    public void DelayResponse(){
        headers.put(headerKey , headerValue);
        headers.put(contentTypeKey , contentTypeValue);

        Response response = RestAssured.
                given().headers(headers).
                when().get(baseUrl + "api/users?delay=3").
                then().extract().response();

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode() , 200);
    }
}
