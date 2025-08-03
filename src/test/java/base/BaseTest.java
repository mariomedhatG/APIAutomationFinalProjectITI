package base;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected String baseUrl = "https://reqres.in/";
    protected Map<String , String> headers = new HashMap<>();
    protected String headerKey = "x-api-key";
    protected String headerValue = "reqres-free-v1";
    protected String contentTypeKey = "Content-Type";
    protected String contentTypeValue = "application/json";

}
