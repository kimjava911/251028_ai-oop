import model.GeminiModel;
import service.GeminiService;

import java.util.Scanner;

public class Application {
    // 진입점
    public static void main(String[] args) {
        System.out.println("🤖 챗봇 구동 시작");
        Scanner sc = new Scanner(System.in);
        GeminiService gemini = new GeminiService();
        while (true) {
            System.out.print("🤖 질문을 입력해주세요 : ");
            String input = sc.nextLine();
            if (input.equals("종료")) {
                System.out.println("🤖 챗봇 구동 종료");
                break;
            }
            // Gemini
//            String output = gemini.chat(input);
//            String output = gemini.chat(input, "gemini-2.5-pro");
            // https://ai.google.dev/gemini-api/docs/models?hl=ko
            // https://ai.google.dev/gemini-api/docs/rate-limits?hl=ko
//            String output = gemini.chat(input, "gemini-2.5-flash-lite");
            String output = gemini.chat(input, GeminiModel.GEMINI_2_5_FLASH_LITE);
            System.out.println("\uD83D\uDCAC AI : %s".formatted(output));
        }
        sc.close();
    }
}
