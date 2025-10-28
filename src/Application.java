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
//            String output = gemini.chat(input, GeminiModel.GEMINI_2_5_FLASH_LITE);
            String output = gemini.chat(input, GeminiModel.GEMINI_2_5_FLASH_LITE, "50자 이내의 꾸미는 문법 없이 단순한 평문 텍스트로 결과만 작성");
            System.out.println("\uD83D\uDCAC AI : %s".formatted(output));
        }
        sc.close();
    }
}
