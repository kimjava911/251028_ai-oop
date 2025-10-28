package service;

import model.ChatCategory;
import model.ChatModel;
import util.TextUtil;

/*
curl https://openrouter.ai/api/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENROUTER_API_KEY" \
  -d '{
  "model": "openai/gpt-4o",
  "messages": [
    {
      "role": "user",
      "content": "What is the meaning of life?"
    }
  ]
}'
*/
// https://openrouter.ai/docs/quickstart
// https://openrouter.ai/models?fmt=cards&max_price=0&order=top-weekly
public class OpenRouterService extends AbstractChatService {
    public OpenRouterService() {
        super(System.getenv("OPENROUTER_API_KEY"), "https://openrouter.ai/api/v1/chat/completions");
    }

    @Override
    public String chat(String input, ChatModel model, String instruction) {
        if (model.category != ChatCategory.OPEN_ROUTER) return "OpenRouter 모델이 아닙니다!";
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
