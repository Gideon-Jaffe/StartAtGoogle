package Week3.ApacheHttpFacade;

public class MyResponse {
    private final int code;
    private final String body;

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public MyResponse(int code, String body) {
        this.code = code;
        this.body = body;
    }

    @Override
    public String toString() {
        return "MyResponse - status code = " + code + ", body= '" + body + '\'';
    }
}
