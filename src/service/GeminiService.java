package service;

import model.ChatModel;

public class GeminiService extends AbstractChatService {
    public GeminiService() {
        super(System.getenv("GEMINI_API_KEY"), "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent");
    }

    @Override
    public String chat(String input, ChatModel model, String instruction) {
        String payload = """
                    {
                    "contents": [{"parts": [{"text": "%s"}]}],
                    "system_instruction": {"parts": [{"text": "%s"}]}
                    }
                    """.formatted(input, instruction);
        String[] headers = {
                "x-goog-api-key", API_KEY,
                "Content-Type", "application/json"
        };
        String output = sendRequest(API_URL.formatted(model.modelCode), payload, headers);
        return output.split("\"text\": \"")[1]
                .split("\"\\s*}")[0];
    }
}
