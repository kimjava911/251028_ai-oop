import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
            // Gemini
            HttpClient client = HttpClient.newHttpClient();
            // request, responseHandler
//            String API_KEY = "...";
            String API_KEY = System.getenv("GEMINI_API_KEY");
            // 없으면 Null
            if (API_KEY == null) {
                System.out.println("🤖 GEMINI_API_KEY가 없습니다");
                return;
            }
            /*
curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent"   -H "x-goog-api-key: $GEMINI_API_KEY"   -H 'Content-Type: application/json'   -X POST   -d '{
    "contents": [
      {
        "parts": [
          {
            "text": "Explain how AI works in a few words"
          }
        ]
      }
    ]
  }'
             */
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";
            String payload = """
            {"contents": [{"parts": [{"text": "%s"}]}]}
            """.formatted(input); // JSON 포맷
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("x-goog-api-key", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            payload
                    ))
                    .build();
            // http://aistudio.google.com/
            // HttpResponse.BodyHandlers.ofString() : 문자열
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                System.out.println(body);
            } catch (Exception ex) {
                ex.getStackTrace(); // 에러 추적 메시지
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
