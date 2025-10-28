package service;

import model.GeminiModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String API_KEY = System.getenv("GEMINI_API_KEY");
    private final String url = "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent"; // 포맷팅으로 변경시켜줄 것

    private String chatUseModel(String input, String model, String instruction) {
        // 1. request
//        String payload = """
//                    {"contents": [{"parts": [{"text": "%s"}]}]}
//                    """.formatted(input); // JSON 포맷
        String payload = """
                    {"contents": [{"parts": [{"text": "%s %s"}]}]}
                    """.formatted(input, instruction); // JSON 포맷
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
        return chatUseModel(input, "gemini-2.5-flash", "");
    }

    // 오버로딩
    public String chat(String input, String model) {
        return chatUseModel(input, model, "");
    }

    public String chat(String input, GeminiModel model) {
        return chatUseModel(input, model.modelCode, "");
    }

    public String chat(String input, GeminiModel model, String instruction) {
        return chatUseModel(input, model.modelCode, instruction);
    }
}
