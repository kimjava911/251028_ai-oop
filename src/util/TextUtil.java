package util;

public class TextUtil {
    // 접두사, 접미사
    public static String splitJson(String text, String prefix, String suffix) {
//        return text.split("\"text\": \"")[1]
//                .split("\"\\s*}")[0];
        return text.split(prefix)[1]
                .split(suffix)[0];
    }
}
