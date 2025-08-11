package utility;

public class Constant {
    public static final String baseUrl = "https://reqres.in/";
    public static final String headerKey = "x-api-key";
    public static final String headerValue = "reqres-free-v1";
    public static final String contentTypeKey = "Content-Type";
    public static final String contentTypeValue = "application/json";
    public static final String queryKey = "page";
    public static final String queryValue = "2";
    public static final String UserEndPoint = "api/users/";
    public static final String ListUserEndPoint = "api/users?page=2";
    public static final String UpdateUsersEndPoint = "api/users/2";
    public static final String RegisterUserEndPoint = "api/register";
    public static final String LoginEndPoint = "api/login";
    public static final String DelayedEndPoint = "api/users?delay=3";
    public static final String ListEndPoint = "api/unknown";
    public static final String SingleListEndPoint = "api/unknown/2";
    public static final String SingleListNotFoundEndPoint = "api/unknown/23";
    public static final int SingleUser = 2;
    public static final int SingleResource = 2;
    public static final int NotFoundUser = 23;
}
