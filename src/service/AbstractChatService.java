package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractChatService implements ChatService {
    // 상속 받아서 실제로 구현할 기능들이 사용
    protected final HttpClient client = HttpClient.newHttpClient();
    protected final String API_KEY; // 환경변수의 이름이 제각각
    protected final String API_URL;
    AbstractChatService(String apiKey, String apiURL) { // 주입
        API_KEY = apiKey;
        API_URL = apiURL;
    }

    protected String sendRequest(String payload, String[] headers) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .headers(headers) // header name, value, name, value ...
                .build();
        String output = "";
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            output = response.body(); // split? <- 플랫폼마다 구조가 다름
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return output;
    }
}
