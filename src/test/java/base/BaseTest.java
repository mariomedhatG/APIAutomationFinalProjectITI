package base;


import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import static utility.Constant.*;
public class BaseTest {

    protected RequestSpecification getRequestSpecWithHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        return RequestSpecBuilderUtil.getRequestSpec(headers);
    }
    protected RequestSpecification getRequestSpecWithHeadersquery() {
        Map<String, String> headers = new HashMap<>();
        headers.put(headerKey, headerValue);
        headers.put(contentTypeKey, contentTypeValue);
        headers.put(queryKey, queryValue);
        return RequestSpecBuilderUtil.getRequestSpec(headers);
    }
}
