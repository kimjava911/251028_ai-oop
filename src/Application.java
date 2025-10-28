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
            System.out.print("질문을 입력해주세요 : ");
            String input = sc.nextLine();
            System.out.println("AI : 무슨 말씀이시죠?");
        }
//        sc.close();
    }
}
