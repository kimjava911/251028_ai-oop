package service;

import model.GeminiModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiService {
    // 인스턴스변수(멤버변수)
    // 캡슐화 -> GeminiService / Application
    private final HttpClient client = HttpClient.newHttpClient();
    private final String API_KEY = System.getenv("GEMINI_API_KEY");
//    private final String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";
    private final String url = "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent"; // 포맷팅으로 변경시켜줄 것

    private String chatUseModel(String input, String model) {
        // 1. request
        String payload = """
                    {"contents": [{"parts": [{"text": "%s"}]}]}
                    """.formatted(input); // JSON 포맷
        // String model = "gemini-2.5-flash";
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url.formatted(model)))
                .header("x-goog-api-key", API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        // 2. response
        String output = "";
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            output = body.split("\"text\": \"")[1]
                    .split("\"\\s*}")[0];
            // 정규표현식 패턴
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        // output
        return output;
    }

    public String chat(String input) {
        return chatUseModel(input, "gemini-2.5-flash");
    }

    // 오버로딩
    public String chat(String input, String model) {
        return chatUseModel(input, model);
    }

    public String chat(String input, GeminiModel model) {
//        String modelCode = switch (model) {
//            case GEMINI_2_5_FLASH -> "gemini-2.5-flash";
//            case GEMINI_2_5_PRO -> "gemini-2.5-pro";
//            case GEMINI_2_5_FLASH_LITE -> "gemini-2.5-flash-lite";
//        };
//        return chatUseModel(input, modelCode);
        return chatUseModel(input, model.modelCode);
    }
}
