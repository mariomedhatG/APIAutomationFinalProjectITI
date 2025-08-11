package e2e_scenario;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.CreateUserPayload;
import models.LoginPayLoad;
import models.RegisterPayLoad;
import models.UpdateUserPayLoad;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.LogsUtility;
import utility.Payload;
import java.util.HashMap;
import java.util.Map;
import static utility.Constant.*;

public class E2E_Scenario extends BaseTest {
    UpdateUserPayLoad user;
    RegisterPayLoad register;
    CreateUserPayload createUser;
    LoginPayLoad login;
    private Map<String, String> headers;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        login = new LoginPayLoad();
        register = new RegisterPayLoad();
        user = new UpdateUserPayLoad();
        createUser = new CreateUserPayload();
        headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        user.setName("morpheus");
        user.setJob("zion resident");
        register.setEmail("eve.holt@reqres.in");
        register.setPassword("pistol");
        user.setName("morpheus");
        user.setJob("leader");
        login.setEmail("eve.holt@reqres.in");
        login.setPassword("cityslicka");
    }
    @Test(groups = {"E2E_Test"})
    public void Valid_Register()
    {
        Response RegisterResponse = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(register)
                .when().post(baseUrl + RegisterUserEndPoint)
                .then().extract().response();
        Assert.assertTrue(RegisterResponse.statusCode() == 200);
        LogsUtility.info("status code for Register is 200");
        String token = RegisterResponse.jsonPath().getString("token");
        Assert.assertNotNull(token,"Token is null");
        LogsUtility.info("Token is generated successfully after Register");
        System.out.println("Token : " + token);
    }
    @Test(groups = {"E2E_Test"})
    public void RegisterToLogin()
    {
        softAssert = new SoftAssert();
       Response RegisterResponse = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(register)
                .when().post(baseUrl + RegisterUserEndPoint)
                .then().extract().response();
        softAssert.assertTrue(RegisterResponse.statusCode() == 200);
        LogsUtility.info("status code for Register is 200");
        String token = RegisterResponse.jsonPath().getString("token");
        softAssert.assertNotNull(token,"Token is null");
        LogsUtility.info("Token is generated successfully after Register");
        System.out.println("Token : " + token);

        Response LoginResponse = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(login)
                .when().post(baseUrl + LoginEndPoint)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath = LoginResponse.jsonPath();
        jsonPath.prettyPrint();
        softAssert.assertNotNull(jsonPath.get("token"), "Login token is null");
        LogsUtility.info("Login token received successfully");
        softAssert.assertEquals(LoginResponse.getStatusCode() , 200);
        LogsUtility.info("Login response status code is 200");
        System.out.println(LoginResponse.jsonPath().getString("token"));
        softAssert.assertAll();
    }
    @Test(groups = {"E2E_Test"})
    public void RegisterToListUser(){
        softAssert = new SoftAssert();
        Response  RegisterResponse = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(register)
                .when().post(baseUrl + RegisterUserEndPoint)
                .then().extract().response();
        softAssert.assertTrue(RegisterResponse.statusCode() == 200);
        LogsUtility.info("status code for Register is 200");
        String token = RegisterResponse.jsonPath().getString("token");
        softAssert.assertNotNull(token,"Token is null");
        LogsUtility.info("Token is generated successfully after Register");
        System.out.println("Token : " + token);

        Response  LoginResponse  = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(Payload.LoginBody())
                .when().post(baseUrl + LoginEndPoint)
                .then().statusCode(200).extract().response();
        JsonPath jsonPath = LoginResponse.jsonPath();
        jsonPath.prettyPrint();
        String loginToken = jsonPath.getString("token");
        softAssert.assertNotNull(loginToken, "Login Token is null");
        LogsUtility.info("Login token received successfully");
        softAssert.assertEquals(LoginResponse.getStatusCode() , 200);
        LogsUtility.info("Login response status code is 200");
        System.out.println(LoginResponse);

        Response ListuserResponse = RestAssured
                .given().spec(getRequestSpecWithHeadersquery())
                .when().get(baseUrl + ListUserEndPoint)
                .then().extract().response();
        boolean status = ListuserResponse.statusCode() == 200;
        softAssert.assertTrue(status, "Status code isn't 200");
        LogsUtility.info("Status code for List Users is 200");
        softAssert.assertAll();
    }
    @Test(groups = {"E2E_Test"})
    public void CreateToDeleteUser(){
        softAssert = new SoftAssert();
       Response CreateResponse = RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .body(createUser)
                .when().post(baseUrl + UserEndPoint)
                .then().extract().response();
       softAssert.assertEquals(CreateResponse.statusCode(), 201, "Status code should be 201 Created");
       LogsUtility.info("Status code is 201 Created");

        Response ListuserResponse = RestAssured
                .given().spec(getRequestSpecWithHeadersquery())
                .when().get(baseUrl + ListUserEndPoint)
                .then().extract().response();
         boolean statu = ListuserResponse.statusCode() == 200;
        softAssert.assertTrue(statu, "Status code isn't 200");
        LogsUtility.info("Status code for List Users is 200");

       Response GetSingleuserResponse =  RestAssured.given().spec(getRequestSpecWithHeaders())
                .when().get(baseUrl + UserEndPoint + SingleUser).
                then().extract().response();
        softAssert.assertEquals(GetSingleuserResponse.statusCode(),200);
        LogsUtility.info("Status code for Single User is 200");

        Response UpdateSindleuserResponse = RestAssured.
                given().spec(getRequestSpecWithHeaders()).
                body(user)
                .when().put(baseUrl + UserEndPoint+ SingleUser)
                .then().extract().response();
        softAssert.assertEquals(UpdateSindleuserResponse.statusCode(),200);
        LogsUtility.info("User updated successfully");
        String body = UpdateSindleuserResponse.getBody().asPrettyString();
        System.out.println(body);

        Response DeleteResponse =  RestAssured
                .given().spec(getRequestSpecWithHeaders())
                .when().delete(baseUrl + UserEndPoint +SingleUser )
                .then().extract().response();
        softAssert.assertEquals(DeleteResponse.statusCode(),204);
        LogsUtility.info("User deleted successfully");

        Response NotfoundResponse  =  RestAssured.
                given().spec(getRequestSpecWithHeaders())
                .when().get(baseUrl + UserEndPoint+ NotFoundUser).
                then().extract().response();
        boolean stat = NotfoundResponse.statusCode() == 404;
        softAssert.assertTrue(stat,"User Is Found");
        LogsUtility.info("User Is Found");
        softAssert.assertAll();
    }
}
