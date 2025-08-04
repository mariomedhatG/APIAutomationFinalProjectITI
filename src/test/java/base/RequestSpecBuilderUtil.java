package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import java.util.Map;

public class RequestSpecBuilderUtil {

    public static RequestSpecification getRequestSpec(Map<String, String> headers) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/");
        requestSpecBuilder.setContentType(ContentType.JSON);
        if (headers != null && !headers.isEmpty()) {
            requestSpecBuilder.addHeaders(headers);
        }
        return requestSpecBuilder.build();
    }
}
