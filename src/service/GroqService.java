package service;

import model.ChatCategory;
import model.ChatModel;
import util.TextUtil;

/*
curl -X POST "https://api.groq.com/openai/v1/chat/completions" \
     -H "Authorization: Bearer $GROQ_API_KEY" \
     -H "Content-Type: application/json" \
     -d '{"messages": [{"role": "user", "content": "Explain the importance of fast language models"}], "model": "llama-3.3-70b-versatile"}'
 */
// https://console.groq.com/docs/quickstart
public class GroqService extends AbstractChatService {
    public GroqService() {
        super(System.getenv("GROQ_API_KEY"), "https://api.groq.com/openai/v1/chat/completions");
    }

    @Override
    public String chat(String input, ChatModel model, String instruction) {
        if (model.category != ChatCategory.GROQ) return "Groq 모델이 아닙니다!";
        String payload = """
                    {
                    "messages": [
                        {"role": "system", "content": "%s"},
                        {"role": "user", "content": "%s"}
                    ],
                    "model": "%s"
                    }
                    """.formatted(instruction, input, model.modelCode);
        String[] headers = {
                "Authorization", "Bearer %s".formatted(API_KEY),
                "Content-Type", "application/json"
        };
        String output = sendRequest(API_URL.formatted(model.modelCode), payload, headers);
        return TextUtil.splitJson(output, "\"content\":\"", "\",\"");
//        return output.split("\"text\": \"")[1]
//                .split("\"\\s*}")[0];
    }
}
