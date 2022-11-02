package Week3.ApacheHttpFacade;

import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class MyHandler implements HttpClientResponseHandler<MyResponse> {

    @Override
    public MyResponse handleResponse(ClassicHttpResponse classicHttpResponse) throws HttpException, IOException {
        int code = classicHttpResponse.getCode();
        String body;
        if (classicHttpResponse.getEntity() == null) {
            body = "";
        } else {
            body = EntityUtils.toString(classicHttpResponse.getEntity(), classicHttpResponse.getEntity().getContentEncoding());
        }
        return new MyResponse(code, body);
    }
}
