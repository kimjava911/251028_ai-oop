import java.util.Scanner;

public class Application {
    // 진입점
    public static void main(String[] args) {
        System.out.println("챗봇 구동 시작");
        // Scanner -> input -> ai -> output
        // 반복
        // 객체의 생성은 반복 바깥에 진행되어야함
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("🤖 질문을 입력해주세요 : ");
            String input = sc.nextLine();
//            if (input == "종료") {
            if (input.equals("종료")) {
                System.out.println("🤖 챗봇 구동 종료");
                break;
            }
            String output = "무슨 말씀이시죠?";
//            System.out.println("AI : 무슨 말씀이시죠?");
            // %s <- String
            // mac : fn * 2, win : 윈도우(win) + .
            // https://emojipedia.org/
            System.out.println("\uD83D\uDCAC AI : %s".formatted(output));
        }
        sc.close();
    }
}
