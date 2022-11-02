package Week3.ApacheHttpFacade;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class ApacheFacade {
    private final HttpClient client;

    public ApacheFacade() {
        client = HttpClientBuilder.create().build();
    }

    public MyResponse httpGetRequest(String path){
        ClassicHttpRequest getUrl = new HttpGet(path);
        return execute(getUrl);
    }

    public MyResponse httpPostRequest(String path, Object o){
        ClassicHttpRequest postUrl = new HttpPost(path);
        postUrl.setEntity(new StringEntity(new Gson().toJson(o)));
        return execute(postUrl);
    }

    public MyResponse httpPutRequest(String path, Object o) {
        ClassicHttpRequest putUrl = new HttpPut(path);
        putUrl.setEntity(new StringEntity(new Gson().toJson(o)));
        return execute(putUrl);
    }

    public MyResponse httpPatchRequest(String path, Object o) {
        ClassicHttpRequest patchUrl = new HttpPatch(path);
        patchUrl.setEntity(new StringEntity(new Gson().toJson(o)));
        return execute(patchUrl);
    }

    public MyResponse httpDeleteRequest(String path) {
        ClassicHttpRequest deleteUrl = new HttpDelete(path);
        return execute(deleteUrl);
    }

    private MyResponse execute(ClassicHttpRequest postUrl) {
        HttpClientResponseHandler<MyResponse> myHandler = new MyHandler();
        MyResponse response;
        try {
            response = client.execute(postUrl, myHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}

