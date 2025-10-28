import model.ChatModel;
import service.ChatService;
import service.GeminiService;
import service.GroqService;

import java.util.Scanner;

public class Application {
    // 진입점
    public static void main(String[] args) {
        System.out.println("🤖 챗봇 구동 시작");
        Scanner sc = new Scanner(System.in);
//        ChatService chatService = new GeminiService(); // Groq, OpenRouter ?
        ChatService chatService = new GroqService();
        String instruction = "50자 이내, 마크다운 없이, 영어로 결과만 작성.";
        while (true) {
            System.out.print("🤖 질문을 입력해주세요 : ");
            String input = sc.nextLine();
            if (input.equals("종료")) {
                System.out.println("🤖 챗봇 구동 종료");
                break;
            }
//            String output = chatService.chat(input, ChatModel.GEMINI_2_5_FLASH_LITE, instruction);
            String output = chatService.chat(input, ChatModel.GPT_OSS_120B, instruction);
            System.out.println("\uD83D\uDCAC AI : %s".formatted(output));
        }
        sc.close();
    }
}
