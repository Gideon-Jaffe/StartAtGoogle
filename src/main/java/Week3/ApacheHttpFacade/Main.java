package Week3.ApacheHttpFacade;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApacheFacade httpCalls = new ApacheFacade();

        testGet(httpCalls);

        testPut(httpCalls);

        testPost(httpCalls);

        testPatch(httpCalls);

        testDelete(httpCalls);
    }

    private static void testGet(ApacheFacade httpCalls) {
        System.out.println("---------- Checking get code");
        MyResponse myResponse = httpCalls.httpGetRequest("https://reqres.in/api/users?page2");
        System.out.println(myResponse);
    }

    private static void testPut(ApacheFacade httpCalls) {
        System.out.println("---------- Checking put code");
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", "Gideon");
        userDetails.put("job", "Tester");
        MyResponse myResponse = httpCalls.httpPutRequest("https://reqres.in/api/users/2", userDetails);
        System.out.println(myResponse);
    }

    private static void testPost(ApacheFacade httpCalls) {
        System.out.println("---------- Checking post code");
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", "Gideon");
        userDetails.put("job", "Tester");
        MyResponse myResponse = httpCalls.httpPostRequest("https://reqres.in/api/users/2", userDetails);
        System.out.println(myResponse);
    }

    private static void testPatch(ApacheFacade httpCalls) {
        System.out.println("---------- Checking patch code");
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("name", "Gideon");
        userDetails.put("job", "Tester");
        MyResponse myResponse = httpCalls.httpPatchRequest("https://reqres.in/api/users/2", userDetails);
        System.out.println(myResponse);
    }

    private static void testDelete(ApacheFacade httpCalls) {
        System.out.println("---------- Checking delete code");
        MyResponse myResponse = httpCalls.httpDeleteRequest("https://reqres.in/api/users/2");
        System.out.println(myResponse);
    }
}

